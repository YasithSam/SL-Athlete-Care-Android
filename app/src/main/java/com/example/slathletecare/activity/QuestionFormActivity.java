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
import com.example.slathletecare.helper.SessionManager;
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
import java.util.HashMap;

public class QuestionFormActivity extends AppCompatActivity {
    EditText et1,et2;
    Spinner s;
    Button btn1;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_form);
        getSupportActionBar().hide();
        et1=findViewById(R.id.editTextTitleQ);
        et2=findViewById(R.id.editTextDescriptionQ);
        String[] sports = { "Cricket", "Football", "Athletics", "Rugby", "Other" };
        s=findViewById(R.id.spinnerQS);
        btn1=findViewById(R.id.buttonQSubmit);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sports);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        FloatingActionButton fab=findViewById(R.id.f_q_back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                QuestionFormActivity.super.onBackPressed();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i1=s.getSelectedItem().toString();
                String i2=et1.getText().toString();
                String i3=et2.getText().toString();
                if(i2.isEmpty() || i1.isEmpty() || i3.isEmpty()){
                    Toast.makeText(QuestionFormActivity.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }
                else{
                    new QuestionFormActivity.AsyncAddQ().execute(i1,i2,i3);
                }
            }
        });

    }

    private class AsyncAddQ extends AsyncTask<String,String,String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String ... params) {
            try {
                // Enter URL address where your php file resides
                url = new URL(AppConfig.URL_ADD_Q);

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
                sessionManager =  new SessionManager(getApplicationContext());


                // get user data from session
                HashMap<String, String> user = sessionManager.getUserDetails();

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("sport",params[0])
                        .appendQueryParameter("id",user.get(SessionManager.username))
                        .appendQueryParameter("heading", params[1])
                        .appendQueryParameter("desc", params[2]);
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
                    startActivity(new Intent(QuestionFormActivity.this, FAActivity.class));
                    finish();

                }
                else if (obj.getString("status").equals("n")) {
                    Toast.makeText(QuestionFormActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                }
                else if (obj.getString("status").equals("e")){
                    Toast.makeText(QuestionFormActivity.this, "No data entered", Toast.LENGTH_LONG).show();
                }

            }
            catch (JSONException jsonException){

            }


        }

    }
}