package com.entelect.hackathon.models.state;

public class Grid {
    private final int width;
    private final int height;
    private final Cell[][] cells;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width][height];

        // For testing, we initialize the entire board as Dirt (Soil Type 0)
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y, 0);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (isWithinBounds(x, y)) {
            return cells[x][y];
        }
        return null; // Or throw an exception depending on your design preference
    }

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}