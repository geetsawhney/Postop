package com.postop.utils;

import org.json.simple.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CallbackJsonValidation {

    protected JSONObject jsonObject;

    public CallbackJsonValidation(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public boolean validateJson() {
        return validateEmail() && validateCallbackDate() && validateIsResolved() && validateSeverity() &&
                validateHasFever() && validateHasPain() && validateHasFatigue() && validateUrineColor();
    }

    protected boolean validateEmail() {
        if (!jsonObject.containsKey("email")) return false;

        String email = jsonObject.get("email").toString().trim().toLowerCase();
        if (email.length() == 0) return false;

        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        return validateString(regex, email);
    }

    protected boolean validateCallbackDate(){
        if (!jsonObject.containsKey("callbackDate")) return false;

        String callbackDate = jsonObject.get("callbackDate").toString().trim();
        if (callbackDate.length() != 10) return false;

        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        return validateString(regex, callbackDate);
    }

    protected boolean validateIsResolved() {
        if (!jsonObject.containsKey("isResolved")) return false;

        String isResolved = jsonObject.get("isResolved").toString().trim();

        return isResolved.equals("true") || isResolved.equals("false");
    }

    protected boolean validateSeverity() {
        if (!jsonObject.containsKey("severity")) return false;

        String severity = jsonObject.get("severity").toString().trim();

        String regex = "[0-9]+";
        return validateString(regex, severity);
    }

    protected boolean validateHasFever() {
        if (!jsonObject.containsKey("hasFever")) return false;

        String hasFever = jsonObject.get("hasFever").toString().trim();

        return hasFever.equals("true") || hasFever.equals("false");
    }

    protected boolean validateHasPain() {
        if (!jsonObject.containsKey("hasPain")) return false;

        String hasPain = jsonObject.get("hasPain").toString().trim();

        return hasPain.equals("true") || hasPain.equals("false");
    }

    protected boolean validateHasFatigue() {
        if (!jsonObject.containsKey("hasFatigue")) return false;

        String hasFatigue = jsonObject.get("hasFatigue").toString().trim();

        return hasFatigue.equals("true") || hasFatigue.equals("false");
    }

    protected boolean validateUrineColor() {
        if (!jsonObject.containsKey("urineColor")) return false;

        String urineColor = jsonObject.get("urineColor").toString().trim();
        if (urineColor.length() == 0) return false;

        String regex = "[MF]";
        return urineColor.equals("Cloudy") || urineColor.equals("Dark") || urineColor.equals("Normal");
    }

    protected boolean validateString(String regex, String str) {
        Pattern checkRegex = Pattern.compile(regex);
        Matcher regexMatcher = checkRegex.matcher(str);

        return regexMatcher.matches();
    }

}
