package com.postop.model;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Date;

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

    public Patient() {

    }

    public static Patient setupPatient(JSONObject jsonObject) {
        Patient patient = new Patient();

        patient.setEmail(jsonObject.get("email").toString().trim());
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

    public boolean getCatheterUsage() {
        return catheterUsage;
    }

    public void setCatheterUsage(boolean catheterUsage) {
        this.catheterUsage = catheterUsage;
    }

    public void setLastVisitDate(Date lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public Date getLastVisitDate() {
        return lastVisitDate;
    }

//    public void setLastVisitDate(String lastVisitDate) {
//        this.lastVisitDate= Date.valueOf(lastVisitDate);
//    }

//    public String getLastVisitDateString() {
//        return new SimpleDateFormat("yyyy-MM-dd").format(lastVisitDate);
//    }

    public int getUtiVisitCount() {
        return utiVisitCount;
    }

    public void setUtiVisitCount(int utiVisitCount) {
        this.utiVisitCount = utiVisitCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


//    public Patient(String email, String name, String sex, String ssn) {
//        this.email = email;
//        this.name = name;
//        this.sex = sex;
//        this.ssn = ssn;
//    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

//    public void setDob(String dob) {
//        this.dob=Date.valueOf(dob);
//    }

//    public String getDobString() {
//        return new SimpleDateFormat("yyyy-MM-dd").format(dob);
//    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHospitalVisitReason() {
        return hospitalVisitReason;
    }

    public void setHospitalVisitReason(String hospitalVisitReason) {
        this.hospitalVisitReason = hospitalVisitReason;
    }

    public boolean getDiabetic() {
        return diabetic;
    }

    public void setDiabetic(boolean diabetic) {
        this.diabetic = diabetic;
    }
}