package com.postop;

import com.postop.controller.PostOpController;
import com.postop.service.PostOpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

/**
 * This class acts as the entry point of the program as it contains the main method.
 * @author Geet Sawhney, Rohit Aakash
 */
public class Bootstrap {
    public static final String IP_ADDRESS = "localhost";
    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);
    public static int PORT = 8080;

    public static void main(String[] args){
        //Specify the IP address and Port at which the server should be run
        PORT = getHerokuAssignedPort();
        port(PORT);

        //Specify the sub-directory from which to serve static resources (like html and css)
        staticFileLocation("/public");

        PostOpService model = new PostOpService();
        new PostOpController(model);

    }

    /**
     * to get a heroku port if there exists one or get default 4567
     * @return
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
