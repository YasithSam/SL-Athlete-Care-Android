package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.slathletecare.R;

public class QuestionOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_one);
        getSupportActionBar().hide();
    }
}