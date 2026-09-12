package com.entelect.hackathon.models.state;

import com.entelect.hackathon.models.staticdata.Plant;

public class PlantInstance {
    private final Plant species;
    private int ageInTicks;

    public PlantInstance(Plant species) {
        this.species = species;
        this.ageInTicks = 0;
    }

    public void ageUp() {
        this.ageInTicks++;
    }

    public boolean isMature() {
        return ageInTicks >= species.getGrowth().getTimeToMaturity();
    }

    public Plant getSpecies() {
        return species;
    }

    public int getAgeInTicks() {
        return ageInTicks;
    }
}