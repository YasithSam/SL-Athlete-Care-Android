package com.example.slathletecare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteCallbackList;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.developer.kalert.KAlertDialog;
import com.example.slathletecare.activity.LoginActivity;
import com.example.slathletecare.activity.SignUpActivity;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.AppController;
import com.example.slathletecare.model.accountRegister;

import org.json.JSONArray;
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
import java.util.Map;

public class OTPActivity extends AppCompatActivity {
    private static final String TAG = OTPActivity.class.getSimpleName();
    private accountRegister user;
    private TextView tvPhone;
    private Button btnVerify;
    private EditText pinView;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);
        user= (accountRegister) getIntent().getSerializableExtra("user");
        tvPhone=findViewById(R.id.otp_description_text);
        btnVerify=findViewById(R.id.btnVerify);
        pinView=findViewById(R.id.pin_view);

        getSupportActionBar().hide();

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOtp(pinView.getText().toString().trim(),user);
            }
        });

    }

    private void verifyOtp(String pinView,accountRegister user){
        if(pinView.equals(user.getOtp())){
            new AsyncRegister().execute(user.getUsername(),user.getPhone(),user.getPassword(),user.getEmail(), user.getGender());
        }
        else{
            Toast.makeText(getApplicationContext(),"Not Matching password",Toast.LENGTH_SHORT).show();
        }

    }
    private class AsyncRegister extends AsyncTask<String,String,String> {
        HttpURLConnection conn;
        URL url = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(OTPActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }


        @Override
        protected String doInBackground(String ... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL(AppConfig.URL_REGISTER);

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
                        .appendQueryParameter("name", params[0])
                        .appendQueryParameter("password", params[2])
                        .appendQueryParameter("phone",params[1])
                        .appendQueryParameter("sex",params[4])
                        .appendQueryParameter("email",params[3]);
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
            if (pDialog.isShowing())
                pDialog.dismiss();
            if (result.equals("Success")) {
                // Launch login activity
                Intent intent = new Intent(
                        OTPActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
            } else if (result.equalsIgnoreCase("false")) {

                // If username and password does not match display a error message
                Toast.makeText(OTPActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(OTPActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

            }
        }
    }


    private void registerUser(accountRegister user){
            // Tag used to cancel the request
            String tag_string_req = "req_register";

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    AppConfig.URL_REGISTER, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    try {
                        String s=response;

                        if (s.equals("Success")) {
                            Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();
                            new KAlertDialog(OTPActivity.this, KAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Verified!")
                                    .setContentText("User has been created Successfully!")
                                    .show();
                            // Launch login activity
                            Intent intent = new Intent(
                                   OTPActivity.this,
                                    LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {

                            // Error occurred in registration. Get the error
                            // message

                            Log.e(TAG,"fuck");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Registration Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();

                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting params to register url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", user.getUsername());
                    params.put("password", user.getPassword());
                    params.put("phone", user.getPhone());

                    return params;
                }

            };

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        }


    }


