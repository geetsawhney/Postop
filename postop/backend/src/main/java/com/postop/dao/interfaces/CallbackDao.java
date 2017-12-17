package com.postop.dao.interfaces;

import com.postop.model.Callback;
import org.json.simple.JSONObject;

import java.util.List;

/**
 *
 */
public interface CallbackDao {
    boolean checkCallbackExists(String email) ;
    boolean updateCallback(String email, JSONObject jsonObject);
    boolean addCallback(String email, JSONObject jsonObject);
    List<JSONObject> getAllCallbacks() ;
    Callback getCallback(String email) ;
    boolean deleteCallback(String email);
}
