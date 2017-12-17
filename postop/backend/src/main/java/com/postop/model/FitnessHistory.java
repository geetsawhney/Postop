package com.postop.model;

import java.sql.Date;

public class FitnessHistory {
    private String email;
    private Date captureDate;
    private int stepCount;
    private int caloriesExpended;

    public FitnessHistory() {
    }

    public FitnessHistory(String email, Date captureDate, int stepCount, int caloriesExpended) {
        this.email = email;
        this.captureDate = captureDate;
        this.stepCount = stepCount;
        this.caloriesExpended = caloriesExpended;
    }

    public String getEmail() {
        return email;
    }

    public Date getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
    }

    public int getCaloriesExpended() {
        return caloriesExpended;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public void setCaloriesExpended(int caloriesExpended) {
        this.caloriesExpended = caloriesExpended;
    }
}