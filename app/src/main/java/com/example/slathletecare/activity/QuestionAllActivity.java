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
import android.widget.TextView;
import android.widget.Toast;

import com.example.slathletecare.ArticleAdapter;
import com.example.slathletecare.R;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.model.Article;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.slathletecare.AppController.TAG;

public class QuestionAllActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private QuestionAdapter mAdapter;
    private List<Article> list = new ArrayList<>();
    TextView id1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_all);

        getSupportActionBar().hide();
        recyclerView=findViewById(R.id.rv_qq);
        new QuestionAllActivity.AsyncGetAll().execute();
        mAdapter = new QuestionAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener(onItemClickListener);
    }
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAbsoluteAdapterPosition();
            Article cs = list.get(position);
            Intent myIntent = new Intent(QuestionAllActivity.this, QuestionOneActivity.class);
            myIntent.putExtra("h",cs.getHeading());
            myIntent.putExtra("d",cs.getDescription());
            myIntent.putExtra("id",cs.getId());
            startActivity(myIntent);

        }
    };
    private class AsyncGetAll extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();


            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_GET_ALL+"?type="+String.valueOf(getIntent().getIntExtra("type",0)));

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("data");
                    if (data != null) {
                        JSONObject st=data.getJSONObject(0);
                        Article s = new Article(st.getString("id"),st.getString("heading"),st.getString("description"), st.getString("datetime"),st.getString("likes"),st.getString("comments"),st.getString("url"),st.getString("status").equals("1")?st.getString("status"):"");
                        list.add(s);
                        int len = data.length();
                        for (int i=1;i<len;i++){
                            JSONObject t=data.getJSONObject(i);
                            s=new Article(t.getString("id"),t.getString("heading"),t.getString("description"), t.getString("datetime"),t.getString("likes"),t.getString("comments"),t.getString("url"),t.getString("status").equals("1")?t.getString("status"):"");
                            list.add(s);
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