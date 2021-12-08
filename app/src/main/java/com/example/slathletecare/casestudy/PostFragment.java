package com.example.slathletecare.casestudy;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slathletecare.R;
import com.example.slathletecare.casestudy.inner.AdviceActivity;
import com.example.slathletecare.casestudy.inner.DietActivity;
import com.example.slathletecare.casestudy.inner.ImageActivity;
import com.example.slathletecare.casestudy.inner.MedicineActivity;
import com.example.slathletecare.casestudy.inner.WorkoutActivity;
import com.example.slathletecare.databinding.FragmentHomeBinding;
import com.example.slathletecare.databinding.FragmentPostBinding;

public class PostFragment extends Fragment {
    CardView c1,c2,c3,c4;
    private FragmentPostBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPostBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        c1=root.findViewById(R.id.aa);
        c2=root.findViewById(R.id.a2);
        c3=root.findViewById(R.id.d11);
        c4=root.findViewById(R.id.w11);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AdviceActivity.class));
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ImageActivity.class));
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DietActivity.class));
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WorkoutActivity.class));
            }
        });
        return  root;
    }



}