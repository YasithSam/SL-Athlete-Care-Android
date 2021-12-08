package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.slathletecare.R;

public class QuestionAllActivity extends AppCompatActivity {
    TextView id1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_all);
        id1=findViewById(R.id.qid);
        getSupportActionBar().hide();
        id1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuestionAllActivity.this,QuestionOneActivity.class));
            }
        });
    }
}