package com.example.slathletecare;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.slathletecare.activity.ArticleOneActivity;
import com.example.slathletecare.activity.CaseStudyActivity;
import com.example.slathletecare.activity.HealthActivity;
import com.example.slathletecare.activity.HealthEditActivity;
import com.example.slathletecare.activity.InjuryActivity;
import com.example.slathletecare.activity.IntroActivity;
import com.example.slathletecare.activity.LoginActivity;
import com.example.slathletecare.activity.SignUpActivity;
import com.example.slathletecare.casestudy.CaseStudyItemActivity;
import com.example.slathletecare.casestudy.inner.DietActivity;
import com.example.slathletecare.casestudy.inner.WorkoutActivity;
import com.example.slathletecare.tabbed.FAActivity;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run() {

                Intent i = new Intent(SplashScreen.this, IntroActivity.class);

                startActivity(i);

                // close this activity

                finish();

            }

        }, 5000); // wait for 5 seconds

    }
}