package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.slathletecare.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PrivacyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        FloatingActionButton fab=findViewById(R.id.fab_privacy);
        getSupportActionBar().hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                PrivacyActivity.super.onBackPressed();
            }
        });
    }
}