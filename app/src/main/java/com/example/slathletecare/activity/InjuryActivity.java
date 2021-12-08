package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slathletecare.ForgetPassword;
import com.example.slathletecare.MainActivity;
import com.example.slathletecare.R;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.helper.SessionManager;

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
import java.util.HashMap;
import java.util.List;

public class InjuryActivity extends AppCompatActivity {
    public ArrayList<String> iList=new ArrayList<>();
    public ArrayList<String> dList=new ArrayList<>();
    ProgressDialog pDialog;

    private Spinner s;
    private Spinner s1;
    Button btnP,btnC;
    EditText etCon,etDes;
    TextView te;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_injury);
        getSupportActionBar().hide();
        s = (Spinner) findViewById(R.id.spinnerInjury);
        s1 = (Spinner) findViewById(R.id.spinnerDoc);
        btnP=findViewById(R.id.btnPostInjury);
        btnC=findViewById(R.id.btnCancelInjury);
        te=findViewById(R.id.textView7);



        etCon=findViewById(R.id.etCon);
        etDes=findViewById(R.id.etDesc);
        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String injury=s.getSelectedItem().toString();
                String doc=s1.getSelectedItem().toString();
                String con=etCon.getText().toString();
                String des=etDes.getText().toString();
                if(validateUser(con,des)){
                    new AsyncInjury().execute(injury,doc,con,des);
                }

            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InjuryActivity.this, MainActivity.class));
            }
        });
        //final Dialog nagDialog = new Dialog(InjuryActivity.this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
       // nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
       // nagDialog.setCancelable(false);
        //nagDialog.setContentView(R.layout.img_full);
       // Button btnClose = nagDialog.findViewById(R.id.fClose);
        //ImageView ivPreview = nagDialog.findViewById(R.id.imageViewInjury);
       // nagDialog.show();
        new getDataInjury().execute();

    }
    private void addSpinner() {
        List<String> injuries = new ArrayList<String>();
        List<String> doctors = new ArrayList<String>();

        for (int i = 0; i < iList.size(); i++) {
            injuries.add(iList.get(i));
        }

        doctors.add("For all");
        for (int i = 0; i < dList.size(); i++) {
            doctors.add(dList.get(i));
        }

        // Creating adapter for spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, injuries);
        // Creating adapter for spinner
        ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, doctors);

        // Drop down layout style - list view with radio button
        spinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAdapter2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        s.setAdapter(spinnerAdapter);
        s1.setAdapter(spinnerAdapter2);
    }

    private class getDataInjury extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(InjuryActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_INJURYDATA);

            Log.e("tag", "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray in = jsonObj.getJSONArray("injury");
                    JSONArray d = jsonObj.getJSONArray("doctor");
                    int x=in.length();
                    // looping through All injuries
                    if (in != null) {
                        int len = in.length();
                        for (int i=0;i<len;i++){
                            iList.add(in.get(i).toString());
                        }
                    }
                    if (d != null) {
                        int len = d.length();
                        for (int i=0;i<len;i++){

                            dList.add(d.get(i).toString());
                        }
                    }


                } catch (final JSONException e) {
                    Log.e("TAG", "Json parsing error: " + e.getMessage());
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
                Log.e("TAG", "Couldn't get json from server.");
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
            if (pDialog.isShowing())
                pDialog.dismiss();
            addSpinner();

        }

    }
    private class AsyncInjury extends AsyncTask<String,String,String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String... params) {
            try {
                // Enter URL address where your php file resides
                url = new URL(AppConfig.URL_INJURY);

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


                String userId="sl-ac-617e516484ac0";
                String doctor=params[1].equals("For all")?"0":params[1];
                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("userId", userId)
                        .appendQueryParameter("injury", params[0])
                        .appendQueryParameter("doctor", doctor)
                        .appendQueryParameter("condition", params[2])
                        .appendQueryParameter("description", params[3]);
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

            try {
                JSONObject obj = new JSONObject(result);
                if (obj.getString("status").equals("ok")) {
                    //obj.getString("data")
                    startActivity(new Intent(InjuryActivity.this,MainActivity.class));

                } else if (obj.getString("status").equals("error")) {

                    // If username and password does not match display a error message
                    Toast.makeText(InjuryActivity.this, "Could not update user", Toast.LENGTH_LONG).show();

                } else if (obj.getString("status").equals("errorUser")) {

                    Toast.makeText(InjuryActivity.this, "No user found with given data", Toast.LENGTH_LONG).show();

                }

            } catch (JSONException jsonException) {

            }


        }

    }
    private boolean validateUser(String con,String desc){
        if (con.isEmpty()) {
            etCon.setError("Field cannot be empty");
            return false;
        }if(desc.isEmpty()) {
            etDes.setError("Field cannot be empty");
            return false;
        }
        etCon.setError(null);
        etDes.setError(null);
        return true;



    }

}