package com.oose.postop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {


    EditText email, password;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

//        login.setOnClickListener()
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }


           private void userLogin() {
               //first getting the values
               final String username = email.getText().toString();
               final String pass = password.getText().toString();
               //validating inputs
               if (TextUtils.isEmpty(username)) {
                   email.setError("Please enter your username");
                   email.requestFocus();
                   return;
               }

               if (TextUtils.isEmpty(pass)) {
                   password.setError("Please enter your password");
                   password.requestFocus();
                   return;
               }

               //if everything is fine

                class UserLogin extends AsyncTask<Void, Void, String> {

                   ProgressBar progressBar;

                   @Override
                   protected void onPreExecute() {
                       super.onPreExecute();
                       progressBar = (ProgressBar) findViewById(R.id.progressBar);
                       progressBar.setVisibility(View.VISIBLE);
                   }

                   @Override
                   protected void onPostExecute(String s) {
                       super.onPostExecute(s);
                       progressBar.setVisibility(View.GONE);


                       try {
                           //converting response to json object
                           JSONObject obj = new JSONObject(s);

                           //if no error in response
                           if (!obj.getBoolean("error")) {
                               Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                               //getting the user from the response
                               JSONObject userJson = obj.getJSONObject("user");

                               //creating a new user object
                               User user = new User(

                                       userJson.getString("username"),
                                       userJson.getString("password")

                               );

                               //storing the user in shared preferences
                               SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                               //starting the profile activity
                               finish();
                               startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                           } else {
                               Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();

                       }
                   }

                       @Override
                       protected String doInBackground(Void... voids) {
                           //creating request handler object
                           RequestHandler requestHandler = new RequestHandler();

                           //creating request parameters
                           HashMap<String, String> params = new HashMap<>();
                           params.put("username", username);
                           params.put("password", pass);

                           //returing the response
                           return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
                       }
                   }

                   UserLogin ul = new UserLogin();
        ul.execute();


               }
       }
