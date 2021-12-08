package com.example.slathletecare.tabbed;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.slathletecare.R;
import com.example.slathletecare.activity.ForumNewActivity;

public class ForumFragment extends Fragment {
    Button btn2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v=inflater.inflate(R.layout.fragment_forum, container, false);
        btn2=v.findViewById(R.id.btnX2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ForumNewActivity.class));
            }
        });
        return v;
    }





}