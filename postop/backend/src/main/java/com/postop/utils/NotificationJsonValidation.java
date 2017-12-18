package com.postop.utils;

import org.json.simple.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class validates the JSON which holds the notification value.
 * @author Geet Sawhney, Rohit Aakash
 */
public class NotificationJsonValidation {


    protected JSONObject jsonObject;

    /**
     * @param jsonObject - the json object to be validated
     */
    public NotificationJsonValidation(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    /**
     * method to validate each field in the json
     * @return true if all valid
     */
    public boolean validateJson() {
        return validateLabel() && validateInterval() && validateEnd() && validateStart();
    }

    /**
     * method to validate label
     * @return true if present and valid format
     */
    private boolean validateLabel() {
        if (!jsonObject.containsKey("label")) return false;

        String label = jsonObject.get("label").toString().trim();
        return label.length() != 0 && (label.equals("C") || label.equals("H") || label.equals("M") || label.equals("L"));

    }


    /**
     * method to validate the start value
     * @return true if present and valid format
     */
    private boolean validateStart(){
        if (!jsonObject.containsKey("start")) return false;

        String start = jsonObject.get("start").toString().trim();
        String regex = "[0-9]+";
        return validateString(regex, start);
    }

    /**
     * Validate the interval field in JSON
     * @return true if present and valid format
     */
    private boolean validateInterval(){
        if (!jsonObject.containsKey("interval")) return false;

        String interval = jsonObject.get("interval").toString().trim();
        String regex = "[0-9]+";
        return validateString(regex, interval);

    }

    /**
     * Validate the end field in the json
     * @return true if present and valid format
     */
    private boolean validateEnd(){
        if (!jsonObject.containsKey("end")) return false;

        String end = jsonObject.get("end").toString().trim();
        String regex = "[0-9]+";
        return validateString(regex, end);
    }

    /**
     * @param regex - regular expression against which a string has to be checked
     * @param str - string to be checked
     * @return true if present and valid format
     */
    private boolean validateString(String regex, String str) {
        Pattern checkRegex = Pattern.compile(regex);
        Matcher regexMatcher = checkRegex.matcher(str);

        return regexMatcher.matches();
    }
}
