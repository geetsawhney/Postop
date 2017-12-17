package com.postop.dao.interfaces;

import com.postop.exceptions.IllegalSqlException;
import com.postop.model.FitnessHistory;
import org.json.simple.JSONObject;

import java.sql.SQLException;

public interface FitnessHistoryDao {
    boolean addFitnessData(JSONObject fitnessData) throws SQLException;
    FitnessHistory getFitnessDataByEmail(String email) throws SQLException;
    boolean deleteFitnessData(FitnessHistory fitnessHistory) throws SQLException;
}
