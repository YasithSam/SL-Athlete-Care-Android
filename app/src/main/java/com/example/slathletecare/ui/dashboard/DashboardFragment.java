package com.example.slathletecare.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.developer.kalert.KAlertDialog;
import com.example.slathletecare.R;
import com.example.slathletecare.databinding.FragmentDashboardBinding;
import com.example.slathletecare.helper.SessionManager;

import java.util.HashMap;

public class DashboardFragment extends Fragment {
    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    SessionManager sessionManager;
    TextView tvName;
    TextView tvSport;
    TextView tvInjury;

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
        tvInjury=root.findViewById(R.id.tvInjuryName);
        tvSport=root.findViewById(R.id.tvSport);
        tvName.setText(user.get(SessionManager.username));


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}