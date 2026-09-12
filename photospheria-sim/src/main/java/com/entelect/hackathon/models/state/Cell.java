package com.entelect.hackathon.models.state;

public class Cell {
    private final int x;
    private final int y;
    private final int soilType; // 0: Dirt, 1: Mud, 2: Clay, 3: Burnt

    private double nutrients;
    private boolean hasDeadMatter;
    private PlantInstance currentPlant;

    public Cell(int x, int y, int soilType) {
        this.x = x;
        this.y = y;
        this.soilType = soilType;
        this.nutrients = 100.0; // All cells start with 100 nutrient points
        this.hasDeadMatter = false;
        this.currentPlant = null;
    }

    public void killPlant() {
        this.currentPlant = null;
        this.hasDeadMatter = true;
    }

    public boolean isOccupied() {
        return this.currentPlant != null;
    }

    // Getters and Setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSoilType() {
        return soilType;
    }

    public double getNutrients() {
        return nutrients;
    }

    public void setNutrients(double nutrients) {
        this.nutrients = nutrients;
    }

    public boolean hasDeadMatter() {
        return hasDeadMatter;
    }

    public void setDeadMatter(boolean hasDeadMatter) {
        this.hasDeadMatter = hasDeadMatter;
    }

    public PlantInstance getCurrentPlant() {
        return currentPlant;
    }

    public void setCurrentPlant(PlantInstance currentPlant) {
        this.currentPlant = currentPlant;
    }
}