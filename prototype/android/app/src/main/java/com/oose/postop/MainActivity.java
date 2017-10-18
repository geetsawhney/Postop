package com.oose.postop;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    EditText emailField;
    EditText ssnField;
    String deviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all fields
        mTextView = (TextView)findViewById(R.id.output);
        emailField = (EditText)findViewById(R.id.email);
        ssnField = (EditText)findViewById(R.id.ssn);
        deviceId ="211312ekjkuhiu1h21U3u2";
    }

    /**
     * Onclick listener for login button
     * @param v
     */
    public void login(View v){
        // Get the username and password field data
        String userNameVal = emailField.getText().toString();
        String passwordVal = ssnField.getText().toString();
        String idVal = deviceId;

        // Send the credentials to the server for authentication
        volleyRequest(userNameVal, passwordVal, idVal);
    }

    /**
     * Volley send request
     * @param email
     * @param ssn
     */
    public void volleyRequest(String email, String ssn, String Id) {
        JSONObject jsonRequestObject = new JSONObject();
        try{
            jsonRequestObject.put("email", email.trim().toLowerCase());
            jsonRequestObject.put("ssn", ssn);
            jsonRequestObject.put("id", Id);
        }catch(JSONException ex){
            return;
        }

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8080/api/v1/patient/login";

        // Request a string response from the provided URL.
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonRequestObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            mTextView.setText("Response is: " + response.get("email").toString() + " " + response.get("ssn").toString() + " " +response.get("address").toString());
                        }catch(JSONException ex){
                            mTextView.setText("Bad Response!");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                    }
                });
        queue.add(jsObjRequest);
    }
}
