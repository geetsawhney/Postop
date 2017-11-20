package com.oose.postop;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.result.DailyTotalResult;

import java.text.DateFormat;
import java.util.concurrent.TimeUnit;


/**
 * Created by Omotola on 11/9/2017.
 */

public class GoogleFitFetch implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    public static final String TAG = "BasicHistoryApi";
    private static final String AUTH_PENDING = "auth_state_pending";
    private static boolean authInProgress = false;

    public static GoogleApiClient googleApiClient = null;
    public static int dailyTotalCount = 0;
    public static int dailyCaloriesExpended = 0;
    public static float angle = 0;

 /*if (savedInstanceState != null) {
        authInProgress = savedInstanceState.getBoolean(AUTH_PENDING);
    }*/


    public void buildFitnessClient(Context c) {
        googleApiClient = new GoogleApiClient.Builder(c)
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
                .addConnectionCallbacks(this)
               // .enableAutoManage(this, 0, this)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "Connected");
        new ReadFitnessData().execute();
    }

    private class ReadFitnessData extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            getFitnessDataForToday();
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            fetchFitData(dailyTotalCount,5000);
        }
    }

    public void getFitnessDataForToday(){
        DailyTotalResult resultSteps = Fitness.HistoryApi.
                readDailyTotal(googleApiClient, DataType.TYPE_STEP_COUNT_DELTA).await(1, TimeUnit.MINUTES);
        DataSet dataSet = resultSteps.getTotal();

        DateFormat dateFormat = DateFormat.getDateTimeInstance();

        for(DataPoint dp: dataSet.getDataPoints()){
            Log.i(TAG, "Data point:");
            Log.i(TAG, "\tType: " + dp.getDataType().getName());
            Log.i(TAG, "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            Log.i(TAG, "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)));
            for(Field field: dp.getDataType().getFields()){
                Log.i(TAG, "\tField: " + field.getName() +
                        " Value: " + dp.getValue(field));
                dailyTotalCount = dp.getValue(field).asInt();
                Log.i(TAG, " Total Steps " + dailyTotalCount);

            }
        }

        DailyTotalResult resultCalories = Fitness.HistoryApi.
                readDailyTotal(googleApiClient, DataType.TYPE_CALORIES_EXPENDED).await(1, TimeUnit.MINUTES);
        dataSet = resultCalories.getTotal();

        for(DataPoint dp: dataSet.getDataPoints()){
            Log.i(TAG, "Data point:");
            Log.i(TAG, "\tType: " + dp.getDataType().getName());
            Log.i(TAG, "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            Log.i(TAG, "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)));
            for(Field field: dp.getDataType().getFields()){
                Log.i(TAG, "\tField: " + field.getName() +
                        " Value: " + dp.getValue(field));
                dailyCaloriesExpended = (int)dp.getValue(field).asFloat();
                Log.i(TAG, " Calories Expended " + dailyCaloriesExpended);

            }
        }
    }


    @Override
    public void onConnectionSuspended(int i) {
        if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
            Log.i(TAG, "Connection lost. Cause: Network Lost.");
        } else if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
            Log.i(TAG, "Connection lost. Reason: Service Disconnected");
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Google Play services connection failed. Cause: " +
                connectionResult.toString());
    }

    //Fetch google fit data
    public void fetchFitData(int steps, int total){
        int stepCount = steps;
        int totalCount = total;
        angle = ((float)stepCount/(float)totalCount)*360;
        //return angle;
    }


}
