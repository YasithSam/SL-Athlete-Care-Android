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
import android.widget.TextView;
import android.widget.Toast;

import com.example.slathletecare.R;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.casestudy.CaseStudyItemActivity;
import com.example.slathletecare.model.CaseStudy;
import com.example.slathletecare.model.Sport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.slathletecare.AppController.TAG;

public class CaseStudyActivity extends AppCompatActivity {

    private RecyclerView recyclerView,recyclerViewOld;
    private CaseStudyAdapter mAdapter,mAdapterOld;
    private List<CaseStudy> sList = new ArrayList<>();
    private List<CaseStudy> sListOld = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_study);
        getSupportActionBar().hide();
        recyclerView = (RecyclerView) findViewById(R.id.rv_c_a);
        recyclerViewOld = (RecyclerView) findViewById(R.id.rv_c_p);

        mAdapter = new CaseStudyAdapter(sList);
        mAdapterOld = new CaseStudyAdapter(sListOld);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerViewOld.setLayoutManager(mLayoutManager2);
        recyclerViewOld.setItemAnimator(new DefaultItemAnimator());
        recyclerViewOld.setAdapter(mAdapterOld);
        new CaseStudyActivity.AsyncGetCaseStudy().execute();
        mAdapter.setOnItemClickListener(onItemClickListener);
        mAdapterOld.setOnItemClickListener(onItemClickListener2);




    }
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAbsoluteAdapterPosition();
            CaseStudy cs = sList.get(position);
            Intent myIntent = new Intent(CaseStudyActivity.this, CaseStudyItemActivity.class);
            myIntent.putExtra("id",cs.getId());
            startActivity(myIntent);

        }
    };
    private View.OnClickListener onItemClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAbsoluteAdapterPosition();
            CaseStudy cs = sListOld.get(position);
            Intent myIntent = new Intent(CaseStudyActivity.this, CaseStudyItemActivity.class);
            myIntent.putExtra("id",cs.getId());
            startActivity(myIntent);

        }
    };

    private class AsyncGetCaseStudy extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_CASE_STUDY_ALL+"?id=sl-ac-617e516484ac0");

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("current");
                    if (data != null) {
                        JSONObject st=data.getJSONObject(0);
                        CaseStudy c = new CaseStudy(st.getString("title"), st.getString("description"), st.getInt("case_id"));
                        sList.add(c);
                        int len = data.length();
                        for (int i=1;i<len;i++){
                            JSONObject cs=data.getJSONObject(i);
                            c=new CaseStudy(cs.getString("title"), cs.getString("description"), cs.getInt("case_id"));
                            sList.add(c);
                        }
                    }
                    JSONArray data2 = jsonObj.getJSONArray("old");
                    if (data2 != null) {
                        JSONObject st=data.getJSONObject(0);
                        CaseStudy c = new CaseStudy(st.getString("title"), st.getString("description"), st.getInt("case_id"));
                        sListOld.add(c);
                        int len = data2.length();
                        for (int i=1;i<len;i++){
                            JSONObject cs=data.getJSONObject(i);
                            c=new CaseStudy(cs.getString("title"), cs.getString("description"), cs.getInt("case_id"));
                            sListOld.add(c);
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
            mAdapterOld.notifyDataSetChanged();


        }
    }
}