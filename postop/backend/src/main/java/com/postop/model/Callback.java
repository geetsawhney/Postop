package com.postop.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCallbackDate() {
        return callbackDate;
    }

//    public String getDateString(){
//        return new SimpleDateFormat("yyyy-MM-dd").format(callbackDate);
//    }

//    public void setCallbackDate(String callbackDate) {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date d = null;
//        try {
//            d = df.parse(callbackDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        this.callbackDate = d;
//    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public boolean getHasPain() {
        return hasPain;
    }

    public void setHasPain(boolean hasPain) {
        this.hasPain = hasPain;
    }

    public boolean getHasNausea() {
        return hasNausea;
    }

    public void setHasNausea(boolean hasNausea) {
        this.hasNausea = hasNausea;
    }

    public boolean getHasFever() {
        return hasFever;
    }

    public void setHasFever(boolean hasFever) {
        this.hasFever = hasFever;
    }

    public boolean getHasFatigue() {
        return hasFatigue;
    }

    public void setHasFatigue(boolean hasFatigue) {
        this.hasFatigue = hasFatigue;
    }

    public String getUrineColor() {
        return urineColor;
    }

    public void setUrineColor(String urineColor) {
        this.urineColor = urineColor;
    }

    public boolean getIsResolved() {
        return isResolved;
    }

    public void setIsResolved(boolean resolved) {
        this.isResolved = resolved;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setCallbackDate(Date callbackDate) {
        this.callbackDate = callbackDate;
    }
}
