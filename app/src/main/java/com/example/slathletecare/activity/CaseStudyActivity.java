package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.slathletecare.R;
import com.example.slathletecare.casestudy.CaseStudyItemActivity;

public class CaseStudyActivity extends AppCompatActivity {
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_study);
        getSupportActionBar().hide();
        tv1=findViewById(R.id.tv_c22);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(CaseStudyActivity.this, CaseStudyItemActivity.class));
            }
        });
    }
}