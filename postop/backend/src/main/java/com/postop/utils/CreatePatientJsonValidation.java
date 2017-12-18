package com.postop.utils;

import org.json.simple.JSONObject;

/**
 * To validate JSON for a new patient, by checking the presence and formats of all fields using regex and length.
 * @author Geet Sawhney, Rohit Aakash
 */
public class CreatePatientJsonValidation extends UpdatePatientJsonValidation {

    /**
     * @param jsonObject : json to be validated
     */
    public CreatePatientJsonValidation(JSONObject jsonObject) {
        super(jsonObject);
    }

    /**
     * validate all the fields and return true if all are valid
     * else false
     * @return true if present and valid format
     */
    @Override
    public boolean validateJson() {

        return validateAddress() && validateCatheterUsage() && validateDeviceId() && validateEmail() && validateName()
                && validatePhone() && validateSex() && validateHospitalVisitReason() && validateDiabetic()
                && validateDob() && validateUtiVisitCount() && validateLastVisitDate() && validatePassword();
    }

    /**
     * validate password
     * @return true if present and valid format
     */
    private boolean validatePassword() {
        if (!jsonObject.containsKey("password"))
            return false;

        String address = jsonObject.get("password").toString().trim();
        return address.length() > 0 && address.length()<=32;
    }

    /**
     * validate the device id field
     * @return true if present and valid format
     */
    protected boolean validateDeviceId() {
        if (!jsonObject.containsKey("id")) return false;

        String id = jsonObject.get("id").toString().trim();
        return (id.length() == 0);
    }

}
