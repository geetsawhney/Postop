package com.postop.service;


import com.postop.dao.*;
import com.postop.dao.interfaces.*;
import com.postop.exceptions.CallbackNotFoundException;
import com.postop.exceptions.IllegalJsonException;
import com.postop.exceptions.IllegalSqlException;
import com.postop.exceptions.PatientNotFoundException;
import com.postop.helper.CallbackLogic;
import com.postop.helper.NotificationLogic;
import com.postop.model.*;
import com.postop.utils.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * This class services the calls from the controller.
 *@author Geet Sawhney, Rohit Aakash
 */
public class PostOpService {

    private final Logger logger = LoggerFactory.getLogger(PostOpService.class);

    /**
     * to facilitate the patient login into the app
     * @param body : json of patient in string format
     * @return patient on valid login
     * @throws IllegalJsonException json in invalid format
     * @throws PatientNotFoundException patient does not exists
     * @throws SQLException: Sql exception
     */
    public Patient patientLogin(String body) throws IllegalJsonException, PatientNotFoundException, SQLException {
        JSONParser jsonParser = new JSONParser();
        Patient patient = null;
        body = body.replaceAll("^\"|\"$", "");

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
            String id = jsonObject.get("id").toString();

            if (jsonObject.containsKey("email") && jsonObject.containsKey("password")) {
                String email = jsonObject.get("email").toString().toLowerCase();
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

        } catch (ParseException e) {
            logger.error("Illegal JSON");
            throw new IllegalJsonException(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            logger.error("No such hash algorithm");
            e.printStackTrace();
        }
        return patient;
    }

    /**
     * Provides service to update a patient
     * @param email : email of the patient
     * @param body : json body of the patient in string format
     * @return true if updated successfully
     * @throws IllegalJsonException :
     * @throws PatientNotFoundException :
     */
    public boolean updatePatient(String email, String body) throws IllegalJsonException, PatientNotFoundException {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
            if (!new UpdatePatientJsonValidation(jsonObject).validateJson()) {
                throw new IllegalJsonException("invalid values in one of the field");
            }

            if (!email.toLowerCase().equals(jsonObject.get("email").toString().toLowerCase()))
                throw new IllegalJsonException("email in parameter and body does not match");

            PatientDao pdi = new PatientDaoImpl();
            Patient patient = Patient.setupPatient(jsonObject);
            pdi.updatePatient(email, patient);

        } catch (ParseException e) {
            logger.error("Illegal JSON error");
            throw new IllegalJsonException(e.getMessage());

        }
        return true;
    }


    /**
     * Add a patient
     * @param body : json of a patient as string
     * @return true if successful
     * @throws IllegalJsonException :
     * @throws IllegalSqlException :
     * @throws UnsupportedEncodingException :
     * @throws SQLException  :
     * @throws NoSuchAlgorithmException :
     */
    public boolean addPatient(String body) throws IllegalJsonException, IllegalSqlException, UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject patientJsonObject = (JSONObject) jsonParser.parse(body);
            if (!new CreatePatientJsonValidation(patientJsonObject).validateJson()) {
                throw new IllegalJsonException("invalid values in one of the field");
            }
            PatientDaoImpl pdi = new PatientDaoImpl();
            pdi.addPatient(patientJsonObject);

            PatientLoginDao pldi = new PatientLoginDaoImpl();
            pldi.addPatient(patientJsonObject);

            String email = patientJsonObject.get("email").toString();
            String password = patientJsonObject.get("password").toString();
            String name = patientJsonObject.get("name").toString();

