package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.slathletecare.R;
import com.example.slathletecare.app.AppConfig;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddSportActivity extends AppCompatActivity {
    Spinner spin,spin2;
    EditText eI;
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sport);
        getSupportActionBar().hide();
        eI=findViewById(R.id.et_i);
        bt1=findViewById(R.id.btn_s_add);
        FloatingActionButton fab=findViewById(R.id.b_sport_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                AddSportActivity.super.onBackPressed();
            }
        });
        String[] sports = { "Cricket", "Football", "Athletics", "Rugby", "Other" };
        spin = (Spinner) findViewById(R.id.sp1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sports);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        String[] l = { "U-23", "Club", "National", "U-19"};
        spin2 = (Spinner) findViewById(R.id.sp5);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,l);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i1=spin.getSelectedItem().toString();
                String i2=eI.getText().toString();
                String i3=spin2.getSelectedItem().toString();
                if(i2.isEmpty()){
                    Toast.makeText(AddSportActivity.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }
                else{
                    new AsyncAddSport().execute(i1,i2,i3);
                }
            }
        });



    }
    private class AsyncAddSport extends AsyncTask<String,String,String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String ... params) {
            try {
                // Enter URL address where your php file resides
                url = new URL(AppConfig.URL_ADD_SPORT);

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
//                $id=$_REQUEST['sport_id'];
//                $a=$_REQUEST['athlete_id'];
//                $i=$_REQUEST['institution'];
//                $l=$_REQUEST['level'];
//                $c=$_REQUEST['category'];

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("sport",params[0])
                        .appendQueryParameter("athlete_id","sl-ac-617e516484ac0")
                        .appendQueryParameter("institution", params[1])
                        .appendQueryParameter("level", params[2]);
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
                    startActivity(new Intent(AddSportActivity.this,SportActivity.class));

                }
                else if (obj.getString("status").equals("n")) {
                    Toast.makeText(AddSportActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                }
                else if (obj.getString("status").equals("e")){
                    Toast.makeText(AddSportActivity.this, "No data entered", Toast.LENGTH_LONG).show();
                }

            }
            catch (JSONException jsonException){

            }


        }

    }
}