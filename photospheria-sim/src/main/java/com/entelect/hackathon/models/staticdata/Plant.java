package com.entelect.hackathon.models.staticdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Plant {
    private String plant;
    private int index;
    private Growth growth;

    @JsonProperty("preferred_soil")
    private List<Integer> preferredSoil;

    private PlantRules rules;
    private String role;

    // Generate Getters and Setters for all fields
    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Growth getGrowth() {
        return growth;
    }

    public void setGrowth(Growth growth) {
        this.growth = growth;
    }

    public List<Integer> getPreferredSoil() {
        return preferredSoil;
    }

    public void setPreferredSoil(List<Integer> preferredSoil) {
        this.preferredSoil = preferredSoil;
    }

    public PlantRules getRules() {
        return rules;
    }

    public void setRules(PlantRules rules) {
        this.rules = rules;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}