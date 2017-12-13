package com.oose.postop.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.TimeZone;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.oose.postop.dao.PatientDataDAO;
import com.oose.postop.helpers.ConnectionHelper;

import org.json.JSONObject;

/**
 * Created by Omotola on 11/11/2017.
 */

public class PushNotificationAlarm extends BroadcastReceiver {

    ConnectionHelper connectionHelper = new ConnectionHelper();


    public void setAlarm(Context context, int mins, boolean test) {
       /* Setting the alarm here */
        Intent alarmIntent = new Intent(context, PushNotificationAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        int interval;

        if (test) {
            interval = 1000*30;
            manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        } else {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 9);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            c.setTimeZone(TimeZone.getTimeZone("America/New_York"));
             interval = 1000 * 60 * mins;
            manager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), interval, pendingIntent);
        }



    }

    @Override
    public void onReceive(Context context, Intent intent) {



        PatientDataDAO d = new PatientDataDAO(context);
        String id = d.retrieveID();
        volleyRequest(context, id);

    }

    public void volleyRequest(final Context context, String id) {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = connectionHelper.getPushNotificationUrl(id);

        // Request a string response from the provided URL.
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();


                    }
                });
        queue.add(jsObjRequest);


    }

    public static void StopAlarm(Context c) {


        Intent alarmIntent = new Intent(c, PushNotificationAlarm.class);


        PendingIntent pendingIntent = PendingIntent.getBroadcast(c, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
    }


}
