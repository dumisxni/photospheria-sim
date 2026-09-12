package com.entelect.hackathon.models.submission;

import java.util.List;

public class TickAction {
    private int tick;
    private List<PlantingCommand> plants;

    // Getters and Setters
    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public List<PlantingCommand> getPlants() {
        return plants;
    }

    public void setPlants(List<PlantingCommand> plants) {
        this.plants = plants;
    }
}