package com.example.slathletecare.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.ArticleAdapter;
import com.example.slathletecare.MessageAdapter;
import com.example.slathletecare.R;
import com.example.slathletecare.databinding.FragmentHomeBinding;
import com.example.slathletecare.model.Articles;
import com.example.slathletecare.model.Conversation;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private ArticleAdapter mAdapter;
    private List<Articles> aList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = (RecyclerView) root.findViewById(R.id.rv_a);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        mAdapter = new ArticleAdapter(aList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareData();
        return root;
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}