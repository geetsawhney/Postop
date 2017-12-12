package com.oose.postop.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.oose.postop.R;

import java.io.IOException;

/**Service for Registering device to GCM and receiving token
 * Created by Omotola on 10/18/2017.
 * Credit to Simplified Coding; https://www.simplifiedcoding.net/android-push-notification-using-gcm-tutorial/#Creating-GCMPushReceiverService
 */

public class RegistrationIntentService extends IntentService {
    public static final String REGISTRATION_SUCCESS = "RegistrationSuccess";
    public static final String REGISTRATION_ERROR = "RegistrationError";

    private static final String TAG = "RegIntentService";
    public RegistrationIntentService() {
        super(TAG);
    }

    /**
     * processes request
     * @param intent
     */
    @Override
    public void onHandleIntent(Intent intent) {
        //Registration complete intent initially null
        Intent registrationComplete = null;

        String token = null;
        try {
            //Creating an instanceid
            InstanceID instanceID = InstanceID.getInstance(getApplicationContext());

            //Getting the token from the instance id
            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            //Displaying the token in the log so that we can copy it to send push notification

            Log.w("GCMRegIntentService", "token:" + token);

            //on registration complete creating intent with success
            registrationComplete = new Intent(REGISTRATION_SUCCESS);

            //Putting the token to the intent
            registrationComplete.putExtra("token", token);
        } catch (Exception e) {
            //If any error occurred
            Log.w("GCMRegIntentService", "Registration error");
            registrationComplete = new Intent(REGISTRATION_ERROR);
        }

        //Sending the broadcast that registration is completed

        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);


        // ...
    }

}
