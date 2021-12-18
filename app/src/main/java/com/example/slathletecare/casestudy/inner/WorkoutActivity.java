package com.example.slathletecare.casestudy.inner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slathletecare.R;
import com.example.slathletecare.activity.CaseStudyActivity;
import com.example.slathletecare.activity.SportActivity;
import com.example.slathletecare.activity.SportsAdapter;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.casestudy.CaseStudyItemActivity;
import com.example.slathletecare.model.CaseStudy;
import com.example.slathletecare.model.Sport;
import com.example.slathletecare.model.WorkoutEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.slathletecare.AppController.TAG;

public class WorkoutActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WorkoutEventsAdapter mAdapter;
    private List<WorkoutEvent> sList = new ArrayList<>();
    ImageButton arrow;
    LinearLayout hiddenView;
    CardView cardView;
    TextView tdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        recyclerView = (RecyclerView) findViewById(R.id.r_e_w);
        tdd = findViewById(R.id.tdddd);
        tdd.setText(getIntent().getStringExtra("desc"));
        getSupportActionBar().hide();
        mAdapter = new WorkoutEventsAdapter(sList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        new AsyncGetWorkoutEvents().execute();
        mAdapter.setOnItemClickListener(onItemClickListener);


//        arrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // If the CardView is already expanded, set its visibility
//                //  to gone and change the expand less icon to expand more.
//                if (hiddenView.getVisibility() == View.VISIBLE) {
//                    TransitionManager.beginDelayedTransition(cardView,
//                            new AutoTransition());
//                    hiddenView.setVisibility(View.GONE);
//                    arrow.setImageResource(R.drawable.ic_baseline_expand_more_24);
//                }
//
//                // If the CardView is not expanded, set its visibility
//                // to visible and change the expand more icon to expand less.
//                else {
//
//                    TransitionManager.beginDelayedTransition(cardView,
//                            new AutoTransition());
//                    hiddenView.setVisibility(View.VISIBLE);
//                    arrow.setImageResource(R.drawable.ic_baseline_expand_less_24);
//                }
//            }
//        });
//        a2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // If the CardView is already expanded, set its visibility
//                //  to gone and change the expand less icon to expand more.
//                if (l2.getVisibility() == View.VISIBLE) {
//                    TransitionManager.beginDelayedTransition(c2,
//                            new AutoTransition());
//                    l2.setVisibility(View.GONE);
//                    a2.setImageResource(R.drawable.ic_baseline_expand_more_24);
//                }
//
//                // If the CardView is not expanded, set its visibility
//                // to visible and change the expand more icon to expand less.
//                else {
//
//                    TransitionManager.beginDelayedTransition(c2,
//                            new AutoTransition());
//                    l2.setVisibility(View.VISIBLE);
//                    a2.setImageResource(R.drawable.ic_baseline_expand_less_24);
//                }
//            }
//        });
    }
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAbsoluteAdapterPosition();
            if (hiddenView.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(cardView,
                            new AutoTransition());
                    hiddenView.setVisibility(View.GONE);

                }

                else {

                    TransitionManager.beginDelayedTransition(cardView,
                            new AutoTransition());
                    hiddenView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.ic_baseline_expand_less_24);
                }


        }
    };

        private class AsyncGetWorkoutEvents extends AsyncTask<Void,Void,Void> {

            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();

                // Making a request to url and getting response
                String jsonStr = sh.makeServiceCall(AppConfig.URL_WORKOUT_EVENTS+"?id="+getIntent().getStringExtra("id"));

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        // Getting JSON Array node
                        JSONArray data = jsonObj.getJSONArray("data");
                        if (data != null) {
                            JSONObject st=data.getJSONObject(0);
                            int len = data.length();
                            if(st.getString("reps").equals("0")) {
                                WorkoutEvent s = new WorkoutEvent(st.getString("title"), st.getString("description"),"Time",st.getString("time"));
                                sList.add(s);
                                //addEvents(len,data,s);

                            }
                            else{
                                WorkoutEvent s = new WorkoutEvent(st.getString("title"), st.getString("description"),"Reps",st.getString("reps"));
                                sList.add(s);
                                //addEvents(len,data,s);
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
//            public void addEvents(int len , JSONArray data, WorkoutEvent s){
//                try {
//                    for (int i = 1; i < len; i++) {
//                        JSONObject s2 = data.getJSONObject(i);
//                        if (s2.getString("reps").equals("0")) {
//                            s = new WorkoutEvent(s2.getString("title"), s2.getString("description"), "Time", s2.getString("time"));
//                            sList.add(s);
//                        } else {
//                            s = new WorkoutEvent(s2.getString("title"), s2.getString("description"), "Reps", s2.getString("reps"));
//                            sList.add(s);
//                        }
//
//                    }
//                }
//                catch (Exception e){
//
//                }
//            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                // Dismiss the progress dialog
                mAdapter.notifyDataSetChanged();


            }
        }

}
