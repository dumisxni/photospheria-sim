package com.entelect.hackathon.engine;

import com.entelect.hackathon.models.state.Cell;
import com.entelect.hackathon.models.state.Grid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridAnalytics {
    private final Grid grid;
    private final double totalCells;
    private final Map<String, List<String>> classifications;

    public GridAnalytics(Grid grid) {
        this.grid = grid;
        this.totalCells = grid.getWidth() * grid.getHeight();
        this.classifications = new HashMap<>();
    }

    // The new constructor for animal group math
    public GridAnalytics(Grid grid, Map<String, List<String>> classifications) {
        this.grid = grid;
        this.totalCells = grid.getWidth() * grid.getHeight();
        this.classifications = classifications;
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

    public double getGroupCoverage(List<String> groupNames) {
        double totalCoverage = 0;
        for (String group : groupNames) {
            List<String> speciesInGroup = classifications.getOrDefault(group, List.of());
            for (String species : speciesInGroup) {
                totalCoverage += getPlantCoverage(species);
            }
        }
        return totalCoverage;
    }

    public int getGroupCount(List<String> groupNames) {
        int totalCount = 0;
        for (String group : groupNames) {
            List<String> speciesInGroup = classifications.getOrDefault(group, List.of());
            for (String species : speciesInGroup) {
                totalCount += getPlantCount(species);
            }
        }
        return totalCount;
    }

    public double getHighestSpeciesDominance() {
        Map<String, Integer> speciesCounts = new HashMap<>();
        int maxCount = 0;

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                Cell cell = grid.getCell(x, y);
                if (cell.isOccupied()) {
                    String name = cell.getCurrentPlant().getSpecies().getPlant();
                    int count = speciesCounts.getOrDefault(name, 0) + 1;
                    speciesCounts.put(name, count);
                    if (count > maxCount)
                        maxCount = count;
                }
            }
        }
        return maxCount / totalCells;
    }

}