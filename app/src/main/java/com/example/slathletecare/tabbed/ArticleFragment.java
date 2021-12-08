package com.example.slathletecare.tabbed;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.slathletecare.R;
import com.example.slathletecare.activity.ArticleFormActivity;
import com.example.slathletecare.databinding.FragmentArticleBinding;
import com.example.slathletecare.databinding.FragmentHomeBinding;


public class ArticleFragment extends Fragment {
    Button btn1;
    private FragmentArticleBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v= inflater.inflate(R.layout.fragment_article, container, false);
        btn1=v.findViewById(R.id.btnX);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ArticleFormActivity.class));
            }
        });
        return v;
    }



}