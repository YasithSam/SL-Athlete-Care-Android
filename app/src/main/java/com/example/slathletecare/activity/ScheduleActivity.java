package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.slathletecare.R;

import com.example.slathletecare.casestudy.inner.DietActivity;
import com.example.slathletecare.casestudy.inner.WorkoutActivity;

public class ScheduleActivity extends AppCompatActivity {
    CardView c1,c2,c3,c4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().hide();
        c1=findViewById(R.id.c1);
        c3=findViewById(R.id.w1);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScheduleActivity.this, DietActivity.class));
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScheduleActivity.this, WorkoutActivity.class));
            }
        });


    }

}