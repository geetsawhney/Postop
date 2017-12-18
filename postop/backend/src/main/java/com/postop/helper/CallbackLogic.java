package com.postop.helper;

import org.json.simple.JSONObject;

/**
 * Computes callback severity
 * @author Rohit Aakash, Geet Sawhney
 */
public class CallbackLogic {

    private JSONObject callback;

    public CallbackLogic(JSONObject callback) {
        this.callback = callback;
    }


    /**
     * Increments the severity by 1 if patient has body pain
     * @param severity: current severity
     * @return updated severity
     */
    public int checkPain(int severity) {
        if (Boolean.parseBoolean(callback.get("hasPain").toString())) {
            severity++;
        }

        return checkNausea(severity);
    }

    /**
     * Increments the severity by 1 if patient has nausea
     * @param severity: current severity
     * @return updated severity
     */
    public int checkNausea(int severity) {
        if (Boolean.parseBoolean(callback.get("hasNausea").toString())) {
            severity++;
        }

        return checkFever(severity);
    }

    /**
     * Increments the severity by 1 if patient has fever
     * @param severity: current severity
     * @return updated severity
     */
    public int checkFever(int severity) {
        if (Boolean.parseBoolean(callback.get("hasFever").toString())) {
            severity++;
        }

        return checkFatigue(severity);
    }

    /**
     * Increments the severity by 1 if patient has fatigue
     * @param severity: current severity
     * @return updated severity
     */
    public int checkFatigue(int severity) {
        if (Boolean.parseBoolean(callback.get("hasFatigue").toString())) {
            severity++;
        }

        return checkUrineColor(severity);
    }

    /**
     * Increments the severity according to the urine color
     * @param severity: current severity
     * @return updated severity
     */
    public int checkUrineColor(int severity) {

        String urineColor = callback.get("urineColor").toString();
        if (urineColor.equals("Dark")) {
            severity = severity + 3;
        } else if (urineColor.equals("Cloudy")) {
            severity = severity + 2;
        }
        return severity;
    }

    /**
     * Makes calls to intermediate methods to return the final severity of a callback
     * @return severity of a callback
     */
    public int getSeverity() {
        return checkPain(0);
    }
}
