package com.postop.exceptions;

import java.util.HashMap;

public class IllegalJsonException extends Exception{

    HashMap<String, String> response;

    public IllegalJsonException(String message) {
        super(message);
        this.response = new HashMap<>();
        this.response.put("error", message);
    }

    public HashMap<String, String> getHash(){
        return this.response;
    }
}
