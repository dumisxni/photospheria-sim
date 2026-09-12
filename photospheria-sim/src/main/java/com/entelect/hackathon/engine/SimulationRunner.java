package com.entelect.hackathon.engine;

import com.entelect.hackathon.models.state.Grid;
import com.entelect.hackathon.models.state.Cell;

public class SimulationRunner {
    private final Grid grid;
    private final int maxTicks;
    private int currentTick;

    public SimulationRunner(Grid grid, int maxTicks) {
        this.grid = grid;
        this.maxTicks = maxTicks;
        this.currentTick = 0;
    }

    public void runSimulation() {
        for (currentTick = 0; currentTick < maxTicks; currentTick++) {
            // 1. Process new plant placements from your JSON schedule (Stub)
            processScheduledPlantings(currentTick);

            // 2. Process biological aging for maturity checks
            agePlants();

            // 3. Process the soil ecosystem and death mechanics
            processNutrientCycle();

            // 4. Spread and Animals (Stubs for the next phases)
            processPlantSpread();
            evaluateAnimalSpawns();
        }
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
        // TODO: Read from a generated schedule and place up to 20 plants per tick[cite:
        // 4]
    }

    private void processPlantSpread() {
        // TODO: Handle spread rates, geometrical propagation, and invasiveness
        // conflicts[cite: 2, 4]
    }

    private void evaluateAnimalSpawns() {
        // TODO: Check coverage thresholds for animals and apply buffs[cite: 5]
    }
}