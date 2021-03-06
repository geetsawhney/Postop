package com.oose.postop.activities;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.oose.postop.helpers.ConnectionHelper;
import com.oose.postop.dao.PatientDataDAO;
import com.oose.postop.receivers.NotificationCountAlarm;
import com.oose.postop.R;
import com.oose.postop.services.RegistrationIntentService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Rohit on 10/09/2017.
 */
public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    EditText emailField;
    EditText passwordField;
    String deviceId;
    ProgressBar progress;
    PatientDataDAO d;


    ConnectionHelper connectionHelper = new ConnectionHelper();
    private BroadcastReceiver mRegistrationBroadcastReceiver;


    /**
     *initialize activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);


        //Initializing our broadcast receiver
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            //When the broadcast received
            //We are sending the broadcast from GCMRegistrationIntentService

            @Override
            public void onReceive(Context context, Intent intent) {
                //starting the listener service for messages

                //If the broadcast has received with success
                //that means device is registered successfully
                if (intent.getAction().equals(RegistrationIntentService.REGISTRATION_SUCCESS)) {
                    //Getting the registration token from the intent
                    String token = intent.getStringExtra("token");



                    //check if the id already exists in DB. If it does show homepage, if not show the login page
                     d = new PatientDataDAO(getApplicationContext());
                    deviceId = token;
                    if(d.checkIdExists(getApplicationContext(),deviceId) == true){


                        Map<String,String> requestList = new HashMap<String,String>();
                        requestList.put("id",deviceId);
                        volleyRequest(requestList);

                    }else{
                        setContentView(R.layout.activity_main);
                        // Initialize all fields
                        emailField = (EditText) findViewById(R.id.email);
                        passwordField = (EditText) findViewById(R.id.password);

                    }
                } else if (intent.getAction().equals(RegistrationIntentService.REGISTRATION_ERROR)) {
                    Toast.makeText(context, "GCM registration Error", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Error Occurred", Toast.LENGTH_LONG).show();

                }
            }
        };

        //Checking play service is available or not
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        //if play service is not available
        if (ConnectionResult.SUCCESS != resultCode) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                Toast.makeText(this, "Google Play Service is not install/enabled in this device!", Toast.LENGTH_LONG).show();

                GooglePlayServicesUtil.showErrorNotification(resultCode, getApplicationContext());

            } else {
                Toast.makeText(this, "This device does not support for Google Play Service!", Toast.LENGTH_LONG).show();
            }

            //If play service is available
        } else {
            //Starting intent to register device
            Intent itent = new Intent(this, RegistrationIntentService.class);
            startService(itent);
        }
    }


    /**
     * Registering receiver on activity resume
     */

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity", "onResume");
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(RegistrationIntentService.REGISTRATION_SUCCESS));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(RegistrationIntentService.REGISTRATION_ERROR));
    }


    /**
     *     Unregistering receiver on activity paused
     */

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("MainActivity", "onPause");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }



    /**
     * Onclick listener for login button
     * @param v
     */
    public boolean login(View v){
        if (TextUtils.isEmpty(emailField.getText())) {
            new AlertDialog.Builder(this).setTitle("Input Error").setMessage("You did not enter a email").setNeutralButton("Close", null).show();
            return false;
        }
        if (TextUtils.isEmpty(passwordField.getText())) {
            new AlertDialog.Builder(this).setTitle("Input Error").setMessage("You did not enter a password").setNeutralButton("Close", null).show();
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailField.getText()).matches()) {
         new AlertDialog.Builder(this).setTitle("Input Error").setMessage("You did not enter a valid email").setNeutralButton("Close", null).show();


            return false;
        }
        //turn on progress bar
        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setIndeterminate(true);
        progress.setVisibility(View.VISIBLE);

        // Get the email and ssn field data
        String userNameVal = emailField.getText().toString();
        String passwordVal = passwordField.getText().toString();
        String idVal = deviceId;

        //Save the values to a Hashmap
        Map<String,String> requestList = new HashMap<String,String>();
        requestList.put("email",userNameVal);
        requestList.put("password",passwordVal);
        requestList.put("id",idVal);





        // Send the credentials to the server for authentication
        volleyRequest(requestList);
        return true;
    }

    /**
     * Volley send request
     */
    public boolean volleyRequest(Map<String,String> h) {
        JSONObject jsonRequestObject = new JSONObject();
        try{
          for (Map.Entry<String,String> entry : h.entrySet() ){
              jsonRequestObject.put(entry.getKey(),entry.getValue());

          }
        }catch(JSONException ex){
            return false;
        }

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url =connectionHelper.getRetrievePatientUrl();

        // Request a string response from the provided URL.
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonRequestObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            Bundle localBundle = new Bundle();
                            localBundle.putString("name", response.get("name").toString());
                            localBundle.putString("email", response.get("email").toString());
                            localBundle.putString("address", response.get("address").toString());
                            localBundle.putString("id", deviceId);

                            Intent localIntent = new Intent(MainActivity.this, HomepageActivity.class);
                            localIntent.putExtras(localBundle);
                            startActivity(localIntent);
                            if(d.checkIdExists(getApplicationContext(),deviceId) == false) {
                                d.addIDToDB(deviceId);

                                 new NotificationCountAlarm().setAlarm(getApplicationContext(),true);



                            }
                            finish();
                        }catch(JSONException ex){
                            Toast.makeText(getApplicationContext(), "BAD RESPONSE", Toast.LENGTH_LONG).show();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        d.deleteID(deviceId);
                        try {

                            JSONObject obj = new JSONObject(new String(error.networkResponse.data));
                            Toast.makeText(getApplicationContext(),obj.getString("error"), Toast.LENGTH_LONG).show();
                            progress.setVisibility(View.INVISIBLE);

                        } catch (Exception e) {
                            progress.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(),"That Didn't work!", Toast.LENGTH_LONG).show();
                            e.printStackTrace();


                        }

                    }
                });
        queue.add(jsObjRequest);
        return true;
    }
}
