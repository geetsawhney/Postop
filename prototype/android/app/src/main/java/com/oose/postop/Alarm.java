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

    public void setAlarm(Context context) {
       /* Setting the alarm here */
        Intent alarmIntent = new Intent(context, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 1;
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);

        Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // GoogleFitFetchService g = new GoogleFitFetchService();
        context.startService(new Intent(context, GoogleFitFetchService.class));
        //Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show();

        // Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show();


    }
}
