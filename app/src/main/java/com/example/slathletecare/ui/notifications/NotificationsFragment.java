package com.example.slathletecare.ui.notifications;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.slathletecare.R;
import com.example.slathletecare.activity.LoginActivity;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.databinding.FragmentNotificationsBinding;
import com.example.slathletecare.model.Conversation;

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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = (RecyclerView) root.findViewById(R.id.rv_chat);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        mAdapter = new MessageAdapter(cList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        return root;
    }
    private class AsyncChat extends AsyncTask<String,String,String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String ... params) {
            try {
                // Enter URL address where your php file resides
                url = new URL(AppConfig.URL_CHAT);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("uuid", params[0]);

                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            try{
                JSONObject obj = new JSONObject(result);
                if (obj.getString("status").equals("ok")) {
                    prepareMovieData(obj);



                } else if (obj.getString("status").equals("p")) {

                    // If username and password does not match display a error message
                    //Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();

                } else if (obj.getString("status").equals("n")) {

                    //Toast.makeText(LoginActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                }

            }
            catch (JSONException jsonException){

            }


        }

    }
    private void prepareMovieData(JSONObject jsonObj) {
        try {
            // Getting JSON Array node
            JSONArray contacts = jsonObj.getJSONArray("contacts");
            JSONObject c = contacts.getJSONObject(0);
            int id = Integer.parseInt(c.getString("id"));
            String from = c.getString("from");
            String top = c.getString("top");
            Conversation chat=new Conversation(id,from,top);
            cList.add(chat);
            // looping through All Contacts
            for (int i = 1; i < contacts.length(); i++) {
                c = contacts.getJSONObject(i);
                id =Integer.parseInt(c.getString("id"));
                from = c.getString("name");
                top = c.getString("top");
                chat=new Conversation(id,from,top);
                cList.add(chat);
            }
            mAdapter.notifyDataSetChanged();
        } catch (final JSONException e) {
            Log.e("tag", "Json parsing error: " + e.getMessage());
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Json parsing error: " + e.getMessage(),
                            Toast.LENGTH_LONG)
                            .show();
                }
            });

        }



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}