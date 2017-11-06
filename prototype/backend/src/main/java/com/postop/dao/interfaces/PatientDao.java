package com.postop.dao.interfaces;

import com.postop.exceptions.IllegalSqlException;
import com.postop.model.Patient;

import java.util.List;

public interface PatientDao {
    public List<Patient> getAllPatients();
    public Patient getPatientByEmail(String email) throws IllegalSqlException;
    public Patient getPatientByDeviceId(String id) throws IllegalSqlException;
    public boolean updatePatientDeviceId(Patient patient) throws IllegalSqlException;
    public boolean addPatient(Patient patient);
    public boolean deletePatient(Patient patient);
    public List<Patient> getPatientByName(String s);
}
