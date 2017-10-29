package com.postop.service;

import com.postop.model.Push;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.postop.model.Patient;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostOpService {

    private final Logger logger = LoggerFactory.getLogger(PostOpService.class);

    public Patient patientLogin(String body){
        JSONParser jsonParser = new JSONParser();
        Patient patient = new Patient();
        try {
            body = body.replaceAll("^\"|\"$", "");
            JSONObject jsonObject = (JSONObject)jsonParser.parse(body);
            String id = jsonObject.get("id").toString();

            if(jsonObject.containsKey("email") && jsonObject.containsKey("ssn")){
                String email = jsonObject.get("email").toString();
                String ssn = jsonObject.get("ssn").toString();

                patient = patient.getPatientByEmail(email);

                if(patient != null){
                    if(patient.getSsn().equals(ssn)){
                        patient.setDeviceId(id);
                        patient.updatePatient();
                        Push p= new Push(id);
                        p.sendPush();
                    }else{
                        logger.error("Entered SSN and email don't match");
                    }
                }else{
                    logger.error("Patient does not exist");
                }
            }else {
                patient = patient.getPatientByDeviceId(id);
                if(patient == null){
                    logger.error("The device_id does not match any patient.");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return patient;
    }

    public void addPatient(String body) {
        JSONParser jsonParser = new JSONParser();
        Patient p = new Patient();
        try {
            JSONObject jsonObject = (JSONObject)jsonParser.parse(body);
            Patient patient = p.setupPatient(jsonObject);
            if(!p.addPatient(patient)){
                logger.error("Failed to add patient");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
