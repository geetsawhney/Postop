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
    Patient getPatientByDeviceId(String id) throws  PatientNotFoundException;
    boolean updatePatientDeviceId(Patient patient) ;
    boolean addPatient(JSONObject patient) throws IllegalSqlException;
    boolean deletePatient(String email);
    boolean updatePatient(String email,Patient patient) throws  PatientNotFoundException;
    boolean checkPatientExist(String email) throws IllegalSqlException;

}
