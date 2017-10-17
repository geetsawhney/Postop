package com.postop.controller;


import com.postop.JsonTransformer;
import com.postop.service.PostOpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import static spark.Spark.post;
import static spark.Spark.put;

public class PostOpController {

    private static final String API_CONTEXT = "/api/v1";
    private final PostOpService postOpService;
    private final Logger logger = LoggerFactory.getLogger(PostOpController.class);

    public PostOpController(PostOpService postOpService) {
        logger.info("inside postop controller");
        this.postOpService = postOpService;
        setupEndpoints();
    }

    private void setupEndpoints() {
        logger.info("inside endpoints");

        post(API_CONTEXT + "/patient/login", "application/json", (request, response)-> {
            try {
                response.status(200);
                return postOpService.patientLogin(request.body());
            } catch  (Exception ex) {
                logger.error("Failed to create patient");
                response.status(500);
                return Collections.EMPTY_MAP;
            }
        }, new JsonTransformer());
    }
}
