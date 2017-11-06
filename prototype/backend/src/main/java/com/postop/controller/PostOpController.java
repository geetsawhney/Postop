package com.postop.controller;


import com.postop.exceptions.IllegalJsonException;
import com.postop.exceptions.IllegalSqlException;
import com.postop.exceptions.InvalidHashAlgorithmException;
import com.postop.exceptions.PatientNotFoundException;
import com.postop.model.Patient;
import com.postop.service.PostOpService;
import com.postop.utils.JsonTransformer;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;

import static spark.Spark.post;

public class PostOpController {

    private static final String API_CONTEXT = "/api/v1";
    private final PostOpService postOpService;
    private final Logger logger = LoggerFactory.getLogger(PostOpController.class);

    public PostOpController(PostOpService postOpService) {
        this.postOpService = postOpService;
        setupEndpoints();
    }

    private void setupEndpoints() {

        /* Implements patient login */
        post(API_CONTEXT + "/patient/login", "application/json", (request, response) -> {
            try {
                Patient patient = postOpService.patientLogin(request.body());
                response.status(200);
                return patient;
            } catch (IllegalJsonException ex) {
                response.status(400);
                return ex.getHash();
            } catch (PatientNotFoundException ex){
                response.status(404);
                return ex.getHash();
            } catch (IllegalSqlException ex){
                response.status(500);
                return ex.getHash();
            } catch(InvalidHashAlgorithmException ex){
                response.status(500);
                return ex.getHash();
            }
        }, new JsonTransformer());

        /* Implements create new patient */
        post(API_CONTEXT + "/patient/add", "application/json", (request, response) -> {
            try {
                postOpService.addPatient(request.body());
                response.status(200);
            } catch (Exception ex) {
                logger.error("Failed to create patient");
                response.status(500);
                return Collections.EMPTY_MAP;
            }
            return Collections.EMPTY_MAP;
        }, new JsonTransformer());

    }


}
