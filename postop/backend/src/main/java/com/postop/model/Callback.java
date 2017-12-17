package com.postop.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;

/**
 * Callback class to represent a callback for a patient
 */
public class Callback {

    private String email;
    private Date callbackDate;
    private int severity;
    private boolean hasPain;
    private boolean hasNausea;
    private boolean hasFever;
    private boolean hasFatigue;
    private String urineColor;
    private boolean isResolved;

    private final Logger logger = LoggerFactory.getLogger(Callback.class);

    /**
     * @return
     */
    public String getEmail() {
        return email;
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
    public Date getCallbackDate() {
        return callbackDate;
    }

    /**
     * @return
     */
    public int getSeverity() {
        return severity;
    }

    /**
     * @param severity
     */
    public void setSeverity(int severity) {
        this.severity = severity;
    }

    /**
     * @return
     */
    public boolean getHasPain() {
        return hasPain;
    }

    /**
     * @param hasPain
     */
    public void setHasPain(boolean hasPain) {
        this.hasPain = hasPain;
    }

    /**
     * @return
     */
    public boolean getHasNausea() {
        return hasNausea;
    }

    /**
     * @param hasNausea
     */
    public void setHasNausea(boolean hasNausea) {
        this.hasNausea = hasNausea;
    }

    /**
     * @return
     */
    public boolean getHasFever() {
        return hasFever;
    }

    /**
     * @param hasFever
     */
    public void setHasFever(boolean hasFever) {
        this.hasFever = hasFever;
    }

    /**
     * @return
     */
    public boolean getHasFatigue() {
        return hasFatigue;
    }

    /**
     * @param hasFatigue
     */
    public void setHasFatigue(boolean hasFatigue) {
        this.hasFatigue = hasFatigue;
    }

    /**
     * @return
     */
    public String getUrineColor() {
        return urineColor;
    }

    /**
     * @param urineColor
     */
    public void setUrineColor(String urineColor) {
        this.urineColor = urineColor;
    }

    /**
     * @return
     */
    public boolean getIsResolved() {
        return isResolved;
    }

    /**
     * @param resolved
     */
    public void setIsResolved(boolean resolved) {
        this.isResolved = resolved;
    }

    /**
     * @param callbackDate
     */
    public void setCallbackDate(Date callbackDate) {
        this.callbackDate = callbackDate;
    }
}
