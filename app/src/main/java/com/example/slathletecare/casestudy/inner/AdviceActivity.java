package com.example.slathletecare.casestudy.inner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.slathletecare.R;

public class AdviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        getSupportActionBar().hide();
    }
}