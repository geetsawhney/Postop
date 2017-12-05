package com.oose.postop;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmReceiver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Omotola on 10/20/2017.
 */

public class GcmBroadcastReceiver extends GcmReceiver {

    private static final String TAG = "GCMReceiver";

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


