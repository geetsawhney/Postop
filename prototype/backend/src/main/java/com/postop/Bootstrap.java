package com.postop;

import com.postop.controller.PostOpController;
import com.postop.dao.PatientDaoImpl;
import com.postop.model.Patient;
import com.postop.service.PostOpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

import java.util.List;

public class Bootstrap {
    public static final String IP_ADDRESS = "10.194.124.129";
    public static final int PORT = 8080;

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) throws Exception {

        //Specify the IP address and Port at which the server should be run
        ipAddress(IP_ADDRESS);
        port(PORT);

        //Specify the sub-directory from which to serve static resources (like html and css)
        staticFileLocation("/public");

        PostOpService model = new PostOpService();
        new PostOpController(model);

    }
}
