package com.postop.dao;

public interface CallbacksDAO {
    public List<Callback> getAllCallbacks();
    public List<Callback> getCallbacksByPatient(int patientId);
    public Callback getCallback(int callbackId);

}
