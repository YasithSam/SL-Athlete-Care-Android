package com.example.slathletecare.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.developer.kalert.KAlertDialog;
import com.example.slathletecare.ForgetPassword;
import com.example.slathletecare.R;
import com.example.slathletecare.activity.InjuryActivity;
import com.example.slathletecare.activity.LoginActivity;
import com.example.slathletecare.helper.SessionManager;

public class ProfileActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        t1=findViewById(R.id.tv_edit);
        t2=findViewById(R.id.tv_change);
        t3=findViewById(R.id.tv_lgout);
        getSupportActionBar().hide();

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,EditProfileActivity.class));
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 startActivity(new Intent(ProfileActivity.this, ForgetPassword.class));
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logoutUser();




            }
        });
    }

}