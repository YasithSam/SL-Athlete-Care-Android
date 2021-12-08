package com.example.slathletecare.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.slathletecare.R;

public class FormDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_details);
        getSupportActionBar().hide();

    }
}