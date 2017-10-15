package com.postop.dao.interfaces;

import com.postop.model.Patient;

import java.util.List;

public interface PatientDao {
    public List<Patient> getAllPatients();
    public Patient getPatientByEmail(String email);
    public boolean updatePatient(Patient patient);
    public boolean addPatient(Patient patient);
    public boolean deletePatient(Patient patient);
    public List<Patient> getPatientByName(String s);
}
