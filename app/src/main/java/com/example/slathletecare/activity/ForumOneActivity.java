package com.example.slathletecare.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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

import com.example.slathletecare.R;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.model.Comment;
import com.example.slathletecare.ui.FormDetailsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

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

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.slathletecare.AppController.TAG;

public class ForumOneActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;
    private RecyclerView recyclerView;
    private CommentAdapter mAdapter;
    private List<Comment> sList = new ArrayList<>();
    Timer t;
    CardView c1;
    EditText et;
    ImageView i1,i2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_one);
        getSupportActionBar().hide();
        t1=findViewById(R.id.forum_one_title);
        t2=findViewById(R.id.forum_one_desc);
        t3=findViewById(R.id.forum_one_date);
        t4=findViewById(R.id.forum_one_d);
        t5=findViewById(R.id.forum_one_i);
        recyclerView=findViewById(R.id.rv_f_2);
        c1=findViewById(R.id.cardView444);

        i2=findViewById(R.id.imageViewPostDelete);

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withConfirmButton();
            }
        });

        FloatingActionButton fab=findViewById(R.id.f_one_back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                ForumOneActivity.super.onBackPressed();
            }
        });


        Intent myIntent = getIntent();
        String h = myIntent.getStringExtra("h");
        String i = myIntent.getStringExtra("i");
        String d = myIntent.getStringExtra("d");
        String l = myIntent.getStringExtra("dd");
        String c = myIntent.getStringExtra("doctor");
        et=findViewById(R.id.forum_one_edit);
        t1.setText(h);
        t2.setText(d);
        t3.setText(l);
        t4.setText(c);
        t5.setText(i);

        mAdapter = new CommentAdapter(sList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                new ForumOneActivity.AsyncGetComments().execute();
            }
        }, 1000);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String h = myIntent.getStringExtra("id");
                String i2=et.getText().toString();
                new ForumOneActivity.AsyncAddComments().execute(i2,h);
            }
        });



    }
    public void withConfirmButton() {
        AlertDialog alertDialog = new AlertDialog.Builder(ForumOneActivity.this).create();
        alertDialog.setTitle("Are you sure you want to delete?");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                new ForumOneActivity.AsyncDeletePost().execute();
                dialog.dismiss();

            }
        });
        alertDialog.show();
    }
    private class AsyncDeletePost extends AsyncTask<String,String,String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String ... params) {
            try {
                // Enter URL address where your php file resides
                url = new URL(AppConfig.URL_DELETE_COMMENTS_POST+"?id="+getIntent().getStringExtra("id"));

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("DELETE");

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
            startActivity(new Intent(ForumOneActivity.this,FormDetailsActivity.class));
            finish();

            try{

                JSONObject obj = new JSONObject(result);

                if (obj.getString("status").equals("n")) {
                    Toast.makeText(ForumOneActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                }
                else if (obj.getString("status").equals("e")){
                    Toast.makeText(ForumOneActivity.this, "No data entered", Toast.LENGTH_LONG).show();
                }


            }
            catch (JSONException jsonException){

            }


        }


    }

    private class AsyncAddComments extends AsyncTask<String,String,String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String ... params) {
            try {
                // Enter URL address where your php file resides
                url = new URL(AppConfig.URL_ADD_COMMENTS_POST);

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
                        .appendQueryParameter("comment",params[0])
                        .appendQueryParameter("post",params[1])
                        .appendQueryParameter("id","sl-ac-617e516484ac0");
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
                    AlertDialog alertDialog = new AlertDialog.Builder(ForumOneActivity.this).create();
                    alertDialog.setTitle("Successfully Added Comment!");
                    alertDialog.setMessage("Your comment has being added successfully, Please wait until it is being approved");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent myIntent = new Intent(ForumOneActivity.this, ForumOneActivity.class);
                                    myIntent.putExtra("h",getIntent().getStringExtra("h"));
                                    myIntent.putExtra("d",getIntent().getStringExtra("d"));
                                    myIntent.putExtra("dd",getIntent().getStringExtra("dd"));
                                    myIntent.putExtra("i",getIntent().getStringExtra("i"));
                                    myIntent.putExtra("doctor",getIntent().getStringExtra("doctor"));
                                    myIntent.putExtra("id",getIntent().getStringExtra("id"));
                                    startActivity(myIntent);
                                }
                            });
                    alertDialog.show();

                }
                else if (obj.getString("status").equals("n")) {
                    Toast.makeText(ForumOneActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                }
                else if (obj.getString("status").equals("e")){
                    Toast.makeText(ForumOneActivity.this, "No data entered", Toast.LENGTH_LONG).show();
                }

            }
            catch (JSONException jsonException){

            }


        }


    }

    private class AsyncGetComments extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            Intent myIntent = getIntent();
            String x = myIntent.getStringExtra("id");

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_GET_COMMENTS_POST+"?id="+x);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("data");
                    if (data != null) {
                        JSONObject st=data.getJSONObject(0);
                        Comment c= new Comment(st.getString("username"), st.getString("comment"), st.getString("date")+" Hours Ago");
                        sList.add(c);
                        int len = data.length();
                        for (int i=1;i<len;i++){
                            JSONObject ct=data.getJSONObject(i);
                            c=new Comment(ct.getString("username"), ct.getString("comment"), ct.getString("date")+" Hours Ago");
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