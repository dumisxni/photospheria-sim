package com.entelect.hackathon.engine;

import com.entelect.hackathon.models.state.Cell;
import com.entelect.hackathon.models.state.Grid;
import java.util.ArrayList;
import java.util.List;

public class PropagationService {

    public static List<Cell> getTargetCells(Grid grid, Cell origin, String spreadType, int range) {
        List<Cell> targets = new ArrayList<>();
        int cx = origin.getX();
        int cy = origin.getY();

        // Calculate spatial propagation geometry[cite: 2]
        switch (spreadType) {
            case "VonNeumann":
                // 4-directional (N, S, E, W)[cite: 2]
                for (int i = 1; i <= range; i++) {
                    addIfValid(grid, targets, cx + i, cy);
                    addIfValid(grid, targets, cx - i, cy);
                    addIfValid(grid, targets, cx, cy + i);
                    addIfValid(grid, targets, cx, cy - i);
                }
                break;
            case "Moore":
                // 8-directional (includes diagonals)[cite: 2]
                for (int x = cx - range; x <= cx + range; x++) {
                    for (int y = cy - range; y <= cy + range; y++) {
                        if (x == cx && y == cy)
                            continue;
                        addIfValid(grid, targets, x, y);
                    }
                }
                break;
            case "Row":
                // Linear horizontal[cite: 2]
                for (int i = 1; i <= range; i++) {
                    addIfValid(grid, targets, cx + i, cy);
                    addIfValid(grid, targets, cx - i, cy);
                }
                break;
            case "Column":
                // Linear vertical[cite: 2]
                for (int i = 1; i <= range; i++) {
                    addIfValid(grid, targets, cx, cy + i);
                    addIfValid(grid, targets, cx, cy - i);
                }
                break;
            case "CrossHatch":
                // Multi-axis expansion (standard diagonals)[cite: 2]
                for (int i = 1; i <= range; i++) {
                    addIfValid(grid, targets, cx + i, cy + i);
                    addIfValid(grid, targets, cx - i, cy - i);
                    addIfValid(grid, targets, cx + i, cy - i);
                    addIfValid(grid, targets, cx - i, cy + i);
                }
                break;
        }
        return targets;
    }

    private static void addIfValid(Grid grid, List<Cell> targets, int x, int y) {
        if (grid.isWithinBounds(x, y)) {
            targets.add(grid.getCell(x, y));
        }
    }
}