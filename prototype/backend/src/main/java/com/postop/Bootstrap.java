package com.postop;

import com.postop.dao.PatientDaoImpl;
import com.postop.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

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

        System.out.println("Get patient by email");

        Patient patient = pdi.getPatientByEmail("gitikavijh@yahoo.co.in");
        System.out.println(patient.toString());

        System.out.println("Updating patient");
        patient = new Patient("gitikavijh@yahoo.co.in", "Gitika", "F", "454418518");
        pdi.updatePatient(patient);

        Patient p2 = pdi.getPatientByEmail("gitikavijh@yahoo.co.in");
        System.out.println(p2.toString());


        System.out.println("Added a patient");
        Patient p3 = new Patient("rohit.aakash13@gmail.com", "Rohit", "M", "467389034");
        System.out.println(pdi.addPatient(p3));


        Patient p4=new Patient();
        pdi.addPatient(p4);

        System.out.println("all patients again");
        list = pdi.getAllPatients();
        for(Patient p: list){
            System.out.println(p.toString());
        }
        Patient p5 = new Patient("rohit.aakash13@gmail.com", "Rohit", "M", "1563256889");
        pdi.deletePatient(p5);

        System.out.println("After Deletion");
        List<Patient> list2 = pdi.getAllPatients();
        for(Patient p: list2){
            System.out.println(p.toString());
        }
    }


}
