package com.postop.utils;

import org.json.simple.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class UpdatePatientJsonValidation {

    protected JSONObject jsonObject;

    /**
     * @param jsonObject
     */
    public UpdatePatientJsonValidation(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    /**
     * @return
     */
    public boolean validateJson() {

        return validateAddress() && validateCatheterUsage() && validateDeviceId() && validateEmail() && validateName() &&
                validatePhone() && validateSex() && validateHospitalVisitReason() && validateDiabetic()
                && validateDob() && validateUtiVisitCount() && validateLastVisitDate();
    }

    /**
     * @return
     */
    protected boolean validateName() {
        if (!jsonObject.containsKey("name")) return false;

        String name = jsonObject.get("name").toString().trim();
        if (name.length() == 0 || name.length()>50) return false;

        String regex = "^[A-Za-z ,'-.]++$";
        return validateString(regex, name);
    }

    /**
     * @return
     */
    protected boolean validateEmail() {
        if (!jsonObject.containsKey("email")) return false;

        String email = jsonObject.get("email").toString().trim().toLowerCase();
        if (email.length() == 0 || email.length()>50) return false;

        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        return validateString(regex, email);
    }

    /**
     * @return
     */
    protected boolean validateDeviceId() {
        if (!jsonObject.containsKey("id")) return false;

        String id = jsonObject.get("id").toString().trim();
        return (id.length()<=200);
    }

    /**
     * @return
     */
    protected boolean validateSex() {
        if (!jsonObject.containsKey("sex")) return false;

        String sex = jsonObject.get("sex").toString().trim();
        if (sex.length() != 1) return false;

        String regex = "[MF]";
        return validateString(regex, sex);
    }

    /**
     * @return
     */
    protected boolean validateDob() {
        if (!jsonObject.containsKey("dob")) return false;

        String dob = jsonObject.get("dob").toString().trim();
        if (dob.length() != 10) return false;

        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        return validateString(regex, dob);
    }

    /**
     * @return
     */
    protected boolean validateAddress() {
        if (!jsonObject.containsKey("address"))
            return false;

        String address = jsonObject.get("address").toString().trim();
        return (address.length() > 0 && address.length() <=80);
    }

    /**
     * @return
     */
    protected boolean validateHospitalVisitReason() {
        if (!jsonObject.containsKey("hospitalVisitReason"))
            return false;

        String hospitalVisitReason = jsonObject.get("hospitalVisitReason").toString().trim();
        return hospitalVisitReason.length() > 0 && hospitalVisitReason.length() <=50;
    }

    /**
     * @return
     */
    protected boolean validateLastVisitDate() {
        if (!jsonObject.containsKey("lastVisitDate")) return false;

        String lastVisitDate = jsonObject.get("lastVisitDate").toString().trim();
        if (lastVisitDate.length() != 10) return false;

        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        return validateString(regex, lastVisitDate);
    }

    /**
     * @return
     */
    protected boolean validateUtiVisitCount() {
        if (!jsonObject.containsKey("utiVisitCount")) return false;

        String utiVisitCount = jsonObject.get("utiVisitCount").toString().trim();
        String regex = "[0-9]+";
        return validateString(regex, utiVisitCount);
    }

    /**
     * @return
     */
    protected boolean validateDiabetic() {
        if (!jsonObject.containsKey("diabetic")) return false;

        String diabetic = jsonObject.get("diabetic").toString().trim();

        return diabetic.equals("true") || diabetic.equals("false");
    }

    /**
     * @return
     */
    protected boolean validateCatheterUsage() {
        if (!jsonObject.containsKey("catheterUsage")) return false;

        String catheterUsage = jsonObject.get("catheterUsage").toString().trim();

        return catheterUsage.equals("true") || catheterUsage.equals("false");
    }


    /**
     * @return
     */
    protected boolean validatePhone() {
        if (!jsonObject.containsKey("phone")) return false;

        String phone = jsonObject.get("phone").toString().trim();
        if (phone.length() != 10)
            return false;

        String regex = "[0-9]{10}";
        return validateString(regex, phone);
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