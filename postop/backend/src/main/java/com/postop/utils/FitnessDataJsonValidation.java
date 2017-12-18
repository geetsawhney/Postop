package com.postop.utils;

import org.json.simple.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to validate the JSON containing fitness data using regex and field lengths.
 * @author Geet Sawhney, Rohit Aakash
 */
public class FitnessDataJsonValidation {

    protected JSONObject jsonObject;

    /**
     * @param jsonObject - json object to be validated
     */
    public FitnessDataJsonValidation(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    /**
     * validate all the fields and returns true if all are valid
     * @return true if all valid
     */
    public boolean validateJson() {

        return validateDeviceId() && validateCaptureDate() &&
                validateStepCount() && validateCaloriesExpended();
    }

    /**
     * Validate the device id field
     * @return true if present and valid format
     */
    private boolean validateDeviceId() {
        if (!jsonObject.containsKey("id")) return false;

        String id = jsonObject.get("id").toString().trim();
        return (id.length() > 0 && id.length()<=200);
    }

    /**
     * validate the capture date
     * @return true if present and valid format
     */
    private boolean validateCaptureDate() {
        if (!jsonObject.containsKey("captureDate")) return false;

        String lastVisitDate = jsonObject.get("captureDate").toString().trim();
        if (lastVisitDate.length() != 10) return false;

        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        return validateString(regex, lastVisitDate);
    }

    /**
     * validate the step count
     * @return true if present and valid format
     */
    private boolean validateStepCount() {
        if (!jsonObject.containsKey("stepCount")) return false;

        String utiVisitCount = jsonObject.get("stepCount").toString().trim();

        String regex = "[0-9]+";
        return validateString(regex, utiVisitCount);
    }

    /**
     * validate the calories expended field
     * @return true if present and valid format
     */
    private boolean validateCaloriesExpended() {
        if (!jsonObject.containsKey("caloriesExpended")) return false;

        String utiVisitCount = jsonObject.get("caloriesExpended").toString().trim();

        String regex = "[0-9]+";
        return validateString(regex, utiVisitCount);
    }

    /**
     * @param regex - regular expression against which a string has to be checked
     * @param str - string to be checked
     * @return  true if pattern matches
     *
     */
    private boolean validateString(String regex, String str) {
        Pattern checkRegex = Pattern.compile(regex);
        Matcher regexMatcher = checkRegex.matcher(str);

        return regexMatcher.matches();
    }
}
