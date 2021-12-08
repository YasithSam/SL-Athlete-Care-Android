package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.slathletecare.R;

public class ArticleOneActivity extends AppCompatActivity {
    ImageView i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_one);
        i1=findViewById(R.id.i_article);
        getSupportActionBar().hide();

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ArticleOneActivity.this,CommentsActivity.class));
            }
        });
    }
}