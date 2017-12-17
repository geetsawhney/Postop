package com.postop.dao.interfaces;

import com.postop.exceptions.IllegalSqlException;
import org.json.simple.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public interface PatientLoginDao {
    boolean validatePatient(String email, String password) throws SQLException;
    boolean deletePatient(String email) throws SQLException;
    boolean addPatient(JSONObject jsonObject) throws IllegalSqlException, SQLException, NoSuchAlgorithmException;
}
