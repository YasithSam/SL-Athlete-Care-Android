package com.example.slathletecare.tabbed;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.slathletecare.R;
import com.example.slathletecare.activity.ArticleFormActivity;
import com.example.slathletecare.activity.ArticleOneActivity;
import com.example.slathletecare.activity.ItemAllActivity;
import com.example.slathletecare.activity.MyArticleAdapter;
import com.example.slathletecare.activity.QuestionAdapter;
import com.example.slathletecare.activity.QuestionAllActivity;
import com.example.slathletecare.model.Article;

import java.util.ArrayList;
import java.util.List;


public class ArticleFragment extends Fragment {
    Button btn1;
    private RecyclerView recyclerView;
    private MyArticleAdapter mAdapter;
    private List<Article> list = new ArrayList<>();
    Bundle bundle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v= inflater.inflate(R.layout.fragment_article, container, false);
        btn1=v.findViewById(R.id.btnX);
        recyclerView=v.findViewById(R.id.r_m_a);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ArticleFormActivity.class));
            }
        });
        bundle=getArguments();
        list = (ArrayList<Article>) bundle.getSerializable("ARRAYLIST");
        mAdapter = new MyArticleAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener(onItemClickListener2);
        return v;
    }

    private View.OnClickListener onItemClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAbsoluteAdapterPosition();
            Article cs = list.get(position);
            Intent myIntent = new Intent(getActivity(), ArticleOneActivity.class);
            myIntent.putExtra("h",cs.getHeading());
            myIntent.putExtra("d",cs.getDescription());
            myIntent.putExtra("l",cs.getLikes());
            myIntent.putExtra("c",cs.getComments());
            myIntent.putExtra("i",cs.getUrl());
            myIntent.putExtra("id",cs.getId());
            myIntent.putExtra("delete",1);
            startActivity(myIntent);

        }
    };



}