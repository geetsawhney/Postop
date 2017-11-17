package com.postop.controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.*;

public class PostOpControllerTest {


    /**
     * Update a Callback
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void UpdateCallbackEndPoint () throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPut request = new HttpPut ("http://10.194.45.213:8080/api/v1/patient/oosegroup19@gmail.com/callback");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("email", "oosegroup19@gmail.com");
        jsonObject.put("callbackDate", "10-10-2017");
        jsonObject.put("isResolved", "false");
        jsonObject.put("severity", 0);
        jsonObject.put("hasPain", true);
        jsonObject.put("hasNausea", true);
        jsonObject.put("hasFever", true);
        jsonObject.put("hasFatigue", true);
        jsonObject.put("urineColor", "Cloudy");


        StringEntity params = null;
        params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);


        assertEquals(200, response.getStatusLine().getStatusCode());

    }

    /**
     * Patient Login
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void PatientLoginEndPoint () throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost ("http://10.194.45.213:8080/api/v1/patient/login");
        JSONObject jsonObject = new JSONObject();
       // JSONObject ejsonObject = new JSONObject();

        jsonObject.put("email", "oosegroup19@gmail.com");
        jsonObject.put("password", "secret");
        jsonObject.put("id", "fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");


        StringEntity params = null;
        params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);


        assertEquals(200, response.getStatusLine().getStatusCode());

    }

    /**
     * Patient Login - Testing Exception 404- Patient does not exist
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void Patient2LoginEndPoint () throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost ("http://10.194.45.213:8080/api/v1/patient/login");
        JSONObject jsonObject = new JSONObject();
        // JSONObject ejsonObject = new JSONObject();

        jsonObject.put("email", "oosegroup1119@gmail.com");
        jsonObject.put("password", "secret");
        jsonObject.put("id", "fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");


        StringEntity params = null;
        params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);


        assertEquals(404, response.getStatusLine().getStatusCode());

    }

    /**
     * Get List of Patients
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void GetPatientsEndPoint () throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet ("http://10.194.45.213:8080/api/v1/patients");




        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        HttpResponse response = httpClient.execute(request);


        assertEquals(200, response.getStatusLine().getStatusCode());

    }

    /**
     * Add Fitness Data
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void addFitnessDataEndPoint () throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost ("http://10.194.45.213:8080/api/v1/patient/gfit");
        JSONObject jsonObject = new JSONObject();
        // JSONObject ejsonObject = new JSONObject();
        jsonObject.put("id", "fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");
        jsonObject.put("captureDate", "11-11-2011");
        jsonObject.put("stepCount", "2345");
        jsonObject.put("caloriesExpended", "456");


        StringEntity params = null;
        params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);


        assertEquals(200, response.getStatusLine().getStatusCode());

    }


    /**
     * Get List of Callbacks
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
   @Test
    public void GetCallbackListEndPoint () throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet ("http://10.194.45.213:8080/api/v1/patients/callbacks");




        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        HttpResponse response = httpClient.execute(request);


        assertEquals(200, response.getStatusLine().getStatusCode());

    }


    /**
     * Get a Callback
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void GetCallbackEndPoint () throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet ("http://10.194.45.213:8080/api/v1/patient/oosegroup19@gmail.com/callback");




        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        HttpResponse response = httpClient.execute(request);


        assertEquals(200, response.getStatusLine().getStatusCode());

    }

    @Test
    public void GetPushNotificationEndPoint () throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet ("http://10.194.45.213:8080/api/v1/patient/fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI/push");




        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        HttpResponse response = httpClient.execute(request);


        assertEquals(200, response.getStatusLine().getStatusCode());

    }

    /**
     * Get a Patient
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void GetAPatientEndpoint () throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet ("http://10.194.45.213:8080/api/v1/patient/oosegroup19@gmail.com");




        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        HttpResponse response = httpClient.execute(request);


        assertEquals(200, response.getStatusLine().getStatusCode());

    }


    /**
     * Create a Patient
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void CreatePatientEndPoint () throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost ("http://10.194.45.213:8080/api/v1/patient");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("email", "oose5group19@gmail.com");
        jsonObject.put("password", "life");
        jsonObject.put("ssn", "865432111");
        jsonObject.put("id", "fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");
        jsonObject.put("name", "John Son");
        jsonObject.put("sex", "M");
        jsonObject.put("dob", "2019-09-09");
        jsonObject.put("address", "Somewhere in Somewhere'sville");
        jsonObject.put("phone", "9096784563");
        jsonObject.put("hospitalVisitReason", "Death");
        jsonObject.put("utiVisitCount", 200);
        jsonObject.put("catheterUsage", true);
        jsonObject.put("diabetic", true);
        jsonObject.put("lastVisitDate", "2019-09-09");


        StringEntity params = null;
        params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);


        assertEquals(200, response.getStatusLine().getStatusCode());

    }


    /**
     * Create a Patient ---Testing 500 Exception Patient already exists
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void CreatePatient2EndPoint () throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost ("http://10.194.45.213:8080/api/v1/patient");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("email", "oosegroup19@gmail.com");
        jsonObject.put("password", "life");
        jsonObject.put("ssn", "865432111");
        jsonObject.put("id", "fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");
        jsonObject.put("name", "John Son");
        jsonObject.put("sex", "M");
        jsonObject.put("dob", "2019-09-09");
        jsonObject.put("address", "Somewhere in Somewhere'sville");
        jsonObject.put("phone", "9096784563");
        jsonObject.put("hospitalVisitReason", "Death");
        jsonObject.put("utiVisitCount", 200);
        jsonObject.put("catheterUsage", true);
        jsonObject.put("diabetic", true);
        jsonObject.put("lastVisitDate", "2019-09-09");


        StringEntity params = null;
        params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);


        assertEquals(500, response.getStatusLine().getStatusCode());

    }




    /**
     * Update a Patient
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void UpdatePatientEndPoint () throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPut request = new HttpPut ("http://10.194.45.213:8080/api/v1/patient/oosegroup19@gmail.com");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("email", "oosegroup19@gmail.com");
        jsonObject.put("password", "secret");
        jsonObject.put("ssn", "865432111");
        jsonObject.put("id", "fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");
        jsonObject.put("name", "John Son");
        jsonObject.put("sex", "M");
        jsonObject.put("dob", "2019-09-09");
        jsonObject.put("address", "Somewhere in Somewhereville");
        jsonObject.put("phone", "9096784563");
        jsonObject.put("hospitalVisitReason", "Death");
        jsonObject.put("utiVisitCount", 290);
        jsonObject.put("catheterUsage", true);
        jsonObject.put("diabetic", true);
        jsonObject.put("lastVisitDate", "2019-09-09");


        StringEntity params = null;
        params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);


        assertEquals(200, response.getStatusLine().getStatusCode());

    }

}