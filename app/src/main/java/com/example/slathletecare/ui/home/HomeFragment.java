package com.example.slathletecare.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.ArticleAdapter;
import com.example.slathletecare.MessageAdapter;
import com.example.slathletecare.R;
import com.example.slathletecare.activity.ItemAllActivity;
import com.example.slathletecare.activity.LoginActivity;
import com.example.slathletecare.activity.NoticeAllActivity;
import com.example.slathletecare.activity.NoticeOneActivity;
import com.example.slathletecare.activity.QuestionAllActivity;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.casestudy.PreFragment;
import com.example.slathletecare.databinding.FragmentHomeBinding;
import com.example.slathletecare.model.Article;
import com.example.slathletecare.model.Articles;
import com.example.slathletecare.model.Conversation;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.slathletecare.AppController.TAG;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    CardView cca,ccc,ccf,ccr,cco;
    CardView qa,qc,qf,qr,qo;
    Button btn1;
    ProgressDialog pDialog;
    TextView tv1,tv2;
    ImageView iv11;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cca=root.findViewById(R.id.cca);
        ccc=root.findViewById(R.id.ccc);
        ccf=root.findViewById(R.id.ccf);
        ccr=root.findViewById(R.id.ccr);
        cco=root.findViewById(R.id.cco);

        qa=root.findViewById(R.id.qa);
        qc=root.findViewById(R.id.qc);
        qf=root.findViewById(R.id.qf);
        qr=root.findViewById(R.id.qr);
        qo=root.findViewById(R.id.qo);

        btn1=root.findViewById(R.id.btnN);
        tv1=root.findViewById(R.id.n_h);
        tv2=root.findViewById(R.id.n_d);
        iv11=root.findViewById(R.id.n_i);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),NoticeOneActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });
        cca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),ItemAllActivity.class);
                intent.putExtra("type", 5);
                startActivity(intent);
            }
        });
        ccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),ItemAllActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
            }
        });
        ccf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ItemAllActivity.class);
                intent.putExtra("type", 3);
                startActivity(intent);
            }
        });
        ccr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ItemAllActivity.class);
                intent.putExtra("type", 4);
                startActivity(intent);
            }
        });
        cco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),ItemAllActivity.class);
                intent.putExtra("type", 6);
                startActivity(intent);
            }
        });
        qc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),QuestionAllActivity.class);
                intent.putExtra("type", 7);
                startActivity(intent);
            }
        });
        qa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),QuestionAllActivity.class);
                intent.putExtra("type", 9);
                startActivity(intent);
            }
        });
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),QuestionAllActivity.class);
                intent.putExtra("type", 10);
                startActivity(intent);
            }
        });
        qf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),QuestionAllActivity.class);
                intent.putExtra("type", 8);
                startActivity(intent);
            }
        });
        qo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),QuestionAllActivity.class);
                intent.putExtra("type", 11);
                startActivity(intent);
            }
        });

        return root;
    }


}