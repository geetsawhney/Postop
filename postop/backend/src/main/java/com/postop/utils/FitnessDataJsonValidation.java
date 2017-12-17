package com.postop.utils;

import org.json.simple.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class FitnessDataJsonValidation {

    protected JSONObject jsonObject;

    /**
     * @param jsonObject
     */
    public FitnessDataJsonValidation(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    /**
     * @return
     */
    public boolean validateJson() {

        return validateDeviceId() && validateCaptureDate() &&
                validateStepCount() && validateCaloriesExpended();
    }

    /**
     * @return
     */
    protected boolean validateDeviceId() {
        if (!jsonObject.containsKey("id")) return false;

        String id = jsonObject.get("id").toString().trim();
        return (id.length() > 0 && id.length()<=200);
    }

    /**
     * @return
     */
    protected boolean validateCaptureDate() {
        if (!jsonObject.containsKey("captureDate")) return false;

        String lastVisitDate = jsonObject.get("captureDate").toString().trim();
        if (lastVisitDate.length() != 10) return false;

        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        return validateString(regex, lastVisitDate);
    }

    /**
     * @return
     */
    protected boolean validateStepCount() {
        if (!jsonObject.containsKey("stepCount")) return false;

        String utiVisitCount = jsonObject.get("stepCount").toString().trim();

        String regex = "[0-9]+";
        return validateString(regex, utiVisitCount);
    }

    /**
     * @return
     */
    protected boolean validateCaloriesExpended() {
        if (!jsonObject.containsKey("caloriesExpended")) return false;

        String utiVisitCount = jsonObject.get("caloriesExpended").toString().trim();

        String regex = "[0-9]+";
        return validateString(regex, utiVisitCount);
    }

    /**
     * @param regex
     * @param str
     * @return
     */
    protected boolean validateString(String regex, String str) {
        Pattern checkRegex = Pattern.compile(regex);
        Matcher regexMatcher = checkRegex.matcher(str);

        return regexMatcher.matches();
    }
}
