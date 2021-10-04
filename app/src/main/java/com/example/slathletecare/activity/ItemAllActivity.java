package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.slathletecare.ArticleAdapter;
import com.example.slathletecare.R;
import com.example.slathletecare.model.Articles;

import java.util.ArrayList;
import java.util.List;

public class ItemAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArticleAdapter mAdapter;
    private List<Articles> aList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_all);
        recyclerView = (RecyclerView) findViewById(R.id.rv_1);
        getSupportActionBar().hide();

        mAdapter = new ArticleAdapter(aList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareData();
    }
    private void prepareData() {
        Articles data = new Articles("How to overcome leg injuries", "There are few things that are very importatnt ....", 23,2);
        aList.add(data);

        data= new Articles("Best place to buy a knee cap", "My coach advised me to go for health care in rajagiriya ....", 34,1);
        aList.add(data);

        data= new Articles("Best place to buy a knee cap", "My coach advised me to go for health care in rajagiriya ....", 34,1);
        aList.add(data);

        data= new Articles("Best place to buy a knee cap", "My coach advised me to go for health care in rajagiriya ....", 34,1);
        aList.add(data);

        mAdapter.notifyDataSetChanged();
    }

}