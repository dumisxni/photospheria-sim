package com.entelect.hackathon.models.staticdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalRequirement {
    private String type;
    private List<AnimalCondition> conditions;

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AnimalCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<AnimalCondition> conditions) {
        this.conditions = conditions;
    }
}