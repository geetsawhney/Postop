package com.postop.service;

import com.postop.dao.CallbackDaoImpl;
import com.postop.dao.FitnessHistoryDaoImpl;
import com.postop.dao.PatientDaoImpl;
import com.postop.dao.PatientLoginDaoImpl;
import com.postop.dao.interfaces.CallbackDao;
import com.postop.dao.interfaces.PatientDao;
import com.postop.dao.interfaces.PatientLoginDao;
import com.postop.exceptions.IllegalJsonException;
import com.postop.exceptions.IllegalSqlException;
import com.postop.exceptions.InvalidHashAlgorithmException;
import com.postop.exceptions.PatientNotFoundException;
import com.postop.helper.NotificationLogic;
import com.postop.model.Callback;
import com.postop.model.FitnessHistory;
import com.postop.model.Patient;
import com.postop.utils.HashGenerator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.util.List;

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

                    NotificationLogic notificationLogic = new NotificationLogic(patient, new FitnessHistory());
                    logger.info("" + notificationLogic.getNumberOfNotifications());
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

    public void updatePatient(String email, String body) throws IllegalSqlException, IllegalJsonException, PatientNotFoundException {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
            PatientDao pdi = new PatientDaoImpl();
            Patient patient = Patient.setupPatient(jsonObject);
            pdi.updatePatient(email, patient);

        } catch (ParseException e) {
            logger.error("Illegal JSON error");
            throw new IllegalJsonException(e.getMessage());

        }
    }


    public void addPatient(String body) throws IllegalJsonException, IllegalSqlException, InvalidHashAlgorithmException {
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject patientJsonObject = (JSONObject) jsonParser.parse(body);
            PatientDaoImpl pdi = new PatientDaoImpl();
            pdi.addPatient(patientJsonObject);

            PatientLoginDao pldi = new PatientLoginDaoImpl();
            pldi.addPatient(patientJsonObject);

        } catch (ParseException e) {
            logger.error("Illegal JSON");
            throw new IllegalJsonException(e.getMessage());
        }
    }


    public JSONObject addFitnessData(String body) throws IllegalJsonException, IllegalSqlException, InvalidHashAlgorithmException, PatientNotFoundException {
        body = body.replaceAll("^\"|\"$", "");
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
            PatientDaoImpl pdi = new PatientDaoImpl();
            Patient patient = pdi.getPatientByDeviceId(jsonObject.get("id").toString());

            jsonObject.put("email", patient.getEmail());
            jsonObject.remove("id");

            FitnessHistoryDaoImpl fhdi = new FitnessHistoryDaoImpl();
            fhdi.addFitnessData(jsonObject);

            NotificationLogic notificationLogic = new NotificationLogic(patient, fhdi.getFitnessDataByEmail(jsonObject.get("email").toString()));

            JSONObject output = new JSONObject();
            output.put("notificationCount", notificationLogic.getNumberOfNotifications());

            return output;

        } catch (ParseException e) {
            logger.error("Illegal JSON");
            throw new IllegalJsonException(e.getMessage());
        }
    }

    public Patient getPatient(String email) throws IllegalSqlException, PatientNotFoundException {
        PatientDao pdi = new PatientDaoImpl();
        return pdi.getPatientByEmail(email);
    }

    public List<Patient> getAllPatients() throws IllegalSqlException {
        PatientDao pdi = new PatientDaoImpl();
        return pdi.getAllPatients();
    }


    public void updateCallback(String email, String body) throws IllegalSqlException, IllegalJsonException {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
            CallbackDao cd = new CallbackDaoImpl();

            if (cd.checkPreviousCallbackExists(email)) {
                cd.updateCallback(email,jsonObject);
            } else {
                cd.addCallback(email,jsonObject);
            }
        } catch (ParseException e) {
            throw new IllegalJsonException("Illegal JSON found");
        }
    }

    public List<Callback> getAllCallbacks() throws IllegalSqlException {
        CallbackDao cd=new CallbackDaoImpl();
        return cd.getAllCallbacks();
    }
}