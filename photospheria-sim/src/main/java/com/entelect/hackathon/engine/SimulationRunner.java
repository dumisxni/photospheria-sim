package com.entelect.hackathon.engine;

import com.entelect.hackathon.models.state.Grid;
import com.entelect.hackathon.models.state.Cell;
import com.entelect.hackathon.models.state.PlantInstance;
import com.entelect.hackathon.models.staticdata.Plant;
import com.entelect.hackathon.models.submission.Submission;
import com.entelect.hackathon.models.submission.TickAction;
import com.entelect.hackathon.models.submission.PlantingCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SimulationRunner {
    private final Grid grid;
    private final int maxTicks;
    private final Submission schedule;
    private final Map<Integer, Plant> plantCatalogue;
    private int currentTick;

    // Updated constructor to accept the schedule and the catalogue
    public SimulationRunner(Grid grid, int maxTicks, Submission schedule, Map<Integer, Plant> plantCatalogue) {
        this.grid = grid;
        this.maxTicks = maxTicks;
        this.schedule = schedule;
        this.plantCatalogue = plantCatalogue;
        this.currentTick = 0;
    }

    public void runSimulation() {
        for (currentTick = 0; currentTick < maxTicks; currentTick++) {
            processScheduledPlantings(currentTick);
            agePlants();
            processNutrientCycle();
            processPlantSpread();
            evaluateAnimalSpawns();
        }
    }

    public record SpreadAction(Cell target, Plant species) {
    }

    private void processNutrientCycle() {
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                Cell cell = grid.getCell(x, y);

                if (cell.isOccupied()) {
                    // Plants on dead matter drain slower (0.5), normal soil drains 1.0[cite: 4]
                    double drainRate = cell.hasDeadMatter() ? 0.5 : 1.0;
                    cell.setNutrients(cell.getNutrients() - drainRate);

                    // Plant dies when nutrients hit 0[cite: 4]
                    if (cell.getNutrients() <= 0) {
                        cell.killPlant();
                    }
                } else if (cell.hasDeadMatter() && cell.getNutrients() < 100.0) {
                    // Dead matter regains 1 point per tick, capped at 100[cite: 4]
                    cell.setNutrients(Math.min(100.0, cell.getNutrients() + 1.0));
                }
            }
        }
    }

    private void agePlants() {
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                Cell cell = grid.getCell(x, y);
                if (cell.isOccupied()) {
                    cell.getCurrentPlant().ageUp();
                }
            }
        }
    }

    private void processScheduledPlantings(int tick) {
        // Find if we have scheduled actions for the current tick
        Optional<TickAction> actionForTick = schedule.getActions().stream()
                .filter(action -> action.getTick() == tick)
                .findFirst();

        if (actionForTick.isPresent()) {
            // Only process a maximum of 20 plants per tick per the rules[cite: 4]
            actionForTick.get().getPlants().stream()
                    .limit(20)
                    .forEach(command -> {
                        int x = command.getCol(); // X maps to columns
                        int y = command.getRow(); // Y maps to rows
                        int plantIndex = command.getIndex();

                        // Validate coordinates and ensure soil type matches preferred soil[cite: 2, 4]
                        if (grid.isWithinBounds(x, y)) {
                            Cell cell = grid.getCell(x, y);
                            Plant species = plantCatalogue.get(plantIndex);

                            // TODO: Later add the UnlockEvaluator check here
                            if (species != null && species.getPreferredSoil().contains(cell.getSoilType())) {
                                cell.setCurrentPlant(new PlantInstance(species));
                                cell.setDeadMatter(false); // Placing a new plant clears dead matter flag
                            }
                        }
                    });
        }
    }

    private void processPlantSpread() {
        List<SpreadAction> pendingSpreads = new ArrayList<>();

        // Pass 1: Identify all mature plants ready to spread
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                Cell cell = grid.getCell(x, y);

                if (cell.isOccupied() && cell.getCurrentPlant().isMature()) {
                    PlantInstance plant = cell.getCurrentPlant();
                    int spreadRate = plant.getSpecies().getGrowth().getSpreadRate();

                    // Trigger spread if enough ticks have passed[cite: 2]
                    if (plant.getTicksSinceLastSpread() >= spreadRate) {
                        String type = plant.getSpecies().getGrowth().getSpreadType();
                        int range = plant.getSpecies().getGrowth().getSpreadRange();

                        List<Cell> targets = PropagationService.getTargetCells(grid, cell, type, range);
                        for (Cell target : targets) {
                            pendingSpreads.add(new SpreadAction(target, plant.getSpecies()));
                        }

                        plant.resetSpreadCounter();
                    }
                }
            }
        }

        // Pass 2: Apply the spreads and resolve conflicts
        for (SpreadAction action : pendingSpreads) {
            Cell target = action.target();
            Plant newSpecies = action.species();

            // Ignore if soil type is incompatible[cite: 2, 4]
            if (!newSpecies.getPreferredSoil().contains(target.getSoilType())) {
                continue;
            }

            if (target.isOccupied()) {
                PlantInstance existingPlant = target.getCurrentPlant();

                if (!existingPlant.isMature()) {
                    // Immature plants cannot use invasiveness rank, the newest plant simply
                    // overwrites[cite: 4]
                    target.setCurrentPlant(new PlantInstance(newSpecies));
                    target.setDeadMatter(false);
                } else {
                    // Mature plants fight based on invasiveness rank[cite: 4]
                    int existingRank = existingPlant.getSpecies().getGrowth().getInvasivenessRank();
                    int newRank = newSpecies.getGrowth().getInvasivenessRank();

                    if (newRank > existingRank) {
                        target.setCurrentPlant(new PlantInstance(newSpecies));
                        target.setDeadMatter(false);
                    }
                }
            } else {
                // Empty soil, just plant it
                target.setCurrentPlant(new PlantInstance(newSpecies));
                target.setDeadMatter(false);
            }
        }
    }

    private void evaluateAnimalSpawns() {
        // TODO: Check coverage thresholds for animals and apply buffs[cite: 5]
    }
}