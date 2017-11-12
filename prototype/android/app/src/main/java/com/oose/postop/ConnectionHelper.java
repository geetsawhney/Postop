package com.oose.postop;

/**
 * Created by Omotola on 11/9/2017.
 */

public class ConnectionHelper {





    String ip = "http://10.194.45.213:8080";
    String retrievePatientUrl = "/api/v1/patient/login";
    String retrieveFitUrl = "/api/v1/patient/gfit";

    public String getRetrievePatientUrl(){
        return ip+retrievePatientUrl;
    }

    public String getRetrieveFitUrl(){
        return ip+retrieveFitUrl;
    }
}
