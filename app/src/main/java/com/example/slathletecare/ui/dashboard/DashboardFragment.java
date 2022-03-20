package com.example.slathletecare.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.developer.kalert.KAlertDialog;
import com.example.slathletecare.R;
import com.example.slathletecare.activity.CaseStudyActivity;
import com.example.slathletecare.activity.ForumNewActivity;
import com.example.slathletecare.activity.InjuryActivity;
import com.example.slathletecare.activity.ProfileImageActivity;
import com.example.slathletecare.activity.ScheduleActivity;
import com.example.slathletecare.databinding.FragmentDashboardBinding;
import com.example.slathletecare.helper.SessionManager;
import com.example.slathletecare.tabbed.FAActivity;
import com.example.slathletecare.ui.FormDetailsActivity;
import com.example.slathletecare.ui.MyDetailsActivity;
import com.example.slathletecare.ui.ProfileActivity;

import java.util.HashMap;

public class DashboardFragment extends Fragment {
    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    SessionManager sessionManager;
    TextView tvName;
    TextView tvSport;
    TextView tvInjury;
    Button btn1;
    CardView c,c1,c2,c3,c5,c6;
    ImageButton imgb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        sessionManager =  new SessionManager(getActivity().getApplicationContext());
        sessionManager.checkLogin();

        // get user data from session
        HashMap<String, String> user = sessionManager.getUserDetails();
        View root = binding.getRoot();
        tvName=root.findViewById(R.id.tvName);

        tvName.setText(user.get(SessionManager.username));
        c=root.findViewById(R.id.cc);
        c1=root.findViewById(R.id.cc2);
        c2=root.findViewById(R.id.cc3);
        c3=root.findViewById(R.id.cc4);
        c5=root.findViewById(R.id.cc5);
        c6=root.findViewById(R.id.cc6);
        btn1=root.findViewById(R.id.btnInjury);
        imgb=root.findViewById(R.id.imageButtonP);
        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProfileImageActivity.class));
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), InjuryActivity.class));
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyDetailsActivity.class));
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FormDetailsActivity.class));
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(),CaseStudyActivity.class));
            }
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),FAActivity.class));
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ScheduleActivity.class));
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ProfileActivity.class));
            }
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}