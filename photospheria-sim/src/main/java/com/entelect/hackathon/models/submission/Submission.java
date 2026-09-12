package com.entelect.hackathon.models.submission;

import java.util.List;

public class Submission {
    private List<TickAction> actions;

    public List<TickAction> getActions() {
        return actions;
    }

    public void setActions(List<TickAction> actions) {
        this.actions = actions;
    }
}