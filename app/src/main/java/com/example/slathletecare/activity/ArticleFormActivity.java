package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.slathletecare.R;

public class ArticleFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_form);
        getSupportActionBar().hide();
    }
}