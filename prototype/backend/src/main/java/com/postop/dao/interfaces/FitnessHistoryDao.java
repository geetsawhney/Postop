package com.postop.dao.interfaces;

import com.postop.exceptions.IllegalSqlException;
import com.postop.model.FitnessHistory;
import org.json.simple.JSONObject;

public interface FitnessHistoryDao {
    boolean addFitnessData(JSONObject fitnessData);
    FitnessHistory getFitnessDataByEmail(String email) ;
    boolean deleteFitnessData(FitnessHistory fitnessHistory);
}
