package com.entelect.hackathon.engine;

import com.entelect.hackathon.models.state.Cell;
import com.entelect.hackathon.models.state.Grid;

public class GridAnalytics {
    private final Grid grid;
    private final double totalCells;

    public GridAnalytics(Grid grid) {
        this.grid = grid;
        this.totalCells = grid.getWidth() * grid.getHeight();
    }

    public int getPlantCount(String plantName) {
        int count = 0;
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                Cell cell = grid.getCell(x, y);
                if (cell.isOccupied() && cell.getCurrentPlant().getSpecies().getPlant().equals(plantName)) {
                    count++;
                }
            }
        }
        return count;
    }

    public double getPlantCoverage(String plantName) {
        // Coverage is the proportion of total available cells occupied by the
        // plant[cite: 4]
        return getPlantCount(plantName) / totalCells;
    }

    public boolean isSpeciesPresent(String speciesName) {
        // This will eventually check both plants and animals. For now, it checks
        // plants.
        return getPlantCount(speciesName) > 0;
    }

    public int getFeatureCount(String featureType) {
        int count = 0;
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                Cell cell = grid.getCell(x, y);
                if ("dead_matter".equals(featureType) && cell.hasDeadMatter())
                    count++;
                if ("burnt_soil".equals(featureType) && cell.getSoilType() == 3)
                    count++;
            }
        }
        return count;
    }
}