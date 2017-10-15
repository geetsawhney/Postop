package com.postop.controller;


import com.postop.JsonTransformer;
import com.postop.service.PostOpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.util.Collections;

public class PostOpController {

    private static final String API_CONTEXT = "/api/v1";

    private final PostOpService postOpService;

    private final Logger logger = LoggerFactory.getLogger(PostOpController.class);

    public PostOpController(PostOpService postOpService) {
        this.postOpService = postOpService;
        setupEndpoints();
    }

    private void setupEndpoints() {


        Spark.post(API_CONTEXT + "/patient", "application/json", (request, response)-> {
            try {
                postOpService.addPatient(request.body());
                response.status(201);
            } catch  (Exception ex) {
                logger.error("Failed to fetch the list of todos");
                response.status(500);
                return Collections.EMPTY_MAP;
            }
            return Collections.EMPTY_MAP;
        }, new JsonTransformer());
    }
}
