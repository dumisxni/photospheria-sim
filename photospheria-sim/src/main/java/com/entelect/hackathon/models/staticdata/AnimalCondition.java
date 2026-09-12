package com.entelect.hackathon.models.staticdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalCondition {
    private String type;
    private JsonNode species; // Handles both single strings and arrays

    @JsonProperty("species_group")
    private List<String> speciesGroup;

    private Double threshold;
    private String operator;
    private String mode;

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsonNode getSpecies() {
        return species;
    }

    public void setSpecies(JsonNode species) {
        this.species = species;
    }

    public List<String> getSpeciesGroup() {
        return speciesGroup;
    }

    public void setSpeciesGroup(List<String> speciesGroup) {
        this.speciesGroup = speciesGroup;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}