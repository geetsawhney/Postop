package com.postop.model;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Date;

/**
 *
 */
public class Patient {
    private final Logger logger = LoggerFactory.getLogger(Patient.class);
    private String email;
    private String ssn;
    private String deviceId;
    private String name;
    private String sex;
    private Date dob;
    private String address;
    private String phone;
    private String hospitalVisitReason;
    private int utiVisitCount;
    private boolean catheterUsage;
    private boolean diabetic;
    private Date lastVisitDate;


    /**
     * @param email
     * @param ssn
     * @param deviceId
     * @param name
     * @param sex
     * @param dob
     * @param address
     * @param phone
     * @param hospitalVisitReason
     * @param utiVisitCount
     * @param catheterUsage
     * @param diabetic
     * @param lastVisitDate
     */
    public Patient(String email, String ssn, String deviceId, String name, String sex, Date dob, String address, String phone, String hospitalVisitReason, int utiVisitCount, boolean catheterUsage, boolean diabetic, Date lastVisitDate) {
        this.email = email;
        this.ssn = ssn;
        this.deviceId = deviceId;
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.hospitalVisitReason = hospitalVisitReason;
        this.utiVisitCount = utiVisitCount;
        this.catheterUsage = catheterUsage;
        this.diabetic = diabetic;
        this.lastVisitDate = lastVisitDate;
    }

    /**
     *
     */
    public Patient() {

    }

    /**
     * @param jsonObject
     * @return
     */
    public static Patient setupPatient(JSONObject jsonObject) {
        Patient patient = new Patient();

        patient.setEmail(jsonObject.get("email").toString().trim().toLowerCase());
        patient.setSsn(jsonObject.get("ssn").toString().trim());
        patient.setDeviceId(jsonObject.get("id").toString().trim());
        patient.setName(jsonObject.get("name").toString().trim());
        patient.setSex(jsonObject.get("sex").toString().trim());
        patient.setDob(Date.valueOf(jsonObject.get("dob").toString().trim()));
        patient.setAddress(jsonObject.get("address").toString().trim());
        patient.setPhone(jsonObject.get("phone").toString().trim());
        patient.setHospitalVisitReason(jsonObject.get("hospitalVisitReason").toString().trim());
        patient.setUtiVisitCount(Integer.parseInt(jsonObject.get("utiVisitCount").toString().trim()));
        patient.setCatheterUsage(Boolean.parseBoolean(jsonObject.get("catheterUsage").toString().trim()));
        patient.setDiabetic(Boolean.parseBoolean(jsonObject.get("diabetic").toString().trim()));
        patient.setLastVisitDate(Date.valueOf(jsonObject.get("lastVisitDate").toString().trim()));

        return patient;
    }

    /**
     * @return
     */
    public boolean getCatheterUsage() {
        return catheterUsage;
    }

    /**
     * @param catheterUsage
     */
    public void setCatheterUsage(boolean catheterUsage) {
        this.catheterUsage = catheterUsage;
    }

    /**
     * @param lastVisitDate
     */
    public void setLastVisitDate(Date lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    /**
     * @return
     */
    public Date getLastVisitDate() {
        return lastVisitDate;
    }


    /**
     * @return
     */
    public int getUtiVisitCount() {
        return utiVisitCount;
    }

    /**
     * @param utiVisitCount
     */
    public void setUtiVisitCount(int utiVisitCount) {
        this.utiVisitCount = utiVisitCount;
    }

    /**
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @param dob
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * @param ssn
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @return
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return
     */
    public String getHospitalVisitReason() {
        return hospitalVisitReason;
    }

    /**
     * @param hospitalVisitReason
     */
    public void setHospitalVisitReason(String hospitalVisitReason) {
        this.hospitalVisitReason = hospitalVisitReason;
    }

    /**
     * @return
     */
    public boolean getDiabetic() {
        return diabetic;
    }

    /**
     * @param diabetic
     */
    public void setDiabetic(boolean diabetic) {
        this.diabetic = diabetic;
    }
}