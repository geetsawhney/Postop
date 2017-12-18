package com.postop.exceptions;

import java.util.HashMap;

/**
 * Handles exceptions in cases when a SQL is not valid
 * @author Rohit Aakash, Geet Sawhney
 */
public class IllegalSqlException extends Exception {
    HashMap<String, String> response;

    /**
     * Initializes a new response object and adds the message passed
     * @param message: the message to be displayed
     */
    public IllegalSqlException(String message) {
        super(message);
        this.response = new HashMap<>();
        this.response.put("error", message);
    }

    /**
     * Returns a HashMap fo the response on finding the exception
     * @return
     */
    public HashMap<String, String> getHash(){
        return this.response;
    }
}
