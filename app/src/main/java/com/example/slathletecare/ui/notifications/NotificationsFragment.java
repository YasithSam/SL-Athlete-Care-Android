package com.example.slathletecare.ui.notifications;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.MessageAdapter;
import com.example.slathletecare.MessageProfile;
import com.example.slathletecare.R;
import com.example.slathletecare.activity.ArticleOneActivity;
import com.example.slathletecare.activity.ItemAllActivity;
import com.example.slathletecare.activity.LoginActivity;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.databinding.FragmentNotificationsBinding;
import com.example.slathletecare.model.Conversation;
import com.example.slathletecare.ui.MessageOneActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {
    private List<Conversation> cList = new ArrayList<>();
    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private RecyclerView recyclerView;
    private MessageAdapter mAdapter;
    LinearLayout l1,l2,l3,l4;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        l1=root.findViewById(R.id.l1);
        l2=root.findViewById(R.id.l2);
        l3=root.findViewById(R.id.l3);
        l4=root.findViewById(R.id.l11);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MessageOneActivity.class);
                myIntent.putExtra("sport","Rugby");
                myIntent.putExtra("Id","3");
                startActivity(myIntent);

            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MessageOneActivity.class);
                myIntent.putExtra("sport","Football");
                myIntent.putExtra("Id","4");
                startActivity(myIntent);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MessageOneActivity.class);
                myIntent.putExtra("sport","Cricket");
                myIntent.putExtra("Id","1");
                startActivity(myIntent);
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MessageOneActivity.class);
                myIntent.putExtra("sport","Athletics");
                myIntent.putExtra("Id","2");
                startActivity(myIntent);
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