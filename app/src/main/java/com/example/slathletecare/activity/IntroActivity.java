package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.slathletecare.R;

public class IntroActivity extends AppCompatActivity {
    private Button btnLog;
    private Button btnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        btnLog=findViewById(R.id.btnReg);
        btnReg=findViewById(R.id.btnLogin);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this,SignUpActivity.class));
                finish();
            }
        });
    }
}