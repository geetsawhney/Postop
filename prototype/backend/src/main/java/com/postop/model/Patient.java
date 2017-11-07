package com.postop.model;

import com.postop.dao.PatientDaoImpl;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {
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

    private final Logger logger = LoggerFactory.getLogger(Patient.class);

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

    public String getLastVisitDateString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(lastVisitDate);
    }

    public void setLastVisitDate(String lastVisitDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(lastVisitDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.lastVisitDate = date;
    }



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

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


//    public Patient(String email, String name, String sex, String ssn) {
//        this.email = email;
//        this.name = name;
//        this.sex = sex;
//        this.ssn = ssn;
//    }

    public Patient() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public String getDobString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(dob);
    }

    public void setDob(String dob) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(dob);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.dob = date;
    }

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



//    public Patient setupPatient(JSONObject jsonObject){
//        Patient patient = new Patient();
//
//        patient.setEmail(jsonObject.get("email").toString());
//        patient.setSsn(jsonObject.get("ssn").toString());
//        patient.setDeviceId(jsonObject.get("id").toString());
//        patient.setName(jsonObject.get("name").toString());
//        patient.setSex(jsonObject.get("sex").toString());
//        patient.setDob(jsonObject.get("dob").toString());
//        patient.setAddress(jsonObject.get("address").toString());
//        patient.setPhone(jsonObject.get("phone").toString());
//        patient.setHospitalVisitReason(jsonObject.get("hospitalVisitReason").toString());
//        patient.setUtiVisitCount(Integer.parseInt(jsonObject.get("utiVisitCount").toString()));
//        patient.setCatheterUsage(Boolean.parseBoolean(jsonObject.get("catheterUsage").toString()));
//        patient.setDiabetic(Boolean.parseBoolean(jsonObject.get("diabetic").toString()));
//        patient.setLastVisitDate(jsonObject.get("lastVisitDate").toString());
//
//        return patient;
//    }
}