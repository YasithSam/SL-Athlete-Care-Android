package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slathletecare.ArticleAdapter;
import com.example.slathletecare.R;
import com.example.slathletecare.casestudy.CaseStudyItemActivity;
import com.example.slathletecare.model.Article;
import com.example.slathletecare.model.Articles;
import com.example.slathletecare.model.CaseStudy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArticleAdapter mAdapter;
    private List<Article> list = new ArrayList<>();
    TextView tv1,tv2;
    ImageView i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_all);
        recyclerView = (RecyclerView) findViewById(R.id.rv_1);
        getSupportActionBar().hide();
        i1=findViewById(R.id.iv_i_ii);
        tv1=findViewById(R.id.tv_i_hh);
        tv2=findViewById(R.id.tv_i_dd);
        Intent i = getIntent();
        Bundle args = i.getBundleExtra("data");
        list = (ArrayList<Article>) args.getSerializable("ARRAYLIST");
        Picasso.get().load(list.get(0).getUrl()).into(i1);
        tv1.setText(list.get(0).getHeading());
        tv2.setText(list.get(0).getDescription());
        mAdapter = new ArticleAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener(onItemClickListener);

    }
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAbsoluteAdapterPosition();
            Article cs = list.get(position);
            Intent myIntent = new Intent(ItemAllActivity.this, ArticleOneActivity.class);
            myIntent.putExtra("h",cs.getHeading());
            myIntent.putExtra("d",cs.getDescription());
            myIntent.putExtra("l",cs.getLikes());
            myIntent.putExtra("k",cs.getComments());
            myIntent.putExtra("i",cs.getUrl());
            myIntent.putExtra("id",cs.getId());
            startActivity(myIntent);

        }
    };

}