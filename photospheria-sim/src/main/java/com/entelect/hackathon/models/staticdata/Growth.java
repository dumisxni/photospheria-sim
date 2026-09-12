package com.entelect.hackathon.models.staticdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Growth {
    @JsonProperty("time_to_maturity")
    private int timeToMaturity;

    @JsonProperty("spread_rate")
    private int spreadRate;

    @JsonProperty("spread_mechanism")
    private String spreadMechanism;

    @JsonProperty("spread_type")
    private String spreadType;

    @JsonProperty("spread_range")
    private int spreadRange;

    @JsonProperty("root_type")
    private String rootType;

    @JsonProperty("invasiveness_rank")
    private int invasivenessRank;

    // Generate Getters and Setters for all fields
    public int getTimeToMaturity() {
        return timeToMaturity;
    }

    public void setTimeToMaturity(int timeToMaturity) {
        this.timeToMaturity = timeToMaturity;
    }

    public int getSpreadRate() {
        return spreadRate;
    }

    public void setSpreadRate(int spreadRate) {
        this.spreadRate = spreadRate;
    }

    public String getSpreadMechanism() {
        return spreadMechanism;
    }

    public void setSpreadMechanism(String spreadMechanism) {
        this.spreadMechanism = spreadMechanism;
    }

    public String getSpreadType() {
        return spreadType;
    }

    public void setSpreadType(String spreadType) {
        this.spreadType = spreadType;
    }

    public int getSpreadRange() {
        return spreadRange;
    }

    public void setSpreadRange(int spreadRange) {
        this.spreadRange = spreadRange;
    }

    public String getRootType() {
        return rootType;
    }

    public void setRootType(String rootType) {
        this.rootType = rootType;
    }

    public int getInvasivenessRank() {
        return invasivenessRank;
    }

    public void setInvasivenessRank(int invasivenessRank) {
        this.invasivenessRank = invasivenessRank;
    }
}