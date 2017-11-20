package com.oose.postop.receivers;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.widget.Toast;

import com.oose.postop.services.GoogleFitFetchService;
import com.oose.postop.helpers.ConnectionHelper;


/**Schedule fetch of Notification Count
 * Created by Omotola on 11/8/2017.
 */

public class NotificationCountAlarm extends BroadcastReceiver {
    ConnectionHelper connectionHelper = new ConnectionHelper();

    /**
     * Schedules the alarm for specified interval
     * @param context
     */
    public void setAlarm(Context context) {
       /* Setting the alarm here */
        Intent alarmIntent = new Intent(context, NotificationCountAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 60;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);

        Toast.makeText(context, "Scheduled Count Fetch", Toast.LENGTH_SHORT).show();

    }

    /**
     * Invoked on receive of alarm every interval
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // GoogleFitFetchService g = new GoogleFitFetchService();
        context.startService(new Intent(context, GoogleFitFetchService.class));
        //Toast.makeText(context, " Alarm!!!!!!!!!!", Toast.LENGTH_LONG).show();

        // Toast.makeText(context, "Alarm!!!!!!!!!!", Toast.LENGTH_LONG).show();


    }
}
