package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.slathletecare.ArticleAdapter;
import com.example.slathletecare.R;
import com.example.slathletecare.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QuestionAllActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private QuestionAdapter mAdapter;
    private List<Article> list = new ArrayList<>();
    TextView id1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_all);
        id1=findViewById(R.id.qid);
        getSupportActionBar().hide();
        recyclerView=findViewById(R.id.rv_qq);
        id1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuestionAllActivity.this,QuestionOneActivity.class));
            }
        });
        Intent i = getIntent();
        Bundle args = i.getBundleExtra("data");
        list = (ArrayList<Article>) args.getSerializable("ARRAYLIST");
        mAdapter = new QuestionAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}