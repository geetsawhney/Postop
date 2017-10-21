package com.postop.model;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

public class Push {
    String id;

    public Push(String i){
        id = i;
    }

    public void sendPush(){

        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        try {

            HttpPost request = new HttpPost("https://gcm-http.googleapis.com/gcm/send");
            JSONObject j1 = new JSONObject();
            JSONObject j2 = new JSONObject();
            JSONObject j3 = new JSONObject();
            j1.put("to",id);
            // j2.put("body","Testing 123");
            // j2.put("title", "TEST");
            j3.put("name","patient Name");
            j3.put("message", "Please Drink Water Today");
            //j1.put("notification",j2);
            j1.put("data",j3);


            StringEntity params =new StringEntity(j1.toString());
            request.addHeader("content-type", "application/json");
            request.addHeader("Authorization","key=AAAAtLQfbo8:APA91bEao5KXye_2NcyguzndrjY6NSKhXix0WVH06dOcez09VwV3kM2aHPufqoRrz-ro1Eo0Zh3OjU-w-LJ0WbA_BS9rXU95FPdkUs--Kk7MSsZHcISwRnym3d8_y3KxYMYP-ceLZPfc");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());
            //handle response here...

        }catch (Exception ex) {
            ex.printStackTrace();
            //handle exception here

        }

    }
}
