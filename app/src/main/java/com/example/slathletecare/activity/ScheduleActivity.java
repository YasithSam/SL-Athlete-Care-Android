package com.example.slathletecare.activity;

import static com.example.slathletecare.AppController.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.slathletecare.R;

import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.casestudy.PreFragment;
import com.example.slathletecare.casestudy.inner.DietActivity;
import com.example.slathletecare.casestudy.inner.DietAdapter;
import com.example.slathletecare.casestudy.inner.WorkoutActivity;
import com.example.slathletecare.casestudy.inner.WorkoutAdapter;
import com.example.slathletecare.model.DietSchedule;
import com.example.slathletecare.model.WorkoutSchedule;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {
    CardView c1,c2,c3,c4;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private DietAdapter mAdapter;
    private List<DietSchedule> mList2 = new ArrayList<>();
    private WorkoutAdapter mAdapter2;
    private List<WorkoutSchedule> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().hide();

        mAdapter = new DietAdapter(mList2);
        recyclerView = findViewById(R.id.my_d_r);
        recyclerView2 = findViewById(R.id.my_w_r);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter2 = new WorkoutAdapter(mList);
        RecyclerView.LayoutManager mLayoutManager2 = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView2.setLayoutManager(mLayoutManager2);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(mAdapter2);
        new ScheduleActivity.AsyncGetMySchedules().execute();
        mAdapter.setOnItemClickListener(onItemClickListener);
        mAdapter2.setOnItemClickListener(onItemClickListener2);
        FloatingActionButton fab=findViewById(R.id.f_schedule);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                ScheduleActivity.super.onBackPressed();
            }
        });


    }
    private View.OnClickListener onItemClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAbsoluteAdapterPosition();
            WorkoutSchedule cs = mList.get(position);
            Intent myIntent = new Intent(ScheduleActivity.this, WorkoutActivity.class);
            myIntent.putExtra("id",cs.getId());
            myIntent.putExtra("desc",cs.getDescription());
            startActivity(myIntent);

        }
    };
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAbsoluteAdapterPosition();
            DietSchedule cs = mList2.get(position);
            Intent myIntent = new Intent(ScheduleActivity.this, DietActivity.class);
            myIntent.putExtra("id",cs.getId());
            myIntent.putExtra("desc",cs.getDescription());
            startActivity(myIntent);

        }
    };
    private class AsyncGetMySchedules extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();


            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_MY_SCHEDULES+"?id="+"sl-ac-617e516484ac0");

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("workout");
                    if (data != null) {
                        JSONObject st=data.getJSONObject(0);
                        WorkoutSchedule m = new WorkoutSchedule(st.getString("id"),st.getString("title"), st.getString("description"));
                        mList.add(m);
                        int len = data.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data.getJSONObject(i);
                            m= new WorkoutSchedule(mt.getString("id"),mt.getString("title"), mt.getString("description"));
                            mList.add(m);
                        }
                    }
                    JSONArray data2 = jsonObj.getJSONArray("diet");
                    if (data2 != null) {
                        JSONObject st=data2.getJSONObject(0);
                        DietSchedule m = new DietSchedule(st.getString("id"),st.getString("title"), st.getString("description"));
                        mList2.add(m);
                        int len = data2.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data2.getJSONObject(i);
                            m= new DietSchedule(mt.getString("id"),mt.getString("title"), mt.getString("description"));
                            mList2.add(m);
                        }
                    }

                } catch (JSONException e) {

                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            mAdapter.notifyDataSetChanged();
            mAdapter2.notifyDataSetChanged();
        }
    }

}