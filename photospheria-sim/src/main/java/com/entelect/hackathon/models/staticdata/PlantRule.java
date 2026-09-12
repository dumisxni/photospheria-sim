package com.entelect.hackathon.models.staticdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlantRule {
    private String type;
    private Integer value; // Use Integer wrapper class so it can be null if not present
    private String feature;
    private JsonNode species; // Can hold a string or an array of strings

    // Generate Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public JsonNode getSpecies() {
        return species;
    }

    public void setSpecies(JsonNode species) {
        this.species = species;
    }
}