package com.postop.dao.interfaces;

import com.postop.exceptions.IllegalSqlException;
import com.postop.model.FitnessHistory;
import org.json.simple.JSONObject;

public interface FitnessHistoryDao {
    boolean addFitnessData(JSONObject fitnessData) throws IllegalSqlException;
    FitnessHistory getFitnessDataByEmail(String email) throws IllegalSqlException;
    boolean deleteFitnessData(FitnessHistory fitnessHistory);
}
