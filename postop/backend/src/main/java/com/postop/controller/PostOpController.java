package com.postop.controller;


import com.postop.exceptions.CallbackNotFoundException;
import com.postop.exceptions.IllegalJsonException;
import com.postop.exceptions.IllegalSqlException;
import com.postop.exceptions.PatientNotFoundException;
import com.postop.model.Callback;
import com.postop.model.Notification;
import com.postop.model.Patient;
import com.postop.service.PostOpService;
import com.postop.utils.JsonTransformer;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static spark.Spark.*;

/**
 *  This class consits of all the end points.
 */
public class PostOpController {

    private static final String API_CONTEXT = "/api/v1";
    private final PostOpService postOpService;
    private final Logger logger = LoggerFactory.getLogger(PostOpController.class);

    /**
     * @param postOpService
     */
    public PostOpController(PostOpService postOpService) {
        this.postOpService = postOpService;
        setupEndpoints();
    }

    /**
     *
     */
    private void setupEndpoints() {

        options("*", (request, response) -> {
            response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
            response.status(200);
            return Collections.EMPTY_MAP;
        });

        /* Implements patient login */
        post(API_CONTEXT + "/patient/login", "application/json", (request, response) -> {
            try {
                Patient patient = postOpService.patientLogin(request.body());
                response.status(200);
                return patient;
            } catch (IllegalJsonException ex) {
                response.status(400);
                return ex.getHash();
            } catch (PatientNotFoundException ex) {
                response.status(404);
                return ex.getHash();
            }
        }, new JsonTransformer());

        /* Implements create new patient */
        post(API_CONTEXT + "/patient", "application/json", (request, response) -> {
            try {
                postOpService.addPatient(request.body());
                response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
                response.header("Access-Control-Allow-Origin", "*");
                response.type("application/json");
                response.status(200);
                HashMap<String, String> output = new HashMap<>();
                output.put("message", "SUCCESS");
                return output;
            } catch (IllegalSqlException ex) {
                response.status(500);
                return ex.getHash();
            } catch (IllegalJsonException ex) {
                response.status(400);
                return ex.getHash();
            }
        }, new JsonTransformer());

        /*Implements update the patient*/
        put(API_CONTEXT + "/patient/:email", "application/json", (request, response) -> {
            String email = request.params(":email");
            try {
                postOpService.updatePatient(email, request.body());
                response.status(200);
                HashMap<String, String> output = new HashMap<>();
                output.put("message", "SUCCESS");
                return output;
            } catch (IllegalJsonException ex) {
                response.status(400);
                return ex.getHash();
            } catch (PatientNotFoundException ex) {
                response.status(404);
                return ex.getHash();
            }
        }, new JsonTransformer());

        /*Implements get a patient */
        get(API_CONTEXT + "/patient/:email", "application/json", (request, response) -> {
            String email = request.params(":email");
            try {
                Patient patient = postOpService.getPatient(email);
                response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
                response.header("Access-Control-Allow-Origin", "*");
                response.type("application/json");
                logger.info(patient.getDob().toString());
                response.status(200);
                return patient;
            } catch (PatientNotFoundException ex) {
                response.status(404);
                return ex.getHash();
            }
        }, new JsonTransformer());


        /* Implements get a list of all patient */
        get(API_CONTEXT + "/patients", "application/json", (request, response) -> {
            List<Patient> patients = postOpService.getAllPatients();
            response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
            response.header("Access-Control-Allow-Origin", "*");
            response.type("application/json");
            response.status(200);
            return patients;
        }, new JsonTransformer());



        /* Implements updating or adding a callback for a patient */
        put(API_CONTEXT + "/patient/:email/callback", "application/json", (request, response) -> {
            String email = request.params(":email");
            try {
                postOpService.updateCallback(email, request.body());
                response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
                response.header("Access-Control-Allow-Origin", "*");
                response.type("application/json");
                response.status(200);
                HashMap<String, String> output = new HashMap<>();
                output.put("message", "SUCCESS");
                return output;
            } catch (IllegalJsonException ex) {
                response.status(400);
                return ex.getHash();
            } catch (PatientNotFoundException ex) {
                response.status(404);
                return ex.getHash();
            }
        }, new JsonTransformer());


        /*Implements get a callback*/
        get(API_CONTEXT + "/patient/:email/callback", "application/json", (request, response) -> {
            try {
                String email = request.params(":email");
                Callback callback = postOpService.getCallback(email);
                response.status(200);
                return callback;
            } catch (CallbackNotFoundException ex) {
                response.status(404);
                return ex.getHash();
            } catch (PatientNotFoundException ex) {
                response.status(404);
                return ex.getHash();
            }
        }, new JsonTransformer());


        /* Implements get a list of all callbacks */
        get(API_CONTEXT + "/patients/callbacks", "application/json", (request, response) -> {
            List<JSONObject> callbacks = postOpService.getAllCallbacks();
            response.header("Access-Control-Allow-Origin", "*");
            response.type("application/json");
            response.status(200);
            return callbacks;
        }, new JsonTransformer());


        /* Implements adding daily fitness data and returning the number of notifications */
        post(API_CONTEXT + "/patient/gfit", "application/json", (request, response) -> {
            try {
                JSONObject jsonObject = postOpService.addFitnessData(request.body());
                response.status(200);
                return jsonObject;
            } catch (IllegalJsonException ex) {
                response.status(400);
                return ex.getHash();
            } catch (PatientNotFoundException ex) {
                response.status(404);
                return ex.getHash();
            }
        }, new JsonTransformer());


        /* Implements sending out a push notification */
        get(API_CONTEXT + "/patient/:id/push", "application/json", (request, response) -> {
            try {
                postOpService.sendPush(request.params("id"));
                response.status(200);
                HashMap<String, String> output = new HashMap<>();
                output.put("message", "SUCCESS");
                return output;
            } catch (PatientNotFoundException ex) {
                response.status(404);
                return ex.getHash();
            }
        }, new JsonTransformer());


        // Implements getting a list of number of Notification values for each label.
        get(API_CONTEXT + "/nurse/notification", "application/json", (request, response) -> {
            List<Notification> notifications = postOpService.getNotifications();
            response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
            response.header("Access-Control-Allow-Origin", "*");
            response.type("application/json");
            response.status(200);
            return notifications;

        }, new JsonTransformer());


        //Implements updating the value of number of Notification values by the nurse
        put(API_CONTEXT + "/nurse/notification", "application/json", (request, response) -> {
            postOpService.updateNotification(request.body());
            response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
            response.header("Access-Control-Allow-Origin", "*");
            response.type("application/json");
            response.status(200);
            HashMap<String, String> output = new HashMap<>();
            output.put("message", "SUCCESS");
            return output;
        }, new JsonTransformer());


    }
}