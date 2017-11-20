package com.postop.dao.interfaces;

import com.postop.exceptions.IllegalSqlException;
import com.postop.exceptions.InvalidHashAlgorithmException;
import org.json.simple.JSONObject;

import java.security.NoSuchAlgorithmException;

public interface PatientLoginDao {
    public boolean validatePatient(String email, String password) throws IllegalSqlException;

    public void addPatient(JSONObject jsonObject) throws InvalidHashAlgorithmException, IllegalSqlException;
}
