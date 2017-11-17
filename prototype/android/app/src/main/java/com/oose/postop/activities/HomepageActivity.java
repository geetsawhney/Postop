package com.oose.postop.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
import com.oose.postop.dao.DeviceIdDAO;
import com.oose.postop.R;

import java.text.DateFormat;
import java.util.concurrent.TimeUnit;

import az.plainpie.PieView;

/**Activity Class for Homepage
 * Created by Omotola, Rohit on 10/23/2017.
 */

public class HomepageActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    public static final String TAG = "BasicHistoryApi";
    private static final String AUTH_PENDING = "auth_state_pending";
    private static boolean authInProgress = false;

    public static GoogleApiClient googleApiClient = null;
    public static int dailyTotalCount = 0;
    public static int dailyCaloriesExpended = 0;
    public String id;
    String email;

    /**
     *initialize activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        if (savedInstanceState != null) {
            authInProgress = savedInstanceState.getBoolean(AUTH_PENDING);
        }

        //Display Welcome Text
        String name = getIntent().getExtras().getString("name");
        email = getIntent().getExtras().getString("email");
        String address = getIntent().getExtras().getString("address");
        id = getIntent().getExtras().getString("id");
        TextView welcomeTextview = (TextView) findViewById(R.id.welcomeText);
        welcomeTextview.setText("Welcome, " + name + "\n Email: " + email + "\n Address: " + address);
        welcomeTextview.setTextSize(20);

        buildFitnessClient();
    }

    /**
     *Invoked after resume
     */
    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    /**
     *Invoked on restart
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }


    /**
     * Initializes google api client
     */
    private void buildFitnessClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
                .addConnectionCallbacks(this)
                .enableAutoManage(this, 0, this)
                .build();
    }

    /**
     * Invoked once connected to the client
     * @param bundle
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "Connected");
        new ReadFitnessData().execute();
    }

    /**
     * Asynchronous task to connect to google fit api and retrieve fitness data in the background
     */
    private class ReadFitnessData extends AsyncTask<Void, Void, Void> {

        /**
         * Invoked in background once this class is started
         * @param params
         * @return
         */
        protected Void doInBackground(Void... params) {
            getFitnessDataForToday();
            return null;
        }

        /**
         * Invoked after the background tasks is finishes
         * @param aVoid
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            displayChart(fetchFitData(dailyTotalCount,10000));
        }
    }

    /**
     * retrieves the raw fitness data
     */
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


    /**
     * Invoked when connection is suspended
     * @param i
     */
    @Override
    public void onConnectionSuspended(int i) {
        if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
            Log.i(TAG, "Connection lost. Cause: Network Lost.");
        } else if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
            Log.i(TAG, "Connection lost. Reason: Service Disconnected");
        }
    }

    /**
     * Invoked when connection fails
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Google Play services connection failed. Cause: " +
                connectionResult.toString());
    }

    /**
     * Converts raw data into graph data
     */
    public float fetchFitData(int steps, int total){
        int stepCount = steps;
        int totalCount = total;
        float angle = ((float)stepCount/(float)totalCount)*360;
        return angle;
    }

    /**
     * Display google fit data
     * @param angle
     */
    public void displayChart(float angle){
        PieView pieView = (PieView) findViewById(R.id.pieView);
        pieView.setInnerText(dailyTotalCount + "\n Steps");
        pieView.setPieAngle(angle);
        pieView.setInnerTextVisibility(1);
        TextView caloriesExpended = (TextView) findViewById(R.id.caloriesExpended);
        caloriesExpended.setText("You have burnt " + dailyCaloriesExpended + " calories!");
        caloriesExpended.setAllCaps(true);
    }


    /**
     * Called when callback button in layout is clicked
     * @param v
     */
    public void callback(View v){
        Intent localIntent = new Intent(HomepageActivity.this, CallbackActivity.class);
        Bundle localBundle = new Bundle();
        localBundle.putString("email",email);
        localIntent.putExtras(localBundle);
        startActivity(localIntent);

    }

    /**
     * Called when logout button is clicked
     * @param v
     */
    public void logout(View v){
        DeviceIdDAO d = new DeviceIdDAO(this);
        d.deleteID(id);
        Intent localIntent = new Intent(HomepageActivity.this, MainActivity.class);
        startActivity(localIntent);


    }

}