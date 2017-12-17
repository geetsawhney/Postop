package com.postop.utils;

import org.json.simple.JSONObject;

public class CreatePatientJsonValidation extends UpdatePatientJsonValidation {

    public CreatePatientJsonValidation(JSONObject jsonObject) {
        super(jsonObject);
    }

    public boolean validateJson() {

        return validateAddress() && validateCatheterUsage() && validateDeviceId() && validateEmail() && validateName()
                && validatePhone() && validateSex() && validateHospitalVisitReason() && validateDiabetic()
                && validateDob() && validateUtiVisitCount() && validateLastVisitDate() && validatePassword();
    }

    private boolean validatePassword() {
        if (!jsonObject.containsKey("password"))
            return false;

        String address = jsonObject.get("password").toString().trim();
        return address.length() > 0 && address.length()<=32;
    }

    protected boolean validateDeviceId() {
        if (!jsonObject.containsKey("id")) return false;

        String id = jsonObject.get("id").toString().trim();
        return (id.length() == 0);
    }

}
