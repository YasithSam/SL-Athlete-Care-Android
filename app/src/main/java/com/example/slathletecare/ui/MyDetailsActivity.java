package com.example.slathletecare.ui;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slathletecare.R;
import com.example.slathletecare.activity.HealthActivity;
import com.example.slathletecare.activity.SportActivity;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class MyDetailsActivity extends AppCompatActivity {
   TextView tvG;
    TextView tvG2;
    TextView name,mail,nic,address,gender,age,city,r_email;
    ProgressDialog pDialog;
    SessionManager sessionManager;
    public ArrayList<String> dList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);
        tvG=findViewById(R.id.textViewG);
        tvG2=findViewById(R.id.textViewG3);

        new getProfileData().execute();
        getSupportActionBar().hide();
        tvG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyDetailsActivity.this, HealthActivity.class));
            }
        });
        tvG2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyDetailsActivity.this, SportActivity.class));
            }
        });

    }
    public void setData(){
        name=findViewById(R.id.full_name);
        mail=findViewById(R.id.email_p);
        nic=findViewById(R.id.nic_p);
        address=findViewById(R.id.address_p);
        gender=findViewById(R.id.sex_p);
        age=findViewById(R.id.age_p);
        city=findViewById(R.id.city_p);
        r_email=findViewById(R.id.r_email_p);
        name.setText(!dList.get(0).equals("null") ? dList.get(0):"Not given");
        mail.setText(!dList.get(1).equals("null") ? dList.get(1):"Not given");
        r_email.setText(!dList.get(2).equals("null") ? dList.get(2):"Not given");
        address.setText(!dList.get(3).equals("null") ? dList.get(3):"Not given");
        nic.setText(!dList.get(4).equals("null") ? dList.get(4):"Not given");
        age.setText(!dList.get(5).equals("0") ? dList.get(5):"NULL");
        gender.setText(!dList.get(6).equals("null") ? dList.get(6):"Not given");
        city.setText(!dList.get(7).equals("null") ? dList.get(7):"Not given");

    }


   private class getProfileData extends AsyncTask<Void, Void, Void> {
       @Override
       protected void onPreExecute() {
           super.onPreExecute();
           pDialog = new ProgressDialog(MyDetailsActivity.this);
           pDialog.setMessage("Please wait...");
           pDialog.setCancelable(false);
           pDialog.show();

       }

       @Override
       protected Void doInBackground(Void... arg0) {
           HttpHandler sh = new HttpHandler();
           sessionManager =  new SessionManager(getApplicationContext());
           HashMap<String, String> user = sessionManager.getUserDetails();
           // Making a request to url and getting response
           String jsonStr = sh.makeServiceCall(AppConfig.URL_USER+"?"+"id="+user.get(SessionManager.userId));

           Log.e("tag", "Response from url: " + jsonStr);
           if (jsonStr != null) {
               try {
                   JSONObject jsonObj = new JSONObject(jsonStr);
                   JSONArray in = jsonObj.getJSONArray("data");
                   for(int i=0;i<in.length();i++){
                       dList.add(in.getString(i));
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
           }

           else {
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
           setData();
       }
   }
}