package com.postop.controller;

import com.postop.Bootstrap;
import com.postop.dao.PatientDaoImpl;
import com.postop.dao.PatientLoginDaoImpl;
import com.postop.dao.interfaces.PatientDao;
import com.postop.dao.interfaces.PatientLoginDao;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.junit.*;
import spark.Spark;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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


        jsonObject.put("email", "test1@test.com");
        jsonObject.put("password", "life");
        jsonObject.put("ssn", "865432111");
        jsonObject.put("id", "fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");
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

        jsonObject.put("email", "oosegroup19@gmail.com");
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

        jsonObject.put("email", "test1@test.com");
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


        jsonObject.put("email", "test1@test.com");
        jsonObject.put("password", "life");
        jsonObject.put("ssn", "865432111");
        jsonObject.put("id", "fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");
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

        HttpPut request1 = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/test1@test.com");
        JSONObject jsonObject1 = new JSONObject();


        jsonObject1.put("email", "test1@test.com");
        jsonObject1.put("password", "secret");
        jsonObject1.put("ssn", "865432111");
        jsonObject1.put("id", "fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");
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

        HttpPut request = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/test1@test.com");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("email", "test1@test.com");
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

        HttpPut request = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19@gmail.com");

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

        HttpPut request = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/test1@test.com");
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

        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19@gmail.com");

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

        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/test1@test.com");

        request.addHeader("content-type", "application/json");
        HttpResponse response = httpClient.execute(request);

        assertEquals(404, response.getStatusLine().getStatusCode());
    }


    /**
     * Update a Callback
     *
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void updateCallbackEndPoint1() {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPut request = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19@gmail.com/callback");
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
        try {
            params = new StringEntity(jsonObject.toString());
            request.addHeader("content-type", "application/json");
            request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            assertEquals(200, response.getStatusLine().getStatusCode());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void updateCallbackEndPoint2() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPut request = new HttpPut("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19@gmail.com/callback");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("email", "someemaildoesnotexists@gmail.com");
        jsonObject.put("callbackDate", "10-10-2017");
        jsonObject.put("isResolved", "false");
        jsonObject.put("severity", 0);
        jsonObject.put("hasPain", true);
        jsonObject.put("hasNausea", true);
        jsonObject.put("hasFever", true);
        jsonObject.put("hasFatigue", true);
        jsonObject.put("urineColor", "Cloudy");


        StringEntity params = null;
        try {
            params = new StringEntity(jsonObject.toString());
            request.addHeader("content-type", "application/json");
            request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            assertNotEquals(200, response.getStatusLine().getStatusCode());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get List of Patients
     *
     * @throws IOException
     */
    @Test
    public void getPatientsEndPoint() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patients");


        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        HttpResponse response = httpClient.execute(request);


        assertEquals(200, response.getStatusLine().getStatusCode());

    }

    /**
     * Add Fitness Data
     *
     * @throws IOException
     */
    @Test
    public void addFitnessDataEndPoint() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/gfit");
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

    @Test
    public void addFitnessDataEndPoint2() throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/gfit");
        JSONObject jsonObject = new JSONObject();
        // JSONObject ejsonObject = new JSONObject();
        jsonObject.put("id", "fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI");
        jsonObject.put("captureDate", "2017-11-25");
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
     *
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void GetCallbackListEndPoint() throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patients/callbacks");


        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        HttpResponse response = httpClient.execute(request);


        assertEquals(200, response.getStatusLine().getStatusCode());

    }


    /**
     * Get a Callback
     *
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void GetCallbackEndPoint() throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/oosegroup19@gmail.com/callback");


        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        HttpResponse response = httpClient.execute(request);


        assertEquals(200, response.getStatusLine().getStatusCode());

    }

    @Test
    public void GetCallbackEndPoint2() throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/hitesh@jhu.edu/callback");


        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        HttpResponse response = httpClient.execute(request);


        assertEquals(404, response.getStatusLine().getStatusCode());

    }

    @Test
    public void GetPushNotificationEndPoint() throws IOException, org.json.simple.parser.ParseException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet("http://" + Bootstrap.IP_ADDRESS + ":" + Bootstrap.PORT + "/api/v1/patient/fBoWR-eAfHQ:APA91bFVF6ex6FMRpLtuQNcIc4QOuaOzQEvco6RKK65xYInlXvWPwhxxeMi6FuVzCGyREfHEqorDYHWTnaDkIodXU8BDzrqjraPZt-EVesLJAQdwZe4aqnG2CA1FjpCgwUDVmzvgYHLI/push");


        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        HttpResponse response = httpClient.execute(request);


        assertEquals(200, response.getStatusLine().getStatusCode());

    }

}