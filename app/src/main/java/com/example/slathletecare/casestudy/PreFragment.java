package com.example.slathletecare.casestudy;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slathletecare.R;
import com.example.slathletecare.activity.CaseStudyActivity;
import com.example.slathletecare.casestudy.inner.AdviceAdapter;
import com.example.slathletecare.casestudy.inner.DietActivity;
import com.example.slathletecare.casestudy.inner.DietAdapter;
import com.example.slathletecare.casestudy.inner.ImageActivity;
import com.example.slathletecare.casestudy.inner.MedicineActivity;
import com.example.slathletecare.casestudy.inner.WorkoutActivity;
import com.example.slathletecare.casestudy.inner.WorkoutAdapter;
import com.example.slathletecare.databinding.FragmentHomeBinding;
import com.example.slathletecare.databinding.FragmentPostBinding;
import com.example.slathletecare.databinding.FragmentPreBinding;
import com.example.slathletecare.model.Advice;
import com.example.slathletecare.model.DietSchedule;
import com.example.slathletecare.model.WorkoutSchedule;

import java.util.ArrayList;
import java.util.List;


public class PreFragment extends Fragment {
    CardView c1,c2,c3,c4;
    private FragmentPreBinding binding;
    Bundle bundle;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private DietAdapter mAdapter;
    private List<DietSchedule> mList = new ArrayList<>();
    private WorkoutAdapter mAdapter2;
    private List<WorkoutSchedule> mList2 = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        binding= FragmentPreBinding.inflate(inflater, container, false);
        View v=binding.getRoot();
        c1=v.findViewById(R.id.m);
        c2=v.findViewById(R.id.a);
        bundle=getArguments();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MedicineActivity.class);
                myIntent.putExtra("id", bundle.getInt("id"));
                myIntent.putExtra("type", 0);
                startActivity(myIntent);

            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), ImageActivity.class);
                myIntent.putExtra("id", bundle.getInt("id"));
                myIntent.putExtra("type",0);
                startActivity(myIntent);

            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MedicineActivity.class);
                myIntent.putExtra("id", bundle.getInt("id"));
                myIntent.putExtra("type", 0);
                startActivity(myIntent);

            }
        });

        return v;
    }



}