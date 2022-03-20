package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slathletecare.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class NoticeAllActivity extends AppCompatActivity {
    ImageView i3;
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_all);
        Intent myIntent = getIntent();
        getSupportActionBar().hide();
        String h = myIntent.getStringExtra("heading");
        String d = myIntent.getStringExtra("description");
        String u = myIntent.getStringExtra("url");
        i3=findViewById(R.id.ivNoticeUrl);
        t1=findViewById(R.id.tv_Notice_Heading);
        t2=findViewById(R.id.tv_Notice_Desc);
        if(!myIntent.getStringExtra("url").isEmpty()){
            Picasso.get().load(myIntent.getStringExtra("url")).into(i3);
        }
        t1.setText(h);
        t2.setText(d);
        FloatingActionButton fab=findViewById(R.id.f_notice_back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                NoticeAllActivity.super.onBackPressed();
            }
        });



    }
}