package com.postop.dao.interfaces;

import com.postop.exceptions.IllegalSqlException;
import com.postop.exceptions.PatientNotFoundException;
import com.postop.model.Patient;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.List;

public interface PatientDao {
    List<Patient> getAllPatients();
    Patient getPatientByEmail(String email) throws  PatientNotFoundException;
    public Patient getPatientByDeviceId(String id) throws  PatientNotFoundException;
    public void updatePatientDeviceId(Patient patient) ;
    public void addPatient(JSONObject patient) throws IllegalSqlException;
    public boolean deletePatient(String email);
//    public List<Patient> getPatientByName(String s);
    public void updatePatient(String email,Patient patient) throws  PatientNotFoundException;
    public boolean checkPatientExist(String email) throws IllegalSqlException;

}
