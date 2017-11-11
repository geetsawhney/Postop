package com.oose.postop;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by Omotola on 11/8/2017.
 */

public class Alarm extends BroadcastReceiver {
    ConnectionHelper connectionHelper = new ConnectionHelper();
    public String deviceId;

    public void setAlarm(Context context, String id) {
       /* Setting the alarm here */
        Intent alarmIntent = new Intent(context, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 1;
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        deviceId = id;

        Toast.makeText(context, "Alarm Set"+id, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show();

        // Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show();
        GoogleFitFetch g = new GoogleFitFetch();
        g.buildFitnessClient(context);
       Calendar c = Calendar.getInstance();
//
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        //Toast.makeText(context, formattedDate.toString(), Toast.LENGTH_LONG).show();

        JSONObject j = new JSONObject();
        try {
            j.put("id", deviceId);
            j.put("capture_date", formattedDate);
            j.put("step_count", g.dailyTotalCount);
            j.put("calories_expended", g.dailyCaloriesExpended);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        volleyRequest(j, context);

    }


    public void volleyRequest(JSONObject jsonRequestObject, final Context context) {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = connectionHelper.getRetrieveFitUrl();

        // Request a string response from the provided URL.
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonRequestObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Toast.makeText(context, response.get("notificationCount").toString(), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(context, deviceId, Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mTextView.setText("That didn't work!");
                        Toast.makeText(context, deviceId, Toast.LENGTH_LONG).show();
                        error.printStackTrace();


                    }
                });
        queue.add(jsObjRequest);


    }
}
