package com.postop.exceptions;

import java.util.HashMap;

/**
 *
 */
public class CallbackNotFoundException extends Throwable {
    HashMap<String, String> response;

    /**
     * @param message
     */
    public CallbackNotFoundException (String message) {
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
