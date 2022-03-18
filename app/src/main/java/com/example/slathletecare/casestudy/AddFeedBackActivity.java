package com.example.slathletecare.casestudy;

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
import com.example.slathletecare.activity.AddSportActivity;
import com.example.slathletecare.activity.SportActivity;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.tabbed.FAActivity;
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
import java.util.stream.Collectors;

public class AddFeedBackActivity extends AppCompatActivity {
    HashMap<Integer,String> spinner=new HashMap<>();
    List<String> types = new ArrayList<String>();
    private Spinner s;
    Button btnf;
    EditText etf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feed_back);
        getSupportActionBar().hide();
        s=(Spinner) findViewById(R.id.spf);
        btnf=findViewById(R.id.btn_f_add);
        etf=findViewById(R.id.et_f);
        FloatingActionButton fab=findViewById(R.id.f_add_f_back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                AddFeedBackActivity.super.onBackPressed();
            }
        });
        spinner.put(1,"Medicine");
        spinner.put(4,"Diet Schedule");
        spinner.put(3,"Workout Schedule");
        List<String> types = new ArrayList<String>(spinner.values());
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, types);

        spinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        s.setAdapter(spinnerAdapter);

        btnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int keyFound=0;
                String sf=etf.getText().toString();
                String value=s.getSelectedItem().toString();
                for ( Integer key : spinner.keySet() )
                {
                    if ( spinner.get(key).equals(value) )
                    {
                        keyFound=key;
                    }
                }
                new AddFeedback().execute(sf,String.valueOf(keyFound));
            }
        });
    }
    private class AddFeedback extends AsyncTask<String,String,String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String ... params) {
            try {
                // Enter URL address where your php file resides
                url = new URL(AppConfig.URL_ADD_FEEDBACKS);

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
                        .appendQueryParameter("case",String.valueOf(getIntent().getIntExtra("id",0)))
                        .appendQueryParameter("state",String.valueOf(getIntent().getIntExtra("type",0)))
                        .appendQueryParameter("id","sl-ac-617e516484ac0")
                        .appendQueryParameter("type", params[1])
                        .appendQueryParameter("feedback", params[0]);
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
                    startActivity(new Intent(AddFeedBackActivity.this, FeedbackActivity.class));

                }
                else if (obj.getString("status").equals("n")) {
                    Toast.makeText(AddFeedBackActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                }
                else if (obj.getString("status").equals("e")){
                    Toast.makeText(AddFeedBackActivity.this, "No data entered", Toast.LENGTH_LONG).show();
                }

            }
            catch (JSONException jsonException){

            }


        }

    }

}