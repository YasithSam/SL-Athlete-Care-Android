package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slathletecare.R;
import com.example.slathletecare.model.Image;
import com.squareup.picasso.Picasso;

public class ArticleOneActivity extends AppCompatActivity {
    ImageView i1,i2,i3;
    TextView t1,t2,t3,t4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_one);
        i1=findViewById(R.id.i_article);
        i2=findViewById(R.id.imageView10);
        i3=findViewById(R.id.imageViewAOne);
        getSupportActionBar().hide();
        t1=findViewById(R.id.textViewAOne);
        t2=findViewById(R.id.textViewAOneD);
        t3=findViewById(R.id.textViewAOneL);
        t4=findViewById(R.id.textViewAOneC);

        Intent myIntent = getIntent();
        String h = myIntent.getStringExtra("h");
        String d = myIntent.getStringExtra("d");
        String l = myIntent.getStringExtra("l");
        String c = myIntent.getStringExtra("c");
        Picasso.get().load(myIntent.getStringExtra("i")).into(i3);
        t1.setText(h);
        t2.setText(d);
        t3.setText(l);
        t4.setText(c);
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t3.setText(String.valueOf(Integer.parseInt(t3.getText().toString())+1));

            }
        });
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ArticleOneActivity.this,CommentsActivity.class);
                myIntent.putExtra("id",myIntent.getStringExtra("id"));
                startActivity(myIntent);
            }
        });
    }
}