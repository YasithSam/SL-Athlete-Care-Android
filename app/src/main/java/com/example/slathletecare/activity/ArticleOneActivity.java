package com.example.slathletecare.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slathletecare.R;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.model.Image;
import com.example.slathletecare.ui.FormDetailsActivity;
import com.example.slathletecare.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ArticleOneActivity extends AppCompatActivity {
    ImageView i1,i2,i3,i4,iD;
    TextView t1,t2,t3,t4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_one);
        i1=findViewById(R.id.i_article);
        i2=findViewById(R.id.imageView10);
        i3=findViewById(R.id.imageViewAOne);
        i4=findViewById(R.id.imageView100);
        iD=findViewById(R.id.imageViewADeelete);
        getSupportActionBar().hide();
        t1=findViewById(R.id.textViewAOne);
        t2=findViewById(R.id.textViewAOneD);
        t3=findViewById(R.id.textViewAOneL);
        t4=findViewById(R.id.textViewAOneC);

        Intent myIntent = getIntent();
        String h = myIntent.getStringExtra("h");
        String d = myIntent.getStringExtra("d");
        String l = myIntent.getStringExtra("l");
        String c = myIntent.getStringExtra("c");
        int i=myIntent.getIntExtra("delete",0);
        if(i==1){
            iD.setVisibility(View.VISIBLE);
        }
        iD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                withConfirmButton();
            }
        });

        if(!myIntent.getStringExtra("i").isEmpty()){
            Picasso.get().load(myIntent.getStringExtra("i")).into(i3);
        }
        t1.setText(h);
        t2.setText(d);
        t3.setText(l);
        t4.setText(c);
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t3.setText(String.valueOf(Integer.parseInt(t3.getText().toString())+1));

            }
        });
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent my= new Intent(ArticleOneActivity.this,CommentsActivity.class);
                my.putExtra("id",myIntent.getStringExtra("id"));
                startActivity(my);
            }
        });
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent my= new Intent(ArticleOneActivity.this,ReportingActivity.class);
                my.putExtra("post",myIntent.getStringExtra("id"));
                startActivity(my);
            }
        });
    }
    public void withConfirmButton() {
        AlertDialog alertDialog = new AlertDialog.Builder(ArticleOneActivity.this).create();
        alertDialog.setTitle("Are you sure you want to delete?");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                new ArticleOneActivity.AsyncDelete().execute();
                dialog.dismiss();

            }
        });
        alertDialog.show();
    }
    private class AsyncDelete extends AsyncTask<String,String,String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String ... params) {
            try {
                // Enter URL address where your php file resides
                url = new URL(AppConfig.URL_DELETE_BLOG+"id=?"+getIntent().getStringExtra("id"));

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

            try{
                JSONObject obj = new JSONObject(result);

                AlertDialog alertDialog = new AlertDialog.Builder(ArticleOneActivity.this).create();
                alertDialog.setTitle("Successfully Deleted Comment!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                startActivity(new Intent(ArticleOneActivity.this, HomeFragment.class));
                            }
                        });
                alertDialog.show();


                if (obj.getString("status").equals("n")) {
                    Toast.makeText(ArticleOneActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                }
                else if (obj.getString("status").equals("e")){
                    Toast.makeText(ArticleOneActivity.this, "No data entered", Toast.LENGTH_LONG).show();
                }

            }
            catch (JSONException jsonException){

            }


        }


    }
}