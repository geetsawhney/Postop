package com.postop.exceptions;

import java.util.HashMap;

/**
 *
 */
public class IllegalSqlException extends Exception {
    HashMap<String, String> response;

    public IllegalSqlException(String message) {
        super(message);
        this.response = new HashMap<>();
        this.response.put("error", message);
    }

    /**
     * @return
     */
    public HashMap<String, String> getHash(){
        return this.response;
    }
}
