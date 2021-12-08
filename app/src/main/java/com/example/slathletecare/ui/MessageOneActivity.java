package com.example.slathletecare.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.slathletecare.R;
import com.example.slathletecare.ui.dashboard.DashboardFragment;

public class MessageOneActivity extends AppCompatActivity {
    ImageView iv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_one);
        getSupportActionBar().hide();
        iv1=findViewById(R.id.b_to_c);


    }
}