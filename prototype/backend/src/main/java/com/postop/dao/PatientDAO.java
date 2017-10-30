package com.postop.dao;

import com.postop.model.Patient;

import java.util.List;

interface PatientDao {
    public List<Patient> getAllPatients();
    public Patient getPatientById(int patientId);
    public void updatePatient(Patient patient);
    public void addPatient(Patient patient);
    public List<Patient> getPatientByName(String s);
}
