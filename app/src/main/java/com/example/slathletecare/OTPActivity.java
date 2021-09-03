package com.example.slathletecare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.slathletecare.activity.LoginActivity;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.AppController;
import com.example.slathletecare.model.accountRegister;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OTPActivity extends AppCompatActivity {
    private static final String TAG = OTPActivity.class.getSimpleName();
    private accountRegister user;
    private TextView tvPhone;
    private Button btnVerify;
    private EditText pinView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);
        user= (accountRegister) getIntent().getSerializableExtra("user");
        tvPhone=findViewById(R.id.otp_description_text);
        btnVerify=findViewById(R.id.btnVerify);
        pinView=findViewById(R.id.pin_view);
        tvPhone.setText(user.getUsername());

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOtp(pinView.getText().toString().trim());
            }
        });

    }

    private void verifyOtp(String pinView){
        String tag_string_otp = "req_otp";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.OTP_REQUEST, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    if(response.toString().equals(pinView)){
                        registerUser(user.getUsername(),user.getPassword(),user.getPhone());

                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Wrong OTP", Toast.LENGTH_LONG).show();
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
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_otp);
    }

    private void registerUser(String username,String password,String phone){
            // Tag used to cancel the request
            String tag_string_req = "req_register";

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    AppConfig.URL_REGISTER, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Register Response: " + response.toString());

                    try {
                        JSONObject jObj = new JSONObject(response);
                        boolean error = jObj.getBoolean("error");
                        if (!error) {
                            Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                            // Launch login activity
                            Intent intent = new Intent(
                                   OTPActivity.this,
                                    LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {

                            // Error occurred in registration. Get the error
                            // message

                            Toast.makeText(getApplicationContext(),
                                    "Enable to register the user", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
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
                    params.put("name", username);
                    params.put("password", password);
                    params.put("phone", phone);

                    return params;
                }

            };

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        }


    }


