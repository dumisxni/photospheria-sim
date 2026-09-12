package com.entelect.hackathon.models.state;

import com.entelect.hackathon.models.staticdata.Plant;

public class PlantInstance {
    private final Plant species;
    private int ageInTicks;
    private int ticksSinceLastSpread;

    public PlantInstance(Plant species) {
        this.species = species;
        this.ageInTicks = 0;
        this.ticksSinceLastSpread = 0;
    }

    public void ageUp() {
        this.ageInTicks++;
        this.ticksSinceLastSpread++;
    }

    public boolean isMature() {
        return ageInTicks >= species.getGrowth().getTimeToMaturity();
    }

    public void resetSpreadCounter() {
        this.ticksSinceLastSpread = 0;
    }

    public Plant getSpecies() {
        return species;
    }

    public int getAgeInTicks() {
        return ageInTicks;
    }

    public int getTicksSinceLastSpread() {
        return ticksSinceLastSpread;
    }

}