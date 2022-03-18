package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.slathletecare.ArticleAdapter;
import com.example.slathletecare.R;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.model.Articles;
import com.example.slathletecare.model.Sport;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.slathletecare.AppController.TAG;

public class SportActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SportsAdapter mAdapter;
    private List<Sport> sList = new ArrayList<>();
    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        recyclerView = (RecyclerView) findViewById(R.id.rv_sports);
        getSupportActionBar().hide();
        bt1=findViewById(R.id.button222);
        FloatingActionButton fab=findViewById(R.id.b_sport);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                SportActivity.super.onBackPressed();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SportActivity.this,AddSportActivity.class));
            }
        });
        mAdapter = new SportsAdapter(sList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        new AsyncGetSports().execute();
    }

    private class AsyncGetSports extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_SPORTS+"?id=sl-ac-617e516484ac0");

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("data");
                    if (data != null) {
                        JSONObject st=data.getJSONObject(0);
                        Sport s = new Sport(st.getString("name"), st.getString("institution"), st.getString("level"));
                        sList.add(s);
                        int len = data.length();
                        for (int i=1;i<len;i++){
                            JSONObject sport=data.getJSONObject(i);
                            s=new Sport(sport.getString("name"), sport.getString("institution"), sport.getString("level"));
                            sList.add(s);
                        }
                    }

                } catch (JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            mAdapter.notifyDataSetChanged();


        }
    }


}