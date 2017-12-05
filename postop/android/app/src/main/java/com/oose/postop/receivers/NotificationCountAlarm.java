package com.oose.postop.receivers;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.widget.Toast;

import com.oose.postop.services.GoogleFitFetchService;
import com.oose.postop.helpers.ConnectionHelper;

import java.util.Calendar;
import java.util.TimeZone;


/**Schedule fetch of Notification Count
 * Created by Omotola on 11/8/2017.
 */

public class NotificationCountAlarm extends BroadcastReceiver{
    ConnectionHelper connectionHelper = new ConnectionHelper();
    /**
     * Schedules the alarm for specified interval
     * @param context
     */
    public void setAlarm(Context context, boolean test) {
       /* Setting the alarm here */
        Intent alarmIntent = new Intent(context, NotificationCountAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if(test) {
            int interval = 1000 * 60 * 2;

            manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        }else{
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY,9);
            c.set(Calendar.MINUTE,0);
            c.set(Calendar.SECOND,0);
            c.set(Calendar.MILLISECOND,0);
            c.setTimeZone(TimeZone.getTimeZone("America/New_York"));
            manager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }

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




    }


    public static void StopAlarm(Context c){

        AlarmManager manager = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);


        Intent alarmIntent = new Intent(c, NotificationCountAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(c, 0, alarmIntent, 0);

        manager.cancel(pendingIntent);
    }


}
