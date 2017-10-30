package com.postop.dao.interfaces;

import com.postop.model.Callback;

import java.util.List;

public interface CallbacksDao {
    public List<Callback> getAllCallbacks();
    public List<Callback> getCallbacksByPatient(int patientId);
    public Callback getCallback(int callbackId);

}
