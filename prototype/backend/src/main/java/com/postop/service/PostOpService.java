package com.postop.service;

import com.postop.dao.PatientDaoImpl;
import com.postop.dao.PatientLoginDaoImpl;
import com.postop.dao.interfaces.PatientDao;
import com.postop.dao.interfaces.PatientLoginDao;
import com.postop.exceptions.IllegalJsonException;
import com.postop.exceptions.IllegalSqlException;
import com.postop.exceptions.InvalidHashAlgorithmException;
import com.postop.exceptions.PatientNotFoundException;
import com.postop.model.Patient;
import com.postop.utils.HashGenerator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PostOpService {

    private final Logger logger = LoggerFactory.getLogger(PostOpService.class);

    public Patient patientLogin(String body) throws IllegalJsonException, PatientNotFoundException, IllegalSqlException, InvalidHashAlgorithmException {
        JSONParser jsonParser = new JSONParser();
        Patient patient;

        body = body.replaceAll("^\"|\"$", "");
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
            String id = jsonObject.get("id").toString();

            if (jsonObject.containsKey("email") && jsonObject.containsKey("password")) {
                String email = jsonObject.get("email").toString();
                String password = jsonObject.get("password").toString();

                PatientLoginDao pldi = new PatientLoginDaoImpl();
                if (pldi.validatePatient(email, HashGenerator.generateHash(password))) {
                    PatientDao pdi = new PatientDaoImpl();
                    patient = pdi.getPatientByEmail(email);
                    patient.setDeviceId(id);
                    pdi.updatePatientDeviceId(patient);
                } else {
                    logger.error("Entered password and email don't match");
                    throw new PatientNotFoundException("email and password do not match");
                }
            } else {
                PatientDao pdi = new PatientDaoImpl();
                patient = pdi.getPatientByDeviceId(id);
                if (patient == null) {
                    logger.error("The device_id does not match any patient.");
                    throw new PatientNotFoundException("device_id does not exist");
                }
            }
            return patient;
        } catch (ParseException e) {
            logger.error("Illegal JSON");
            throw new IllegalJsonException(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            logger.error("No such hash algorithm");
            throw new InvalidHashAlgorithmException(e.getMessage());
        }
    }

    public void addPatient(String body) {
        JSONParser jsonParser = new JSONParser();
        Patient p = new Patient();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
            Patient patient = p.setupPatient(jsonObject);
            if (!p.addPatient(patient)) {
                logger.error("Failed to add patient");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
