package com.postop.model;

import java.util.Date;

public class Patient{
	private int patientId;
	private String email;
	private String ssn;
	private String address;
	private String name;
	private Date dob;
	private String sex;
	private String hospitalReason;
	private boolean hasPastUTI;
	private boolean hasCatheter;

	public Patient(int patientId, String name, String sex) {
		this.patientId = patientId;
		this.name = name;
		this.sex = sex;
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

	public void setDob(Date dob) {
		this.dob = dob;
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

	public boolean isHasPastUTI() {
		return hasPastUTI;
	}

	public void setHasPastUTI(boolean hasPastUTI) {
		this.hasPastUTI = hasPastUTI;
	}

	public void setHasCatheter(boolean hasCatheter) {
		this.hasCatheter = hasCatheter;
	}

	@Override
	public String toString() {
		return "Patient{" +
				"patientId=" + patientId +
				", email='" + email + '\'' +
				", ssn='" + ssn + '\'' +
				", address='" + address + '\'' +
				", name='" + name + '\'' +
				", dob=" + dob +
				", sex=" + sex +
				", hospitalReason='" + hospitalReason + '\'' +
				", hasPastUTI=" + hasPastUTI +
				", hasCatheter=" + hasCatheter +
				'}';
	}
}