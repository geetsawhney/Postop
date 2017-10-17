package com.postop.service;

import com.postop.utils.DbConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.postop.model.Patient;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
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

    public JSONObject patientLogin(String body){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        Patient patient = new Patient();
        logger.info("Inside patientLogin");
        try {
            jsonObject = (JSONObject)jsonParser.parse(body);
            String id = jsonObject.get("id").toString();
            logger.info("Inside try");

            if(jsonObject.containsKey("email") && jsonObject.containsKey("ssn")){
                String email = jsonObject.get("email").toString();
                String ssn = jsonObject.get("ssn").toString();
                logger.info(ssn + " " + email + " " + id +" ");

                patient = patient.getPatientByEmail(email);
                logger.info("Done executing getPatient in service");
                if(patient.getSsn().equals(ssn)){
                    patient.setDeviceId(id);
                    patient.updatePatient();
                }
            }else {
                logger.info( id +" ");
                patient = patient.getPatientByDeviceId(id);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject obj = new JSONObject();
        obj.put("email", patient.getEmail());
        obj.put("ssn", patient.getSsn());
        obj.put("address", patient.getAddress());
        return obj;
    }

    public static class PostOpServiceException extends Exception {
        public PostOpServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
