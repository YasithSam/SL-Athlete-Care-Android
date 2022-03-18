package com.example.slathletecare.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.slathletecare.R;
import com.example.slathletecare.activity.ArticleOneActivity;
import com.example.slathletecare.activity.ForumOneActivity;
import com.example.slathletecare.activity.HealthActivity;
import com.example.slathletecare.activity.ItemAllActivity;
import com.example.slathletecare.activity.SportActivity;
import com.example.slathletecare.activity.SportsAdapter;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.model.Article;
import com.example.slathletecare.model.ForumEvent;
import com.example.slathletecare.model.Sport;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.slathletecare.AppController.TAG;

public class FormDetailsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ForumAdapter mAdapter;
    private List<ForumEvent> sList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_details);
        getSupportActionBar().hide();
        recyclerView = (RecyclerView) findViewById(R.id.rv_forum);
        FloatingActionButton fab=findViewById(R.id.f_details_back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                FormDetailsActivity.super.onBackPressed();
            }
        });
        mAdapter = new ForumAdapter(sList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClickListener);
        new FormDetailsActivity.AsyncGetForum().execute();

    }
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAbsoluteAdapterPosition();
            ForumEvent cs = sList.get(position);
            Intent myIntent = new Intent(FormDetailsActivity.this, ForumOneActivity.class);
            myIntent.putExtra("h",cs.getTitle());
            myIntent.putExtra("d",cs.getDesc());
            myIntent.putExtra("dd",cs.getDate());
            myIntent.putExtra("i",cs.getInjury());
            myIntent.putExtra("doctor",cs.getDoctor());
            myIntent.putExtra("id",cs.getId());
            startActivity(myIntent);

        }
    };

    private class AsyncGetForum extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_GET_FORUM_DETAILS+"?id=sl-ac-617e516484ac0");

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("data");
                    if (data != null) {
                        JSONObject st=data.getJSONObject(0);
                        ForumEvent s = new ForumEvent(st.getString("id"), st.getString("con"), st.getString("injury"),st.getString("date"),st.getString("status"),st.getString("doctor"),st.getString("comment"),st.getString("description"));
                        sList.add(s);
                        int len = data.length();
                        for (int i=1;i<len;i++){
                            JSONObject f=data.getJSONObject(i);
                            s=new ForumEvent(f.getString("id"), f.getString("con"), f.getString("injury"),f.getString("date"),f.getString("status"),f.getString("doctor"),f.getString("comment"),f.getString("description"));
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