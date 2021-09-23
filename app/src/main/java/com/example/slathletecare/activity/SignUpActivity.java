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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.slathletecare.AppController;
import com.example.slathletecare.OTPActivity;
import com.example.slathletecare.R;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.model.accountRegister;
import com.google.gson.JsonObject;

import java.io.BufferedOutputStream;
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

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = SignUpActivity.class.getSimpleName();
    private Button btnRegister;
    private EditText inputUsername;
    private EditText inputPhone;
    private EditText inputPassword;
    private EditText inputConfirmPassword;
    private String name;
    private String phone;
    private String password;
    private String confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        inputUsername = findViewById(R.id.username);
        inputPhone = findViewById(R.id.phone);
        inputPassword=findViewById(R.id.password);
        inputConfirmPassword=findViewById(R.id.confirmPassword);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = inputUsername.getText().toString().trim();
                phone = inputPhone.getText().toString().trim();
                password = inputPassword.getText().toString().trim();
                confirmPassword= inputConfirmPassword.getText().toString().trim();

                if (validateUser(name,password,phone)) {
                    if(!(password.equals(confirmPassword))){
                        Toast.makeText(getApplicationContext(),"Passwords are not matching",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        new AsyncOTP().execute(name,phone,password);
                    }


                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details correctly!", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });





    }
    private class AsyncOTP extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL(AppConfig.OTP_REQUEST);

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
                        .appendQueryParameter("phone", params[1]);
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
            if (!result.isEmpty()) {
                accountRegister user=new accountRegister(name,phone,password,result);
                Intent intent = new Intent(SignUpActivity.this, OTPActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
            } else if (result.equalsIgnoreCase("false")) {

                // If username and password does not match display a error message
                Toast.makeText(SignUpActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(SignUpActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

            }
        }
    }

    private void generateOTP(String name,String phone,String password){
        HttpURLConnection urlConnection = null;

        // POST
        try {
            JsonObject postData = new JsonObject();
            postData.addProperty("name", name);
            postData.addProperty("job", phone);

            URL url = new URL(AppConfig.OTP_REQUEST);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setChunkedStreamingMode(0);
            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    out, "UTF-8"));
            writer.write(postData.toString());
            writer.flush();

            int code = urlConnection.getResponseCode();
            if (code !=  201) {
                throw new IOException("Invalid response from server: " + code);
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                Log.i("data", line);
            }
            /*accountRegister user=new accountRegister(name,phone,password,response);
            Intent intent = new Intent(SignUpActivity.this, OTPActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
            finish();*/


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }




    }


    private boolean validateUserName(String name){

        if (name.isEmpty()) {
            inputUsername.setError("Field cannot be empty");
            return false;
        } else if (name.length() >= 15) {
            inputUsername.setError("Username too long");
            return false;
        } else if (name.contains(" ")) {
            inputUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            inputUsername.setError(null);
            return true;
        }
    }
    private boolean validatePhone(String phone){
        if (phone.isEmpty()) {
            inputPhone.setError("Field cannot be empty");
            return false;
        }else if(phone.length()>10 | phone.length()<10){
            inputPhone.setError("Invalid number");
            return false;
        }
        else {
            inputPhone.setError(null);
            return true;
        }

    }
    private boolean validatePassword(String password){

        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{6,}" +               //at least 6 characters
                "$";

        if (password.isEmpty()) {
            inputPassword.setError("Field cannot be empty");
            return false;
        } else if (!password.matches(passwordVal)) {
            inputPassword.setError("Password is too weak");
            return false;
        } else {
            inputPassword.setError(null);
            return true;
        }

    }
    private boolean validateUser(String name, String password, String phone){
        if(validateUserName(name)&&validatePassword(password)&&validatePhone(phone)){
            return true;
        }
        else{
            return false;
        }

    }
}