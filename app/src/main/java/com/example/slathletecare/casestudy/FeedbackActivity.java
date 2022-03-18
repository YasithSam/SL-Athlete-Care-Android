package com.example.slathletecare.casestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.slathletecare.R;
import com.example.slathletecare.activity.SportActivity;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.casestudy.inner.DietActivity;
import com.example.slathletecare.model.Sport;
import com.example.slathletecare.model.feedback;
import com.example.slathletecare.tabbed.FAActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.slathletecare.AppController.TAG;

public class FeedbackActivity extends AppCompatActivity {
    SearchView searchView;
    public static ArrayList<feedback> fList = new ArrayList<feedback>();
    private ListView listView;
    Button btn4;
    private String selectedFilter = "all";
    private String currentSearchText = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().hide();
        FloatingActionButton fab=findViewById(R.id.f_feedback_back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                FeedbackActivity.super.onBackPressed();
            }
        });
        new AsyncGetFeedbacks().execute();
        setUpList();
        btn4=findViewById(R.id.button444);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(FeedbackActivity.this, AddFeedBackActivity.class);
                myIntent.putExtra("id",getIntent().getIntExtra("id",0));
                myIntent.putExtra("type",getIntent().getIntExtra("type",0));
                startActivity(myIntent);
            }
        });
    }
    private class AsyncGetFeedbacks extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            int c=getIntent().getIntExtra("id",0);
            int s=getIntent().getIntExtra("type",0);

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_GET_FEEDBACKS+"?case="+c+"&&status="+s+"&&user="+"sl-ac-617e516484ac0");

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("data");

                    if (data != null) {
                        JSONObject st=data.getJSONObject(0);
                        feedback f = new feedback(st.getString("type"), st.getString("feedback"), st.getString("datetime")+" Hours Ago");
                        fList.add(f);
                        int len = data.length();
                        for (int i=1;i<len;i++){
                            JSONObject ft=data.getJSONObject(i);
                            f=new feedback(ft.getString("type"), ft.getString("feedback"), ft.getString("datetime")+" Hours Ago");
                            fList.add(f);
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
            setUpList();

        }
    }

    private void setUpList()
    {
        listView = (ListView) findViewById(R.id.fListView);

        FeedbackAdapter adapter = new FeedbackAdapter(getApplicationContext(), 0, fList);
        listView.setAdapter(adapter);
    }


    private void filterList(String status)
    {
        selectedFilter = status;

        ArrayList<feedback> filteredF = new ArrayList<feedback>();

        for(feedback f: fList)
        {
            if(f.getType().toLowerCase().contains(status))
            {
                filteredF.add(f);

            }
        }

        FeedbackAdapter adapter = new FeedbackAdapter(getApplicationContext(), 0, filteredF);
        listView.setAdapter(adapter);
    }


    public void allFilterTapped(View view)
    {
        selectedFilter = "all";

        FeedbackAdapter adapter = new FeedbackAdapter(getApplicationContext(), 0,fList);
        listView.setAdapter(adapter);
    }

    public void pFilterTapped(View view)
    {
        filterList("3");
    }

    public void dFilterTapped(View view)
    {
        filterList("2");
    }

    public void nFilterTapped(View view)
    {
        filterList("5");
    }


}