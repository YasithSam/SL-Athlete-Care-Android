package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slathletecare.ForgetPassword;
import com.example.slathletecare.MainActivity;
import com.example.slathletecare.R;
import com.example.slathletecare.TAG;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.helper.SessionManager;

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

public class LoginActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    private Button btnLogin;
    private String username;
    private String password;
    private TextView btnForget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername=findViewById(R.id.mUserName);
        mPassword=findViewById(R.id.mPassword);
        btnLogin=findViewById(R.id.btnSignIn);
        btnForget=findViewById(R.id.tvForget);

        btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPassword.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = mUsername.getText().toString().trim();
                password = mPassword.getText().toString().trim();
                new AsyncLogin().execute(username,password);
            }
        });


    }

   private class AsyncLogin extends AsyncTask<String,String,String> {
       HttpURLConnection conn;
       URL url = null;

       @Override
       protected String doInBackground(String ... params) {
           try {
               // Enter URL address where your php file resides
               url = new URL(AppConfig.URL_LOGIN);

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
                       .appendQueryParameter("password", params[1]);
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
                   //obj.getString("data")
                   login(result);


               } else if (obj.getString("status").equals("p")) {

                   // If username and password does not match display a error message
                   Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();

               } else if (obj.getString("status").equals("n")) {

                   Toast.makeText(LoginActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

               }

           }
           catch (JSONException jsonException){

           }


       }

   }
    public void login(String result){
        try{
            // Session Manager
             SessionManager session = new SessionManager(getApplicationContext());
            JSONObject obj = new JSONObject(result);
            session.createLoginSession(obj.getString("uuid"),obj.getString("username"));
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        catch (JSONException jsonException){
            Toast.makeText(LoginActivity.this, "OOPs! Something went wrong.", Toast.LENGTH_LONG).show();
        }


    }
}