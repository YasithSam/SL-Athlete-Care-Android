package com.example.slathletecare.ui;

import static com.example.slathletecare.AppController.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slathletecare.MessageAdapter;
import com.example.slathletecare.R;
import com.example.slathletecare.activity.CommentAdapter;
import com.example.slathletecare.activity.CommentsActivity;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.model.Comment;
import com.example.slathletecare.model.Conversation;
import com.example.slathletecare.ui.dashboard.DashboardFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MessageOneActivity extends AppCompatActivity {
    ImageView iv1;
    TextView t1;
    private RecyclerView recyclerView;
    private MessageAdapter mAdapter;
    private List<Conversation> sList = new ArrayList<>();
    Timer t;
    CardView c1;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_one);
        getSupportActionBar().hide();
        iv1=findViewById(R.id.b_to_c);
        Intent myIntent = getIntent();
        String s = myIntent.getStringExtra("sport");
        c1=findViewById(R.id.cardViewMessage);
        et=findViewById(R.id.editTextMessage);
        t1=findViewById(R.id.textView39);
        t1.setText(s);
        recyclerView=findViewById(R.id.r_messages);
        mAdapter = new MessageAdapter(sList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                new MessageOneActivity.AsyncGetMessages().execute();
            }
        }, 1000);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i= myIntent.getStringExtra("Id");
                String i2=et.getText().toString();
                new MessageOneActivity.AsyncAddMessage().execute(i,i2);
            }
        });

    }
    private class AsyncAddMessage extends AsyncTask<String,String,String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String ... params) {
            try {
                // Enter URL address where your php file resides
                url = new URL(AppConfig.URL_ADD_MESSAGES);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("id",params[0])
                        .appendQueryParameter("message",params[1]);

                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            try{
                JSONObject obj = new JSONObject(result);
                if (obj.getString("status").equals("ok")) {
                    Intent myIntentOld = getIntent();

                    Intent myIntent = new Intent(MessageOneActivity.this, MessageOneActivity.class);
                    myIntent.putExtra("sport", myIntentOld.getStringExtra("sport"));
                    myIntent.putExtra("Id", myIntentOld.getStringExtra("Id"));
                    startActivity(myIntent);
                    finish();
                }
                else if (obj.getString("status").equals("n")) {
                    Toast.makeText(MessageOneActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                }
                else if (obj.getString("status").equals("e")){
                    Toast.makeText(MessageOneActivity.this, "No data entered", Toast.LENGTH_LONG).show();
                }

            }
            catch (JSONException jsonException){

            }


        }


    }

    private class AsyncGetMessages extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            Intent myIntent2 = getIntent();
            String x = myIntent2.getStringExtra("Id");

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_GET_MESSAGES+"?id="+x);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("data");
                    if (data != null) {
                        JSONObject st=data.getJSONObject(0);
                        Conversation c= new Conversation(st.getString("message"));
                        sList.add(c);
                        int len = data.length();
                        for (int i=1;i<len;i++){
                            JSONObject ct=data.getJSONObject(i);
                            c=new Conversation(ct.getString("message"));
                            sList.add(c);
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