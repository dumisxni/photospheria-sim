package com.entelect.hackathon.models.staticdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnlockCondition {
    private String plant;
    private ConditionNode unlock;

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public ConditionNode getUnlock() {
        return unlock;
    }

    public void setUnlock(ConditionNode unlock) {
        this.unlock = unlock;
    }
}