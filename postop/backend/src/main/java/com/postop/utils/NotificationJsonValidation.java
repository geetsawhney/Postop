package com.postop.utils;

import org.json.simple.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class NotificationJsonValidation {


    protected JSONObject jsonObject;

    /**
     * @param jsonObject
     */
    public NotificationJsonValidation(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    /**
     * @return
     */
    public boolean validateJson() {
        return validateLabel() && validateInterval() && validateEnd() && validateStart();
    }

    /**
     * @return
     */
    private boolean validateLabel(){
        if (!jsonObject.containsKey("label")) return false;

        String label = jsonObject.get("label").toString().trim();
        if (label.length() == 0) return false;

        return label.equals("C") || label.equals("H") || label.equals("M") || label.equals("L");
    }


    /**
     * @return
     */
    private boolean validateStart(){
        if (!jsonObject.containsKey("start")) return false;

        String start = jsonObject.get("start").toString().trim();
        String regex = "[0-9]+";
        return validateString(regex, start);
    }

    /**
     * @return
     */
    private boolean validateInterval(){
        if (!jsonObject.containsKey("interval")) return false;

        String interval = jsonObject.get("interval").toString().trim();
        String regex = "[0-9]+";
        return validateString(regex, interval);

    }

    /**
     * @return
     */
    private boolean validateEnd(){
        if (!jsonObject.containsKey("end")) return false;

        String end = jsonObject.get("end").toString().trim();
        String regex = "[0-9]+";
        return validateString(regex, end);
    }

    /**
     * @param regex
     * @param str
     * @return
     */
    private boolean validateString(String regex, String str) {
        Pattern checkRegex = Pattern.compile(regex);
        Matcher regexMatcher = checkRegex.matcher(str);

        return regexMatcher.matches();
    }
}
