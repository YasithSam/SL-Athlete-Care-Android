package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.developer.kalert.KAlertDialog;
import com.example.slathletecare.OTPActivity;
import com.example.slathletecare.R;
import com.onesignal.OneSignal;


public class IntroActivity extends AppCompatActivity {
    private Button btnLog;
    private Button btnReg;
    //private static final String ONESIGNAL_APP_ID = "b80f6774-4e66-4724-849d-330fccdf7cb1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        btnReg=findViewById(R.id.btnSignUp);
        btnLog=findViewById(R.id.btnLogin);
        getSupportActionBar().hide();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this,SignUpActivity.class));
                finish();
            }
        });
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(IntroActivity.this,LoginActivity.class));
                finish();

            }
        });
        //OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        // OneSignal.initWithContext(this);
        //OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}