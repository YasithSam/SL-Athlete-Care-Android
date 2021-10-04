package com.example.slathletecare;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.slathletecare.activity.InjuryActivity;
import com.example.slathletecare.activity.IntroActivity;
import com.example.slathletecare.activity.LoginActivity;
import com.example.slathletecare.activity.SignUpActivity;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run() {

                Intent i = new Intent(SplashScreen.this, LoginActivity.class);

                startActivity(i);

                // close this activity

                finish();

            }

        }, 5000); // wait for 5 seconds

    }
}