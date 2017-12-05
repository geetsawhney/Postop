package com.postop.exceptions;

import java.util.HashMap;

public class PatientNotFoundException extends Exception{

    HashMap<String, String> response;

    public PatientNotFoundException(String message) {
        super(message);
        this.response = new HashMap<>();
        this.response.put("error", message);
    }

    public HashMap<String, String> getHash(){
        return this.response;
    }
}
