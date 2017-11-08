package com.postop.model;

import java.util.Date;

public class FitnessHistory {
    private String device_id;
    private Date captureDate;
    private int stepCount;
    private int caloriesExpended;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public Date getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
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