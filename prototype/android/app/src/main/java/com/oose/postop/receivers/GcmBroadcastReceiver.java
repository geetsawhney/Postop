package com.oose.postop.receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.gcm.GcmReceiver;
import com.oose.postop.R;
import com.oose.postop.activities.MainActivity;

/**Receives push messages from GCM
 * Created by Omotola on 10/20/2017.
 */

public class GcmBroadcastReceiver extends GcmReceiver {

    private static final String TAG = "GCMReceiver";

    /**
     * Invoked on receive of a message
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        Bundle extras = intent.getExtras();


        String message;
        if (extras.containsKey("message")) {
            message = extras.getString("message");
        } else {
            message = "default message";
        }

        final Notification.Builder builder = new Notification.Builder(context)
                .setContentTitle(context.getResources().getString(R.string.app_name))
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);

        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.putExtras(extras);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(resultPendingIntent);

        final NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.getNotification());
    }


    }


