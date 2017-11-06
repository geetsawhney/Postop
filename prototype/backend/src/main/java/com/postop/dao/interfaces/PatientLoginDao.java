package com.postop.dao.interfaces;

import com.postop.exceptions.IllegalSqlException;

public interface PatientLoginDao {
    public boolean validatePatient(String email, String password) throws IllegalSqlException;
}
