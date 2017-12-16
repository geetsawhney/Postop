package com.postop.controller;

import com.postop.Bootstrap;
import com.postop.dao.CallbackDaoImpl;
import com.postop.dao.FitnessHistoryDaoImpl;
import com.postop.dao.PatientDaoImpl;
import com.postop.dao.PatientLoginDaoImpl;
import com.postop.dao.interfaces.CallbackDao;
import com.postop.dao.interfaces.FitnessHistoryDao;
import com.postop.dao.interfaces.PatientDao;
import com.postop.dao.interfaces.PatientLoginDao;
import com.postop.model.FitnessHistory;
import com.postop.utils.DbConnector;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.*;
import spark.Spark;

import java.io.IOException;
import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class PostOpControllerTest {


    //------------------------------------------------------------------------//
    // Setup
    //------------------------------------------------------------------------//

    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        //Start the main server
        Bootstrap.main(null);
        Spark.awaitInitialization();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        //Stop the server
        Spark.stop();
    }
    //------------------------------------------------------------------------//
    // Setup
    //------------------------------------------------------------------------//

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() {
    }

    //------------------------------------------------------------------------//
    // Tests
    //------------------------------------------------------------------------//


    /**
     * Create a Patient
     *
     * @throws IOException
     */
    @Test
    public void createPatientEndPoint1() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("email", "oosegroup19test@gmail.com");
        jsonObject.put("password", "life");
        jsonObject.put("ssn", "865432111");
        jsonObject.put("id", "");
        jsonObject.put("name", "Test Case");
        jsonObject.put("sex", "M");
        jsonObject.put("dob", "2019-09-09");
        jsonObject.put("address", "Somewhere in Somewhere'sville");
        jsonObject.put("phone", "9096784563");
        jsonObject.put("hospitalVisitReason", "Death");
        jsonObject.put("utiVisitCount", 200);
        jsonObject.put("catheterUsage", true);
        jsonObject.put("diabetic", true);
        jsonObject.put("lastVisitDate", "2019-09-09");

        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        PatientLoginDao pld = new PatientLoginDaoImpl();
        pld.deletePatient(jsonObject.get("email").toString());

        PatientDao pd = new PatientDaoImpl();
        pd.deletePatient(jsonObject.get("email").toString());


        assertEquals(200, response.getStatusLine().getStatusCode());
    }


    /**
     * Create a Patient ---Testing 500 Exception Patient already exists
     *
     * @throws IOException
     */
    @Test
    public void createPatientEndPoint2() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("email", "oosegroup19@gmail.com");
        jsonObject.put("password", "life");
        jsonObject.put("ssn", "865432111");
        jsonObject.put("id", "");
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


        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);


        assertEquals(500, response.getStatusLine().getStatusCode());

    }

    /**
     * Create a Patient ---Testing 400 Exception for IllegalJsonException
     *
     * @throws IOException
     */
    @Test
    public void createPatientEndPoint3() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient");

        StringEntity params = new StringEntity("test");
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        assertEquals(400, response.getStatusLine().getStatusCode());
    }


    /**
     * Patient Login -- Testing 200 valid email and password
     *
     * @throws IOException
     */
    @Test
    public void patientLoginEndPoint1() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/login");
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("email", "Oosegroup19@gmail.com");
        jsonObject.put("password", "secret");
        jsonObject.put("id", "fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");

        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        assertEquals(200, response.getStatusLine().getStatusCode());

    }

    /**
     * Patient Login - Testing Exception 404- Patient does not exist
     *
     * @throws IOException
     */
    @Test
    public void patientLoginEndPoint2() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/login");
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("email", "oosegroup19test@gmail.com");
        jsonObject.put("password", "secret");
        jsonObject.put("id", "fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");


        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        assertEquals(404, response.getStatusLine().getStatusCode());
    }


    /**
     * Patient Login --testing 400 IllegalJsonException
     *
     * @throws IOException
     */
    @Test
    public void patientLoginEndPoint3() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/login");

        StringEntity params = new StringEntity("hello");
        request.addHeader("content-type", "application/json");
        request.setEntity(params);

        HttpResponse response = httpClient.execute(request);
        assertEquals(400, response.getStatusLine().getStatusCode());
    }


    /**
     * Patient Login - Testing Exception 404- Patient does not exist and login for a second time
     *
     * @throws IOException
     */
    @Test
    public void patientLoginEndPoint4() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/login");
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", "fBoWR-eAfHQ:APA91bFVF6ex6FMRpVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");

        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        assertEquals(404, response.getStatusLine().getStatusCode());
    }


    /**
     * Update a Patient -- testing 200 for an already existing patient
     *
     * @throws IOException
     */
    @Test
    public void updatePatientEndPoint1() throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("email", "oosegroup19test@gmail.com");
        jsonObject.put("password", "life");
        jsonObject.put("ssn", "8654321110");
        jsonObject.put("id", "");
        jsonObject.put("name", "Test Case");
        jsonObject.put("sex", "M");
        jsonObject.put("dob", "2019-09-09");
        jsonObject.put("address", "Somewhere in Somewhere'sville");
        jsonObject.put("phone", "9096784563");
        jsonObject.put("hospitalVisitReason", "Death");
        jsonObject.put("utiVisitCount", 200);
        jsonObject.put("catheterUsage", true);
        jsonObject.put("diabetic", true);
        jsonObject.put("lastVisitDate", "2019-09-09");

        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        httpClient.execute(request);

        HttpClient httpClient1 = HttpClientBuilder.create().build();

        HttpPut request1 = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19test@gmail.com");
        JSONObject jsonObject1 = new JSONObject();


        jsonObject1.put("email", "oosegroup19test@gmail.com");
        jsonObject1.put("password", "secret");
        jsonObject1.put("ssn", "8654321110");
        jsonObject1.put("id", "");
        jsonObject1.put("name", "John Son");
        jsonObject1.put("sex", "M");
        jsonObject1.put("dob", "2019-09-09");
        jsonObject1.put("address", "Somewhere in Somewhereville");
        jsonObject1.put("phone", "9096784563");
        jsonObject1.put("hospitalVisitReason", "Death");
        jsonObject1.put("utiVisitCount", 290);
        jsonObject1.put("catheterUsage", true);
        jsonObject1.put("diabetic", true);
        jsonObject1.put("lastVisitDate", "2019-09-09");


        StringEntity params1 = new StringEntity(jsonObject1.toString());
        request1.addHeader("content-type", "application/json");
        request1.setEntity(params1);
        HttpResponse response = httpClient1.execute(request1);

        PatientLoginDao pld = new PatientLoginDaoImpl();
        pld.deletePatient(jsonObject.get("email").toString());
        PatientDao pd = new PatientDaoImpl();
        pd.deletePatient(jsonObject.get("email").toString());


        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    /**
     * Update a Patient -- testing 404 for non existing patient
     *
     * @throws IOException
     */
    @Test
    public void updatePatientEndPoint2() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPut request = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19test@gmail.com");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("email", "oosegroup19test@gmail.com");
        jsonObject.put("password", "secret");
        jsonObject.put("ssn", "865432111");
        jsonObject.put("id", "");
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

        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        assertEquals(404, response.getStatusLine().getStatusCode());
    }

    /**
     * Update a Patient -- testing 400 for illegal json
     *
     * @throws IOException
     */
    @Test
    public void updatePatientEndPoint3() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPut request = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/ooSegroup19@gmail.com");

        StringEntity params = new StringEntity("test");
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        assertEquals(400, response.getStatusLine().getStatusCode());
    }

    /**
     * Update a Patient -- testing 400 for inconsistent email in json body and url
     *
     * @throws IOException
     */
    @Test
    public void updatePatientEndPoint4() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPut request = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19test@gmail.com");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("email", "test2@test.com");
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

        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        assertEquals(400, response.getStatusLine().getStatusCode());
    }


    /**
     * Get a Patient -- testing 200 as patient exists
     *
     * @throws IOException
     */
    @Test
    public void getAPatientEndpoint1() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/Oosegroup19@gmail.com");

        request.addHeader("content-type", "application/json");
        HttpResponse response = httpClient.execute(request);

        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    /**
     * Get a Patient -- testing 404 for an already existing patient
     *
     * @throws IOException
     */
    @Test
    public void getAPatientEndpoint2() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19test@gmail.com");

        request.addHeader("content-type", "application/json");
        HttpResponse response = httpClient.execute(request);

        assertEquals(404, response.getStatusLine().getStatusCode());
    }


    /**
     * Get List of Patients -- testing 200
     *
     * @throws IOException
     */
    @Test
    public void getPatientsEndPoint() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patients");
        request.addHeader("content-type", "application/json");
        HttpResponse response = httpClient.execute(request);
        assertEquals(200, response.getStatusLine().getStatusCode());
    }


    /**
     * Adding a callback -- testing 200
     *
     * @throws IOException
     **/
    @Test
    public void updateCallbackEndPoint1() throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient");
        JSONObject jsonObject = new JSONObject();

        //adding a new patient oosegroup19test@gmail.com

        jsonObject.put("email", "oosegroup19test@gmail.com");
        jsonObject.put("password", "life");
        jsonObject.put("ssn", "865432111");
        jsonObject.put("id", "");
        jsonObject.put("name", "Test Case");
        jsonObject.put("sex", "M");
        jsonObject.put("dob", "2019-09-09");
        jsonObject.put("address", "Somewhere in Somewhere'sville");
        jsonObject.put("phone", "9096784563");
        jsonObject.put("hospitalVisitReason", "Death");
        jsonObject.put("utiVisitCount", 200);
        jsonObject.put("catheterUsage", true);
        jsonObject.put("diabetic", true);
        jsonObject.put("lastVisitDate", "2019-09-09");

        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        httpClient.execute(request);


        httpClient = HttpClientBuilder.create().build();

        HttpPut requestPut = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19test@gmail.com/callback");
        jsonObject = new JSONObject();


        jsonObject.put("email", "oosegroup19test@gmail.com");
        jsonObject.put("callbackDate", "2017-10-10");
        jsonObject.put("isResolved", "false");
        jsonObject.put("severity", 0);
        jsonObject.put("hasPain", true);
        jsonObject.put("hasNausea", true);
        jsonObject.put("hasFever", true);
        jsonObject.put("hasFatigue", true);
        jsonObject.put("urineColor", "Cloudy");


        params = new StringEntity(jsonObject.toString());
        requestPut.addHeader("content-type", "application/json");
        requestPut.setEntity(params);
        HttpResponse response = httpClient.execute(requestPut);

        PatientLoginDao pld = new PatientLoginDaoImpl();
        pld.deletePatient(jsonObject.get("email").toString());
        CallbackDao cd = new CallbackDaoImpl();
        cd.deleteCallback(jsonObject.get("email").toString());
        PatientDao pd = new PatientDaoImpl();
        pd.deletePatient(jsonObject.get("email").toString());

        assertEquals(200, response.getStatusLine().getStatusCode());
    }


    /**
     * Update an existing callback --testing 200
     *
     * @throws IOException
     */
    @Test
    public void updateCallbackEndPoint2() throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("email", "oosegroup19test@gmail.com");
        jsonObject.put("password", "life");
        jsonObject.put("ssn", "865432111");
        jsonObject.put("id", "");
        jsonObject.put("name", "Test Case");
        jsonObject.put("sex", "M");
        jsonObject.put("dob", "2019-09-09");
        jsonObject.put("address", "Somewhere in Somewhere'sville");
        jsonObject.put("phone", "9096784563");
        jsonObject.put("hospitalVisitReason", "Death");
        jsonObject.put("utiVisitCount", 200);
        jsonObject.put("catheterUsage", true);
        jsonObject.put("diabetic", true);
        jsonObject.put("lastVisitDate", "2019-09-09");

        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        httpClient.execute(request);


        httpClient = HttpClientBuilder.create().build();

        HttpPut requestPut = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19test@gmail.com/callback");
        jsonObject = new JSONObject();


        jsonObject.put("email", "oosegroup19test@gmail.com");
        jsonObject.put("callbackDate", "2017-10-10");
        jsonObject.put("isResolved", "false");
        jsonObject.put("severity", 0);
        jsonObject.put("hasPain", true);
        jsonObject.put("hasNausea", true);
        jsonObject.put("hasFever", true);
        jsonObject.put("hasFatigue", true);
        jsonObject.put("urineColor", "Cloudy");


        params = new StringEntity(jsonObject.toString());
        requestPut.addHeader("content-type", "application/json");
        requestPut.setEntity(params);
        httpClient.execute(requestPut);


        httpClient = HttpClientBuilder.create().build();
        requestPut = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19test@gmail.com/callback");
        jsonObject = new JSONObject();

        jsonObject.put("email", "oosegroup19test@gmail.com");
        jsonObject.put("callbackDate", "2017-10-10");
        jsonObject.put("isResolved", "false");
        jsonObject.put("severity", 0);
        jsonObject.put("hasPain", false);
        jsonObject.put("hasNausea", true);
        jsonObject.put("hasFever", true);
        jsonObject.put("hasFatigue", true);
        jsonObject.put("urineColor", "Normal");

        params = new StringEntity(jsonObject.toString());
        requestPut.addHeader("content-type", "application/json");
        requestPut.setEntity(params);
        HttpResponse response = httpClient.execute(requestPut);


        PatientLoginDao pld = new PatientLoginDaoImpl();
        pld.deletePatient(jsonObject.get("email").toString());
        CallbackDao cd = new CallbackDaoImpl();
        cd.deleteCallback(jsonObject.get("email").toString());
        PatientDao pd = new PatientDaoImpl();
        pd.deletePatient(jsonObject.get("email").toString());


        assertEquals(200, response.getStatusLine().getStatusCode());

    }


    /**
     * Update a callback -- testing 400 for illegal json
     *
     * @throws IOException
     */
    @Test
    public void updateCallbackEndPoint3() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPut request = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19@gmail.com/callback");

        StringEntity params = new StringEntity("test");
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        assertEquals(400, response.getStatusLine().getStatusCode());
    }


    /**
     * Updating a callback -- testing 404 patient does not exist
     *
     * @throws IOException
     */
    @Test
    public void updateCallbackEndPoint4() throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPut request = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19test@gmail.com/callback");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("email", "oosegroup19test@gmail.com");
        jsonObject.put("callbackDate", "2017-10-10");
        jsonObject.put("isResolved", "false");
        jsonObject.put("severity", 0);
        jsonObject.put("hasPain", true);
        jsonObject.put("hasNausea", true);
        jsonObject.put("hasFever", true);
        jsonObject.put("hasFatigue", true);
        jsonObject.put("urineColor", "Cloudy");


        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        PatientLoginDao pld = new PatientLoginDaoImpl();
        pld.deletePatient(jsonObject.get("email").toString());
        PatientDao pd = new PatientDaoImpl();
        pd.deletePatient(jsonObject.get("email").toString());

        assertEquals(404, response.getStatusLine().getStatusCode());
    }


    /**
     * Update a callback -- testing 400 for illegal json when email and
     *
     * @throws IOException
     */
    @Test
    public void updateCallbackEndPoint5() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPut request = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19@gmail.com/callback");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "oosegroup19test@gmail.com");

        StringEntity params = new StringEntity("test");
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        assertEquals(400, response.getStatusLine().getStatusCode());
    }


    /**
     * Get a Callback -- testing 200
     *
     * @throws IOException
     */
    @Test
    public void getCallbackEndPoint1() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19@gmail.com/callback");

        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        HttpResponse response = httpClient.execute(request);

        assertEquals(200, response.getStatusLine().getStatusCode());

    }

    /**
     * Get a Callback where callback does not exist for a patient --testing 404
     *
     * @throws IOException
     */
    @Test
    public void getCallbackEndPoint2() throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("email", "oosegroup19test@gmail.com");
        jsonObject.put("password", "life");
        jsonObject.put("ssn", "865432111");
        jsonObject.put("id", "");
        jsonObject.put("name", "Test Case");
        jsonObject.put("sex", "M");
        jsonObject.put("dob", "2019-09-09");
        jsonObject.put("address", "Somewhere in Somewhere'sville");
        jsonObject.put("phone", "9096784563");
        jsonObject.put("hospitalVisitReason", "Death");
        jsonObject.put("utiVisitCount", 200);
        jsonObject.put("catheterUsage", true);
        jsonObject.put("diabetic", true);
        jsonObject.put("lastVisitDate", "2019-09-09");

        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        httpClient.execute(request);

        httpClient = HttpClientBuilder.create().build();

        HttpGet requestGet = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19test@gmail.com/callback");
        requestGet.addHeader("content-type", "application/json");
        HttpResponse response = httpClient.execute(requestGet);

        PatientLoginDao pld = new PatientLoginDaoImpl();
        pld.deletePatient(jsonObject.get("email").toString());
        PatientDao pd = new PatientDaoImpl();
        pd.deletePatient(jsonObject.get("email").toString());

        assertEquals(404, response.getStatusLine().getStatusCode());
    }


    /**
     * Get a Callback where a patient does not exist --testing 404
     *
     * @throws IOException
     */
    @Test
    public void getCallbackEndPoint3() throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet requestGet = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19test@gmail.com/callback");
        requestGet.addHeader("content-type", "application/json");
        HttpResponse response = httpClient.execute(requestGet);

        assertEquals(404, response.getStatusLine().getStatusCode());
    }

    /**
     * Get List of Callbacks --testing 200
     *
     * @throws IOException
     */
    @Test
    public void getCallbackListEndPoint() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patients/callbacks");
        request.addHeader("content-type", "application/json");
        HttpResponse response = httpClient.execute(request);

        assertEquals(200, response.getStatusLine().getStatusCode());
    }


    /**
     * Add Fitness Data -- testing 200
     *
     * @throws IOException
     */
    @Test
    public void addFitnessDataEndPoint1() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/gfit");
        JSONObject jsonObject = new JSONObject();
        Date date = new Date(System.currentTimeMillis());

        jsonObject.put("id", "fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");
        jsonObject.put("captureDate", date.toString());
        jsonObject.put("stepCount", "2345");
        jsonObject.put("caloriesExpended", "456");

        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        FitnessHistory fitnessHistory = new FitnessHistory();
        fitnessHistory.setEmail("oosegroup19@gmail.com");
        fitnessHistory.setCaptureDate(Date.valueOf(jsonObject.get("captureDate").toString()));
        fitnessHistory.setStepCount(Integer.parseInt(jsonObject.get("stepCount").toString()));
        fitnessHistory.setCaloriesExpended(Integer.parseInt(jsonObject.get("caloriesExpended").toString()));
        FitnessHistoryDao fhd = new FitnessHistoryDaoImpl();
        fhd.deleteFitnessData(fitnessHistory);

        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    /**
     * Add Fitness Data -- patient does not exist --testing 404
     *
     * @throws IOException
     */
    @Test
    public void addFitnessDataEndPoint2() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/gfit");
        JSONObject jsonObject = new JSONObject();
        Date date = new Date(System.currentTimeMillis());

        jsonObject.put("id", "fBoWR-eAfHQ:RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");
        jsonObject.put("captureDate", date.toString());
        jsonObject.put("stepCount", "2345");
        jsonObject.put("caloriesExpended", "456");

        StringEntity params = new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        assertEquals(404, response.getStatusLine().getStatusCode());

    }

    /**
     * Add Fitness Data -- illegal json --testing 400
     *
     * @throws IOException
     */
    @Test
    public void addFitnessDataEndPoint3() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/gfit");

        StringEntity params = new StringEntity("test");
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

        assertEquals(400, response.getStatusLine().getStatusCode());

    }

    /**
     * Send Push Notification -- testing 200
     *
     * @throws IOException
     */
    @Test
    public void getPushNotificationEndPoint1() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI/push");
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        HttpResponse response = httpClient.execute(request);
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    /**
     * Send Push Notification -- testing 404 patient does not exist
     *
     * @throws IOException
     */
    @Test
    public void getPushNotificationEndPoint2() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/fBoWR-eAfHQ:APVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI/push");
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        HttpResponse response = httpClient.execute(request);

        DbConnector.closeConnection();
        assertEquals(404, response.getStatusLine().getStatusCode());
    }

    /**
     * Get Notificatons -- testing 200 as notifications *
     *
     * @throws IOException
     */
    @Test
    public void getNotificationsEndpoint1() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/nurse/notification");

        request.addHeader("content-type", "application/json");

        HttpResponse response = httpClient.execute(request);

        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    /**
     * Get a Patient -- testing 200 as patient exists
     *
     * @throws IOException
     */
    @Test
    public void putANotificationEndpoint1() throws IOException, ParseException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/nurse/notification")
                .addHeader("content-type", "application/json")
                .get()
                .build();

        Response response = client.newCall(request).execute();
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(response.body().string());

        JSONObject row = (JSONObject) jsonArray.get(0);


        int before = Integer.parseInt(row.get("start").toString());
        row.put("start", 100);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut requestHttp = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/nurse/notification");
        StringEntity params = new StringEntity(row.toString());
        requestHttp.addHeader("content-type", "application/json");
        requestHttp.setEntity(params);
        httpClient.execute(requestHttp);

        row.put("start", before);

        requestHttp = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/nurse/notification");
        params = new StringEntity(row.toString());
        requestHttp.addHeader("content-type", "application/json");
        requestHttp.setEntity(params);
        HttpResponse responseHttp = httpClient.execute(requestHttp);

        assertEquals(200, responseHttp.getStatusLine().getStatusCode());
    }

}