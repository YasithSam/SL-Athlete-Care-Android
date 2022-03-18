package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slathletecare.R;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.helper.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class HealthEditActivity extends AppCompatActivity {
    EditText et1,et2,et3;
    Button bt1;
    SessionManager sessionManager;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_edit);
        et1=findViewById(R.id.et_height);
        et2=findViewById(R.id.et_weight);
        et3=findViewById(R.id.et_fat);
        bt1=findViewById(R.id.btn_health_add);
        tv=findViewById(R.id.tvx);
        getSupportActionBar().hide();

        sessionManager =  new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetails();
        String id=user.get(SessionManager.userId);
        FloatingActionButton fab=findViewById(R.id.h_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                HealthEditActivity.super.onBackPressed();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i1=et1.getText().toString();
                String i2=et2.getText().toString();
                String i3=et3.getText().toString();
                new AsyncAdd().execute(id,i1,i2,i3);
            }
        });


    }
    private class AsyncAdd extends AsyncTask<String,String,String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String ... params) {
            try {
                // Enter URL address where your php file resides
                url = new URL(AppConfig.URL_ADD_HEALTH);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("id",params[0])
                        .appendQueryParameter("height", params[1])
                        .appendQueryParameter("weight", params[2])
                        .appendQueryParameter("fat", params[3]);
                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            try{
                JSONObject obj = new JSONObject(result);
                if (obj.getString("status").equals("ok")) {
                    startActivity(new Intent(HealthEditActivity.this,HealthActivity.class));

                }
                 else if (obj.getString("status").equals("n")) {
                    Toast.makeText(HealthEditActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                }
                else if (obj.getString("status").equals("e")){
                    Toast.makeText(HealthEditActivity.this, "No data entered", Toast.LENGTH_LONG).show();
                }

            }
            catch (JSONException jsonException){

            }


        }

    }
}