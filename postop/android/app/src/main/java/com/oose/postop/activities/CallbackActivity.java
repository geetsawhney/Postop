package com.oose.postop.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.oose.postop.helpers.ConnectionHelper;
import com.oose.postop.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Activity Class for creating callbacks
 * Created by Omotola on 11/12/2017.
 */


public class CallbackActivity extends Activity implements AdapterView.OnItemSelectedListener {
    public boolean hasFever;
    public boolean hasFatigue;
    public boolean hasPain;
    public boolean hasNausea;
    public boolean isResolved = false;
    public int severity = 0;
    public String email;
    public String callbackDate;
    public String urineColor;
    Spinner s;
    CheckBox fatigue, fever,nausea,pain;

ConnectionHelper connectionHelper = new ConnectionHelper();


    /**
     * initialize activity
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callback_layout);
        TextView t = (TextView) findViewById(R.id.welcomeText);
                t.setTextSize(20);

        email = getIntent().getExtras().getString("email");
         s  = (Spinner) findViewById(R.id.urineDropdown);
        setUrineColorOptions(s);
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        callbackDate = df.format(c.getTime());
        fatigue= (CheckBox) findViewById(R.id.hasFatigue);
        fever = (CheckBox) findViewById(R.id.hasFever);
        pain = (CheckBox) findViewById(R.id.hasPain);
        nausea = (CheckBox) findViewById(R.id.hasNausea);





    }

    /**
     *Sets the options for the urinecolor drop down menu
     * @param s
     */
    public void setUrineColorOptions(Spinner s){

    List<String> list = new ArrayList<String>();
    list.add("Dark");
    list.add("Cloudy");
    list.add("Normal");
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, list);
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    s.setAdapter(dataAdapter);
    s.setOnItemSelectedListener(this);

}

    /**
     *Called when submit button is clicked
     * @param v
     */
    public void submit(View v){
        hasFatigue =fatigue.isChecked();
        hasFever =fever.isChecked();
        hasNausea =nausea.isChecked();;
        hasPain =pain.isChecked();
        JSONObject j = new JSONObject();
        try {
            j.put("email", email);
            j.put("callbackDate", callbackDate);
            j.put("severity", severity);
            j.put("isResolved", isResolved);
            j.put("hasFatigue", hasFatigue);
            j.put("hasFever", hasFever);
            j.put("hasPain", hasPain);
            j.put("hasNausea", hasNausea);
            j.put("urineColor", urineColor);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        volleyRequest(j, this);
        finish();
       // System.exit(0);
    }


    /**
     *Sends Volley Request
     * @param jsonRequestObject
     * @param context
     */
    public void volleyRequest(JSONObject jsonRequestObject, final Context context) {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = connectionHelper.SendCallbackUrl(email);

        // Request a string response from the provided URL.
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.PUT, url, jsonRequestObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Toast.makeText(context, response.get("message").toString(), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mTextView.setText("That didn't work!");
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();


                    }
                });
        queue.add(jsObjRequest);


    }

    /**
     *Called when an item in this view has been selected.
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        urineColor = String.valueOf(s.getSelectedItem());
    }

    /**
     *Called when nothing has been selected.
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}