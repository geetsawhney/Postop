package com.postopapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import com.google.gson.Gson;

public class PostOpService {

    private Sql2o db;

    private final Logger logger = LoggerFactory.getLogger(PostOpService.class);

    
 /**
//     * Create a new Todo entry.
//     */
    public void addPatient(String body) throws PostOpServiceException {
    	
        Patient p = new Gson().fromJson(body, Patient.class);
        String sql = "INSERT INTO Patient (email,password) " +
                     "             VALUES (:email, :password)" ;

        try (Connection conn = db.open()) {
            conn.createQuery(sql)
                .bind(p)
                .executeUpdate();
        } catch(Sql2oException ex) {
            logger.error("PostOpService.addPatient: Failed to create new entry", ex);
            throw new PostOpServiceException("PostOpService.addPatient: Failed to create new entry", ex);
        }
    }

    public static class PostOpServiceException extends Exception {
        public PostOpServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
