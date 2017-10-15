package com.postop;

import com.postop.controller.PostOpController;
import com.postop.dao.PatientDaoImpl;
import com.postop.model.Patient;
import com.postop.service.PostOpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

import static spark.Spark.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Bootstrap {
    public static final String IP_ADDRESS = "localhost";
    public static final int PORT = 8080;

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) throws Exception {
   
        //Specify the IP address and Port at which the server should be run
        ipAddress(IP_ADDRESS);
        port(PORT);

        //Specify the sub-directory from which to serve static resources (like html and css)
//        staticFileLocation("/public");

//      PostOpService model = new PostOpService();
//		new PostOpController(model);

		/* Test code here */
        PatientDaoImpl pdi = new PatientDaoImpl();
        List<Patient> list = pdi.getAllPatients();
        for(Patient p: list){
            System.out.println(p.toString());
        }

        Patient patient = pdi.getPatientById(2);
        System.out.println(patient.toString());

        patient = new Patient(2, "Geet", "M");
        pdi.updatePatient(patient);

        Patient p2 = pdi.getPatientById(2);
        System.out.println(p2.toString());

        p2=new Patient();
        pdi.addPatient(p2);

        System.out.println("all patients again");
        list = pdi.getAllPatients();
        for(Patient p: list){
            System.out.println(p.toString());
        }
    }


}

