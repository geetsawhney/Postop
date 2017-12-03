package com.oose.postop.helpers;

/**Url Helper for server endpoints
 * Created by Omotola on 11/9/2017.
 */

public class ConnectionHelper {





    String ip = "http://remcare.herokuapp.com";
    String retrievePatientUrl = "/api/v1/patient/login";
    String retrieveFitUrl = "/api/v1/patient/gfit";

    /**
     * endpoint for retrieving patients
     * @return
     */
    public String getRetrievePatientUrl(){
        return ip+retrievePatientUrl;
    }

    /**
     * end point for retrieving notification count
     * @return
     */
    public String getRetrieveFitUrl(){
        return ip+retrieveFitUrl;
    }

    /**
     * endpoint for getting push notification
     * @param id
     * @return
     */
    public String getPushNotificationUrl(String id){return ip+"/api/v1/patient/"+id+"/push";}

    /**
     * endpoint for sending callbacks
     * @param email
     * @return
     */
    public String SendCallbackUrl(String email){return ip+"/api/v1/patient/"+email+"/callback";}

}
