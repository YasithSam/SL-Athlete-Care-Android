package com.example.slathletecare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slathletecare.activity.LoginActivity;
import com.example.slathletecare.app.AppConfig;

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

public class ForgetPassword extends AppCompatActivity {
    EditText fUsername;
    EditText fCPassword;
    EditText fNPassword;
    Button btnChange;
    Button btnBack;
    String username;
    String cPassword;
    String nPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        fUsername=findViewById(R.id.mFUserName);
        fCPassword=findViewById(R.id.mFCPassword);
        fNPassword=findViewById(R.id.mFNPassword);
        btnBack=findViewById(R.id.btnBack);
        btnChange=findViewById(R.id.btnForgetPassword);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPassword.this, LoginActivity.class));
            }
        });
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=fUsername.getText().toString();
                cPassword=fCPassword.getText().toString();
                nPassword=fNPassword.getText().toString();
                new AsyncForget().execute(username,cPassword,nPassword);
            }
        });

    }
    private class AsyncForget extends AsyncTask<String,String,String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String... params) {
            try {
                // Enter URL address where your php file resides
                url = new URL(AppConfig.URL_FORGET);

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
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("cpassword", params[1])
                        .appendQueryParameter("npassword", params[2]);
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

            try {
                JSONObject obj = new JSONObject(result);
                if (obj.getString("status").equals("ok")) {
                    //obj.getString("data")
                    startActivity(new Intent(ForgetPassword.this,LoginActivity.class));

                } else if (obj.getString("status").equals("error")) {

                    // If username and password does not match display a error message
                    Toast.makeText(ForgetPassword.this, "Could not update user", Toast.LENGTH_LONG).show();

                } else if (obj.getString("status").equals("errorUser")) {

                    Toast.makeText(ForgetPassword.this, "No user found with given data", Toast.LENGTH_LONG).show();

                }

            } catch (JSONException jsonException) {

            }


        }

    }

}