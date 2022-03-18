package com.example.slathletecare.tabbed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.slathletecare.R;
import com.example.slathletecare.activity.SportActivity;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.model.Article;
import com.example.slathletecare.model.Sport;
import com.example.slathletecare.ui.FormDetailsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.slathletecare.AppController.TAG;

public class FAActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tab1;
    TabItem tab2;
    private List<Article> aList = new ArrayList<>();
    private List<Article> qList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faactivity);
        tabLayout = findViewById(R.id.tab);
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        viewPager = findViewById(R.id.viewPager);
        getSupportActionBar().hide();
        FloatingActionButton fab=findViewById(R.id.f_article_back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                FAActivity.super.onBackPressed();
            }
        });
        new FAActivity.AsyncGet().execute();
        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(),aList,qList);

        viewPager.setAdapter(pageAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private class AsyncGet extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_GET_FORUM+"?id=sl-ac-617e516484ac0");

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    //p.heading,p.type,p.description,p.approval_status,p.datetime,p.likes,p.comments,a.url
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("articles");
                    if (data != null) {
                        JSONObject st=data.getJSONObject(0);
                        Article s = new Article(st.getString("id"),st.getString("heading"), st.getString("description"), st.getString("datetime"),st.getString("likes"),st.getString("comments"),st.getString("url"),st.getString("approval_status"));
                        aList.add(s);
                        int len = data.length();
                        for (int i=1;i<len;i++){
                            JSONObject a=data.getJSONObject(i);
                            s=new Article(a.getString("id"),a.getString("heading"), a.getString("description"), a.getString("datetime"),a.getString("likes"),a.getString("comments"),a.getString("url"),a.getString("approval_status"));
                            aList.add(s);
                        }
                    }
                    JSONArray data2 = jsonObj.getJSONArray("questions");
                    if (data2 != null) {
                        JSONObject st=data2.getJSONObject(0);
                        Article s = new Article(st.getString("id"),st.getString("heading"), st.getString("description"), st.getString("datetime"),st.getString("likes"),st.getString("comments"),st.getString("url"),st.getString("approval_status"));
                        qList.add(s);
                        int len = data2.length();
                        for (int i=1;i<len;i++){
                            JSONObject a=data2.getJSONObject(i);
                            s=new Article(a.getString("id"),a.getString("heading"), a.getString("description"), a.getString("datetime"),a.getString("likes"),a.getString("comments"),a.getString("url"),a.getString("approval_status"));
                            qList.add(s);
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

        }
    }

}