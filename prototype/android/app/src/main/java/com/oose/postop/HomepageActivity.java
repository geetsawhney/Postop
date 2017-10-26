package com.oose.postop;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import az.plainpie.PieView;

/**
 * Created by Omotola on 10/23/2017.
 */

public class HomepageActivity extends  Activity {

    TextView welcomeTextview;
    String name;
    String email;
    String address;
    int stepCount;
    int totalCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        //Display Welcome Text
        name = getIntent().getExtras().getString("name");
        email = getIntent().getExtras().getString("email");
        address = getIntent().getExtras().getString("address");
        welcomeTextview = (TextView) findViewById(R.id.welcomeText);
        welcomeTextview.setText("Welcome, " + name + "\n Email: " + email + "\n Address: " + address);
        welcomeTextview.setTextSize(20);

        displayChart(fetchFitData(2345,10000));
    }





        //Fetch google fit data
        float fetchFitData(int steps, int total){
            stepCount = steps;
            totalCount = total;
            float angle = ((float)stepCount/(float)totalCount)*360;
            return angle;
        }






     //Display the Google Fit UI
        void displayChart(float angle){

        PieView pieView = (PieView) findViewById(R.id.pieView);
        pieView.setInnerText(stepCount+"\n Steps");
        pieView.setPieAngle(angle);
    }





    public void callback(View v){

    }

}
