package com.postop.dao.interfaces;

import com.postop.exceptions.IllegalSqlException;
import org.json.simple.JSONObject;


public interface PatientLoginDao {
    boolean validatePatient(String email, String password) ;
    boolean deletePatient(String email);
    boolean addPatient(JSONObject jsonObject) throws  IllegalSqlException;
}
