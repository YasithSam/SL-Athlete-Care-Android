package com.example.slathletecare.casestudy;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slathletecare.R;
import com.example.slathletecare.activity.CaseStudyActivity;
import com.example.slathletecare.casestudy.inner.DietActivity;
import com.example.slathletecare.casestudy.inner.ImageActivity;
import com.example.slathletecare.casestudy.inner.MedicineActivity;
import com.example.slathletecare.casestudy.inner.WorkoutActivity;
import com.example.slathletecare.databinding.FragmentHomeBinding;
import com.example.slathletecare.databinding.FragmentPostBinding;
import com.example.slathletecare.databinding.FragmentPreBinding;


public class PreFragment extends Fragment {
    CardView c1,c2,c3,c4;
    private FragmentPreBinding binding;
    Bundle bundle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        binding= FragmentPreBinding.inflate(inflater, container, false);
        View v=binding.getRoot();
        c1=v.findViewById(R.id.m);
        c2=v.findViewById(R.id.a);
        c3=v.findViewById(R.id.d1);
        c4=v.findViewById(R.id.d3);
        bundle=getArguments();

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MedicineActivity.class);
                myIntent.putExtra("id", bundle.getInt("id"));
                startActivity(myIntent);

            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), ImageActivity.class);
                myIntent.putExtra("id", bundle.getInt("id"));
                startActivity(myIntent);

            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MedicineActivity.class);
                myIntent.putExtra("id", bundle.getInt("id"));
                startActivity(myIntent);

            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), WorkoutActivity.class);
                myIntent.putExtra("id", bundle.getInt("id"));
                startActivity(myIntent);
            }
        });
        return v;
    }



}