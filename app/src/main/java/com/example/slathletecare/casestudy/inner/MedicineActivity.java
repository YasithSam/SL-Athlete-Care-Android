package com.example.slathletecare.casestudy.inner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slathletecare.R;
import com.example.slathletecare.activity.SportActivity;
import com.example.slathletecare.activity.SportsAdapter;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.model.Medicine;
import com.example.slathletecare.model.Sport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.slathletecare.AppController.TAG;

public class MedicineActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MedicineAdapter mAdapter;
    private List<Medicine> mList = new ArrayList<>();
     TextView tvH,tvD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        getSupportActionBar().hide();
        tvH=findViewById(R.id.tv_m_h);
        tvD=findViewById(R.id.tv_m_d);
        mAdapter = new MedicineAdapter(mList);
        recyclerView = (RecyclerView) findViewById(R.id.rv_m_m);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        new MedicineActivity.AsyncGetMedicine().execute();

    }
    private class AsyncGetMedicine extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            int id=getIntent().getIntExtra("id",0);
            int state=getIntent().getIntExtra("type",0);

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_CASE_STUDY_MEDICINE+"?id="+id+"&&type="+state);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("data");
                    if (data != null) {
                        JSONObject st=data.getJSONObject(0);
                        Medicine m = new Medicine(st.getString("heading"), st.getString("description"));
                        mList.add(m);
                        int len = data.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data.getJSONObject(i);
                            m= new Medicine(mt.getString("heading"), mt.getString("description"));
                            mList.add(m);
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