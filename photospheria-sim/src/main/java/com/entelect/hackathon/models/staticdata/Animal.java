package com.entelect.hackathon.models.staticdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Animal {
    private String id;
    private String name;
    private AnimalRequirement requirements;
    private List<AnimalEffect> effects;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalRequirement getRequirements() {
        return requirements;
    }

    public void setRequirements(AnimalRequirement requirements) {
        this.requirements = requirements;
    }

    public List<AnimalEffect> getEffects() {
        return effects;
    }

    public void setEffects(List<AnimalEffect> effects) {
        this.effects = effects;
    }
}