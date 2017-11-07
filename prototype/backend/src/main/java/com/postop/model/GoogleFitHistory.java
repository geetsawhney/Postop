package com.postop.model;

import java.util.Date;

public class GoogleFitHistory {

    private Date date;
    private int stepCount;
    private int caloriesExpended;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public int getCaloriesExpended() {
        return caloriesExpended;
    }

    public void setCaloriesExpended(int caloriesExpended) {
        this.caloriesExpended = caloriesExpended;
    }
}