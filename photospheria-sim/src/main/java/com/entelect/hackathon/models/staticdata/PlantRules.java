package com.entelect.hackathon.models.staticdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlantRules {
    private List<PlantRule> weaknesses;
    private List<PlantRule> special;

    // Generate Getters and Setters
    public List<PlantRule> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<PlantRule> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<PlantRule> getSpecial() {
        return special;
    }

    public void setSpecial(List<PlantRule> special) {
        this.special = special;
    }
}