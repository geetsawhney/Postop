package com.postop.exceptions;

import java.util.HashMap;

public class InvalidHashAlgorithmException extends Exception{
    HashMap<String, String> response;

    public InvalidHashAlgorithmException(String message) {
        super(message);
        this.response = new HashMap<>();
        this.response.put("error", message);
    }

    public HashMap<String, String> getHash(){
        return this.response;
    }
}
