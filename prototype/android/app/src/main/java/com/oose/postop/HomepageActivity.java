package com.oose.postop;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;


import az.plainpie.PieView;

/**
 * Created by Omotola, Rohit on 10/23/2017.
 */

public class HomepageActivity extends AppCompatActivity{
    String id;
    GoogleFitFetch g = new GoogleFitFetch();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);



        //Display Welcome Text
        String name = getIntent().getExtras().getString("name");
        String email = getIntent().getExtras().getString("email");
        String address = getIntent().getExtras().getString("address");
        id= getIntent().getExtras().getString("id");
        TextView welcomeTextview = (TextView) findViewById(R.id.welcomeText);
        welcomeTextview.setText("Welcome, " + name + "\n Email: " + email + "\n Address: " + address);
        welcomeTextview.setTextSize(20);

        g.buildFitnessClient(getApplicationContext());
        displayChart(g.angle,g.dailyTotalCount,g.dailyCaloriesExpended);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }



    public void callback(View v){

    }


    public void logout(View v){
        DeviceIdDAO d = new DeviceIdDAO(id, getApplicationContext());
        d.deleteID();
        finish();
        System.exit(0);
    }
    //Display the Google Fit UI
    public void displayChart(float angle,int dailyTotalCount, int dailyCaloriesExpended){
        PieView pieView = (PieView) findViewById(R.id.pieView);
        pieView.setInnerText(dailyTotalCount + "\n Steps");
        pieView.setPieAngle(angle);
        pieView.setInnerTextVisibility(1);
        TextView caloriesExpended = (TextView) findViewById(R.id.caloriesExpended);
        caloriesExpended.setText("You have burnt " + dailyCaloriesExpended + " calories!");
        caloriesExpended.setAllCaps(true);
    }
}
