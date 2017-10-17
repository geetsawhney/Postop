package com.postop.model;

import com.postop.controller.PostOpController;
import com.postop.dao.PatientDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient{
	private int patientId;
	private String email;
	private String ssn;
	private String address;
	private String name;
	private String phone;
    private boolean diabetes;
    private Date dob;
	private String sex;
	private String hospitalReason;
	private int utiVisitCount;
	private Date lastVisitDate;

    private final Logger logger = LoggerFactory.getLogger(Patient.class);


    public Date getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(String lastVisitDate) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
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

    private boolean hasCatheter;

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

    private String deviceId;

	public Patient(String email, String name, String sex, String ssn) {
		this.email = email;
		this.name = name;
		this.sex = sex;
		this.ssn = ssn;
	}

	public Patient(){

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isHasCatheter() {
		return hasCatheter;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
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

	public void setDob(String dob) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
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

	public String getHospitalReason() {
		return hospitalReason;
	}

	public void setHospitalReason(String hospitalReason) {
		this.hospitalReason = hospitalReason;
	}

	public void setHasCatheter(boolean hasCatheter) {
		this.hasCatheter = hasCatheter;
	}

    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }



	public Patient getPatientByEmail(String email){
		logger.info("Inside Patient getPatientByEmail");
        PatientDaoImpl pdi = new PatientDaoImpl();
        return pdi.getPatientByEmail(email);
	}

	public boolean updatePatient(){
        PatientDaoImpl pdi = new PatientDaoImpl();
	    return pdi.updatePatient(this);
    }

    public Patient getPatientByDeviceId(String id){
        PatientDaoImpl pdi = new PatientDaoImpl();
        return pdi.getPatientByDeviceId(id);
    }
}