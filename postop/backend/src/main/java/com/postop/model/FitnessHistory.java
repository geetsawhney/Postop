package com.postop.model;

import java.sql.Date;

/**
 *  FitnessHistory class to represent
 */
public class FitnessHistory {
    private String email;
    private Date captureDate;
    private int stepCount;
    private int caloriesExpended;

    /**
     *  Default constructor
     */
    public FitnessHistory() {
    }

    /**
     * Parameterized constructor
     * @param email
     * @param captureDate
     * @param stepCount
     * @param caloriesExpended
     */
    public FitnessHistory(String email, Date captureDate, int stepCount, int caloriesExpended) {
        this.email = email;
        this.captureDate = captureDate;
        this.stepCount = stepCount;
        this.caloriesExpended = caloriesExpended;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return
     */
    public Date getCaptureDate() {
        return captureDate;
    }

    /**
     * @param captureDate
     */
    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
    }

    /**
     * @return
     */
    public int getCaloriesExpended() {
        return caloriesExpended;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return
     */
    public int getStepCount() {
        return stepCount;
    }

    /**
     * @param stepCount
     */
    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    /**
     * @param caloriesExpended
     */
    public void setCaloriesExpended(int caloriesExpended) {
        this.caloriesExpended = caloriesExpended;
    }
}