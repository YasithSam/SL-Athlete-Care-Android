package com.example.slathletecare.casestudy.inner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.model.DietEvent;
import com.example.slathletecare.model.Sport;
import com.example.slathletecare.model.WorkoutEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.slathletecare.AppController.TAG;

public class DietActivity extends AppCompatActivity {
    ImageButton arrow,a2;
    LinearLayout hiddenView,l2;
    TextView td2;
    CardView cardView,c2;
    private RecyclerView recyclerView;
    private DietEventsAdapter mAdapter;
    private List<DietEvent> sList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        cardView = findViewById(R.id.cccxd);
        arrow = findViewById(R.id.arrow_buttond);
        hiddenView = findViewById(R.id.l3);
        getSupportActionBar().hide();
        recyclerView=findViewById(R.id.xx);
        td2=findViewById(R.id.tddddx);
        td2.setText(getIntent().getStringExtra("desc"));
        mAdapter = new DietEventsAdapter(sList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        new AsyncGetDietEvents().execute();
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
    private class AsyncGetDietEvents extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_DIET_EVENTS+"?id="+getIntent().getStringExtra("id"));

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("data");
                    if (data != null) {
                        JSONObject st=data.getJSONObject(0);
                            DietEvent s = new DietEvent(st.getString("title"), st.getString("description"), st.getString("amount"));
                            sList.add(s);
                            int len = data.length();
                            for (int i = 1; i < len; i++) {
                                JSONObject s2 = data.getJSONObject(i);
                                s = new DietEvent(s2.getString("title"), s2.getString("description"), s2.getString("amount"));
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