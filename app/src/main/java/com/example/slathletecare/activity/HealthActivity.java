package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.slathletecare.helper.SessionManager;
import com.example.slathletecare.ui.MyDetailsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.slathletecare.AppController.TAG;

public class HealthActivity extends AppCompatActivity {
    FloatingActionButton f1;
    TextView tv1,tv2,tv3;
    Button btq;
    public ArrayList<String> hList=new ArrayList<>();
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        getSupportActionBar().hide();
        tv1=findViewById(R.id.tv_height_v);
        tv2=findViewById(R.id.tv_weight_v);
        tv3=findViewById(R.id.tv_fat_v);
        btq=findViewById(R.id.buttongg);
        FloatingActionButton fab=findViewById(R.id.bb_health);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                HealthActivity.super.onBackPressed();
            }
        });

        btq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthActivity.this,HealthEditActivity.class));

            }
        });

        new HealthActivity.AsyncGetHealth().execute();



    }
    private class AsyncGetHealth extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            sessionManager =  new SessionManager(getApplicationContext());

            HashMap<String, String> user = sessionManager.getUserDetails();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_GHEALTH+"?id="+user.get(SessionManager.userId));

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("data");
                    if (data != null) {
                        int len = data.length();
                        for (int i=0;i<len;i++){
                            hList.add(data.get(i).toString());
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

            tv1.setText(hList.get(0)+" m");
            tv2.setText(hList.get(1)+" KG");
            tv3.setText(hList.get(2));


        }


    }

}