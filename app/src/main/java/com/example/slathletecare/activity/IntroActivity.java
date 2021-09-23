package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.developer.kalert.KAlertDialog;
import com.example.slathletecare.R;



public class IntroActivity extends AppCompatActivity {
    private Button btnLog;
    private Button btnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        btnReg=findViewById(R.id.btnSignUp);
        btnLog=findViewById(R.id.btnLogin);
        KAlertDialog pDialog = new KAlertDialog(this,KAlertDialog.WARNING_TYPE);
        pDialog.setTitleText("Here's a message!");
        pDialog.setContentText("It's pretty, isn't it?");
        pDialog.setConfirmText("Yes,delete it!");
        pDialog.setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
            @Override
            public void onClick(KAlertDialog sDialog) {
                sDialog.dismissWithAnimation();
            }
        });
        pDialog.show();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this,SignUpActivity.class));
                finish();
            }
        });
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this,LoginActivity.class));
                finish();

            }
        });
    }
}