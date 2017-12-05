package com.postop.helper;

import org.json.simple.JSONObject;

public class CallbackLogic {

    private int severity;
    private JSONObject callback;

    public CallbackLogic(JSONObject callback) {
        this.severity = 0;
        this.callback = callback;
    }


    public void checkPain() {
        if (Boolean.parseBoolean(callback.get("hasPain").toString())) {
            severity++;
        }

        checkNausea();
    }

    public void checkNausea() {
        if (Boolean.parseBoolean(callback.get("hasNausea").toString())) {
            severity++;
        }

        checkFever();
    }

    public void checkFever() {
        if (Boolean.parseBoolean(callback.get("hasFever").toString())) {
            severity++;
        }

        checkFatigue();
    }

    public void checkFatigue() {
        if (Boolean.parseBoolean(callback.get("hasFatigue").toString())) {
            severity++;
        }

        checkUrineColor();
    }

    public void checkUrineColor() {

        String urineColor = callback.get("urineColor").toString();
        if (urineColor.equals("Dark")) {
            severity = severity + 3;
        } else if (urineColor.equals("Cloudy")) {
            severity = severity + 2;
        }
    }

    public int getSeverity() {
        checkPain();
        return severity;
    }
}
