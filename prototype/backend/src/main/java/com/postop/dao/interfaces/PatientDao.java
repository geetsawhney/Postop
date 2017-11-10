package com.postop.dao.interfaces;

import com.postop.exceptions.IllegalSqlException;
import com.postop.exceptions.PatientNotFoundException;
import com.postop.model.Patient;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.List;

public interface PatientDao {
    public List<Patient> getAllPatients() throws IllegalSqlException;
    public Patient getPatientByEmail(String email) throws IllegalSqlException, PatientNotFoundException;
    public Patient getPatientByDeviceId(String id) throws IllegalSqlException, PatientNotFoundException;
    public void updatePatientDeviceId(Patient patient) throws IllegalSqlException;
    public void addPatient(JSONObject patient) throws IllegalSqlException;
    public boolean deletePatient(Patient patient);
    public List<Patient> getPatientByName(String s);
    public void updatePatient(Patient patient) throws IllegalSqlException, PatientNotFoundException;
    public boolean checkPatientExist(String email) throws SQLException, IllegalSqlException;
}
