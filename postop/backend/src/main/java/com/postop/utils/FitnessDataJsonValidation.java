package com.postop.utils;

import org.json.simple.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FitnessDataJsonValidation {

    protected JSONObject jsonObject;

    public FitnessDataJsonValidation(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public boolean validateJson() {

        return validateDeviceId() && validateCaptureDate() &&
                validateStepCount() && validateCaloriesExpended();
    }

    protected boolean validateDeviceId() {
        if (!jsonObject.containsKey("id")) return false;

        String id = jsonObject.get("id").toString().trim();
        return (id.length() > 0 && id.length()<=200);
    }

    protected boolean validateCaptureDate() {
        if (!jsonObject.containsKey("captureDate")) return false;

        String lastVisitDate = jsonObject.get("captureDate").toString().trim();
        if (lastVisitDate.length() != 10) return false;

        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        return validateString(regex, lastVisitDate);
    }

    protected boolean validateStepCount() {
        if (!jsonObject.containsKey("stepCount")) return false;

        String utiVisitCount = jsonObject.get("stepCount").toString().trim();

        String regex = "[0-9]+";
        return validateString(regex, utiVisitCount);
    }

    protected boolean validateCaloriesExpended() {
        if (!jsonObject.containsKey("caloriesExpended")) return false;

        String utiVisitCount = jsonObject.get("caloriesExpended").toString().trim();

        String regex = "[0-9]+";
        return validateString(regex, utiVisitCount);
    }

    protected boolean validateString(String regex, String str) {
        Pattern checkRegex = Pattern.compile(regex);
        Matcher regexMatcher = checkRegex.matcher(str);

        return regexMatcher.matches();
    }
}
