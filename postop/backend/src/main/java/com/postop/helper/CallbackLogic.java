package com.postop.helper;

import org.json.simple.JSONObject;

public class CallbackLogic {

    private JSONObject callback;

    public CallbackLogic(JSONObject callback) {
        this.callback = callback;
    }


    public int checkPain(int severity) {
        if (Boolean.parseBoolean(callback.get("hasPain").toString())) {
            severity++;
        }

        return checkNausea(severity);
    }

    public int checkNausea(int severity) {
        if (Boolean.parseBoolean(callback.get("hasNausea").toString())) {
            severity++;
        }

        return checkFever(severity);
    }

    public int checkFever(int severity) {
        if (Boolean.parseBoolean(callback.get("hasFever").toString())) {
            severity++;
        }

        return checkFatigue(severity);
    }

    public int checkFatigue(int severity) {
        if (Boolean.parseBoolean(callback.get("hasFatigue").toString())) {
            severity++;
        }

        return checkUrineColor(severity);
    }

    public int checkUrineColor(int severity) {

        String urineColor = callback.get("urineColor").toString();
        if (urineColor.equals("Dark")) {
            severity = severity + 3;
        } else if (urineColor.equals("Cloudy")) {
            severity = severity + 2;
        }
        return severity;
    }

    public int getSeverity() {
        return checkPain(0);
    }
}
