package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.slathletecare.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CommentsActivity extends AppCompatActivity {
    FloatingActionButton f8c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        getSupportActionBar().hide();

    }
}