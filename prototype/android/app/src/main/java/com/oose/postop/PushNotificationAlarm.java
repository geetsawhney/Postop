package com.oose.postop;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;
import java.util.TimeZone;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Omotola on 11/11/2017.
 */

public class PushNotificationAlarm extends BroadcastReceiver {

    ConnectionHelper connectionHelper = new ConnectionHelper();

    public void setAlarm(Context context, int mins) {
       /* Setting the alarm here */
        Intent alarmIntent = new Intent(context, PushNotificationAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,22);
        c.set(Calendar.MINUTE,8);
        c.set(Calendar.SECOND,0);
        c.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * mins;
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), interval, pendingIntent);


        Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, " Alarm!!!!!!!!!!", Toast.LENGTH_LONG).show();
        DeviceIdDAO d = new DeviceIdDAO(context);
        String id = d.retrieveID();
        volleyRequest(context,id);
    }

    public void volleyRequest( final Context context, String id) {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = connectionHelper.getPushNotificationUrl(id);

        // Request a string response from the provided URL.
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                                         Toast.makeText(context, "Works", Toast.LENGTH_LONG).show();

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
}
