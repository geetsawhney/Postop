package com.postop.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FitnessHistory {
    private String email;
    private Date captureDate;
    private int stepCount;
    private int caloriesExpended;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(String captureDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(captureDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.captureDate = date;

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