package com.entelect.hackathon.models.staticdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConditionNode {
    // Logical Node Fields
    private String op;
    private List<ConditionNode> children;
    private ConditionNode child;

    // Leaf Condition Fields
    private String type;
    private String species;
    private String plant;
    private String operator;
    private Double value; // Uses Double to handle both decimal coverage (0.05) and integer counts (4)
    private String event;
    private String feature;

    // Getters and Setters
    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public List<ConditionNode> getChildren() {
        return children;
    }

    public void setChildren(List<ConditionNode> children) {
        this.children = children;
    }

    public ConditionNode getChild() {
        return child;
    }

    public void setChild(ConditionNode child) {
        this.child = child;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}