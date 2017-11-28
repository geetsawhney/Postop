package com.postop.dao.interfaces;

import com.postop.exceptions.IllegalSqlException;
import com.postop.model.Callback;
import org.json.simple.JSONObject;

import java.util.List;

public interface CallbackDao {
    boolean checkCallbackExists(String email) throws IllegalSqlException;
    boolean updateCallback(String email, JSONObject jsonObject) throws IllegalSqlException;
    boolean addCallback(String email, JSONObject jsonObject) throws IllegalSqlException;
    List<Callback> getAllCallbacks() throws IllegalSqlException;
    Callback getCallback(String email) throws IllegalSqlException;
    boolean deleteCallback(String email);
}
