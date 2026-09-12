package com.entelect.hackathon.models.submission;

import com.fasterxml.jackson.annotation.JsonAlias;

public class PlantingCommand {
    @JsonAlias({ "index", "plant_index" })
    private int index;
    private int row;
    private int col;

    // Getters and Setters
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}