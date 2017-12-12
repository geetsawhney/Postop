package com.postop.model;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Push {

    private static final Logger logger = LoggerFactory.getLogger(Push.class);

    public static boolean sendPush(Patient patient) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        HttpPost request = new HttpPost("https://gcm-http.googleapis.com/gcm/send");
        JSONObject j1 = new JSONObject();
        JSONObject j3 = new JSONObject();

        j1.put("to", patient.getDeviceId());
        j3.put("name", patient.getName());

        j3.put("message", "Please Drink Water Today");
        j1.put("data", j3);

        StringEntity params = null;

        params = new StringEntity(j1.toString());
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);

//            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        logger.info("Response Code : " + response.getStatusLine().getStatusCode());

        return true;

    }
}