            if(!email.equals("oosegroup19test@gmail.com"))
                new MailUtil(email, password, name).sendEmail();

        } catch (ParseException e) {
            logger.error("Illegal JSON");
            throw new IllegalJsonException(e.getMessage());
        }
        return true;
    }


    /**
     * add fitness data
     * @param body :json body of the patient in string format
     * @return JSON object of the fitness data
     * @throws IllegalJsonException
     * @throws PatientNotFoundException
     * @throws SQLException
     */
    public JSONObject addFitnessData(String body) throws IllegalJsonException, PatientNotFoundException, SQLException {
        body = body.replaceAll("^\"|\"$", "");
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
            if (!new FitnessDataJsonValidation(jsonObject).validateJson()) {
                throw new IllegalJsonException("invalid values in one of the field");
            }

            PatientDaoImpl pdi = new PatientDaoImpl();
            Patient patient = pdi.getPatientByDeviceId(jsonObject.get("id").toString());

            jsonObject.put("email", patient.getEmail());
            jsonObject.remove("id");

            FitnessHistoryDao fhdi = new FitnessHistoryDaoImpl();
            fhdi.addFitnessData(jsonObject);

            NotificationLogic notificationLogic = new NotificationLogic(patient, fhdi.getFitnessDataByEmail(jsonObject.get("email").toString().toLowerCase()));

            JSONObject output = new JSONObject();
            output.put("notificationCount", notificationLogic.getNumberOfNotifications());

            return output;

        } catch (ParseException e) {
            logger.error("Illegal JSON");
            throw new IllegalJsonException(e.getMessage());
        }
    }

    /**
     * To get specific patient
     * @param email : email of the patient
     * @return Patient object requested
     * @throws PatientNotFoundException :
     */
    public Patient getPatient(String email) throws PatientNotFoundException {
        PatientDao pdi = new PatientDaoImpl();
        return pdi.getPatientByEmail(email);
    }

    /**
     * Returns a list of all patient
     * @return  list of all patient
     */
    public List<Patient> getAllPatients() {
        PatientDao pdi = new PatientDaoImpl();
        return pdi.getAllPatients();
    }


    /**
     *
     * @param email : email of patient
     * @param body : json body of the patient in string format
     * @return true if updates callback successfully
     * @throws IllegalJsonException :
     * @throws PatientNotFoundException :
     */
    public boolean updateCallback(String email, String body) throws IllegalJsonException, PatientNotFoundException {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
            if (!new CallbackJsonValidation(jsonObject).validateJson()) {
                throw new IllegalJsonException("invalid values in one of the field");
            }
            CallbackDao cd = new CallbackDaoImpl();

            if (!email.toLowerCase().equals(jsonObject.get("email").toString().toLowerCase()))
                throw new IllegalJsonException("email in parameter and body does not match");

            if (new PatientDaoImpl().checkPatientExist(email)) {
                if (jsonObject.containsKey("hasPain")) {
                    jsonObject.put("severity", new CallbackLogic(jsonObject).getSeverity());
                }
                if (cd.checkCallbackExists(email)) {
                    cd.updateCallback(email, jsonObject);
                } else {
                    jsonObject.put("isResolved", false);
                    cd.addCallback(email, jsonObject);
                }
            } else {
                throw new PatientNotFoundException("Patient does not exist");
            }

        } catch (ParseException e) {
            throw new IllegalJsonException("Illegal JSON found");
        }
        return true;
    }

    /**
     * Get list of all callbacks not resolved
     * @return list of all callbacks not resolved
     */
    public List<JSONObject> getAllCallbacks() {
        CallbackDao cd = new CallbackDaoImpl();
        return cd.getAllCallbacks();
    }


    /**
     * @param id : device id of patient's device
     * @return true if successful
     * @throws PatientNotFoundException :
     * @throws IOException :
     */
    public boolean sendPush(String id) throws PatientNotFoundException, IOException {
        PatientDaoImpl pdi = new PatientDaoImpl();
        Patient patient = pdi.getPatientByDeviceId(id);
        Push.sendPush(patient);
        return true;
    }

    /**
     * Get a specific callback using email of a patient
     * @param email: email of a patient
     * @return get a specific callback
     * @throws PatientNotFoundException :
     * @throws CallbackNotFoundException :
     * @throws SQLException :
     */
    public Callback getCallback(String email) throws PatientNotFoundException, CallbackNotFoundException, SQLException {
        CallbackDao cd = new CallbackDaoImpl();
        PatientDao pdi = new PatientDaoImpl();
        if (!pdi.checkPatientExist(email)) {
            throw new PatientNotFoundException("Patient does not exist");
        }
        if (!cd.checkCallbackExists(email))
            throw new CallbackNotFoundException("Callback for patient does not exist");
        return cd.getCallback(email);
    }

    /**
     * Get all notification labels and their values
     * @return liest of all notification count
     * @throws SQLException :
     */
    public List<Notification> getNotifications() throws SQLException {
        NotificationDao nd = new NotificationDaoImpl();
        return nd.getNotifications();
    }

    /**
     * update a specific notification
     * @param body : json body of the notification in string format
     * @return true if successful
     * @throws SQLException :
     * @throws IllegalJsonException :
     */
    public boolean updateNotification(String body) throws SQLException, IllegalJsonException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) jsonParser.parse(body);

            if (!new NotificationJsonValidation(jsonObject).validateJson()) {
                throw new IllegalJsonException("invalid method in one of the fields");
            }
        } catch (ParseException e) {
            throw new IllegalJsonException(e.getMessage());
        }

        NotificationDao nd = new NotificationDaoImpl();
        return nd.updateNotification(jsonObject);
    }
}