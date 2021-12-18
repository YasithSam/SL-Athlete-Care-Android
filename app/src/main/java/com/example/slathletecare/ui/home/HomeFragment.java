package com.example.slathletecare.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.ArticleAdapter;
import com.example.slathletecare.MessageAdapter;
import com.example.slathletecare.R;
import com.example.slathletecare.activity.ItemAllActivity;
import com.example.slathletecare.activity.LoginActivity;
import com.example.slathletecare.activity.NoticeAllActivity;
import com.example.slathletecare.activity.NoticeOneActivity;
import com.example.slathletecare.activity.QuestionAllActivity;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.casestudy.PreFragment;
import com.example.slathletecare.databinding.FragmentHomeBinding;
import com.example.slathletecare.model.Article;
import com.example.slathletecare.model.Articles;
import com.example.slathletecare.model.Conversation;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.slathletecare.AppController.TAG;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    CardView cca,ccc,ccf,ccr,cco;
    CardView qa,qc,qf,qr,qo;
    Button btn1;
    ProgressDialog pDialog;
    TextView tv1,tv2;
    ImageView iv11;
    private List<Article> nList = new ArrayList<>();
    private List<Article> acList = new ArrayList<>();
    private List<Article> afList = new ArrayList<>();
    private List<Article> arList = new ArrayList<>();
    private List<Article> aaList = new ArrayList<>();
    private List<Article> aoList = new ArrayList<>();
    private List<Article> qcList = new ArrayList<>();
    private List<Article> qfList = new ArrayList<>();
    private List<Article> qrList = new ArrayList<>();
    private List<Article> qaList = new ArrayList<>();
    private List<Article> qoList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cca=root.findViewById(R.id.cca);
        ccc=root.findViewById(R.id.ccc);
        ccf=root.findViewById(R.id.ccf);
        ccr=root.findViewById(R.id.ccr);
        cco=root.findViewById(R.id.cco);

        qa=root.findViewById(R.id.qa);
        qc=root.findViewById(R.id.qc);
        qf=root.findViewById(R.id.qf);
        qr=root.findViewById(R.id.qr);
        qo=root.findViewById(R.id.qo);

        btn1=root.findViewById(R.id.btnN);
        tv1=root.findViewById(R.id.n_h);
        tv2=root.findViewById(R.id.n_d);
        iv11=root.findViewById(R.id.n_i);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),NoticeOneActivity.class);
                intent.putExtra("data", (Serializable) nList);
                startActivity(intent);
            }
        });
        cca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)aaList);
                Intent intent = new Intent(getActivity(),ItemAllActivity.class);
                intent.putExtra("data", args);
                startActivity(intent);
            }
        });
        ccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)acList);
                Intent intent = new Intent(getActivity(),ItemAllActivity.class);
                intent.putExtra("data", args);
                startActivity(intent);
            }
        });
        ccf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)afList);
                Intent intent = new Intent(getActivity(),ItemAllActivity.class);
                intent.putExtra("data", args);
                startActivity(intent);
            }
        });
        ccr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)arList);
                Intent intent = new Intent(getActivity(),ItemAllActivity.class);
                intent.putExtra("data", args);
                startActivity(intent);
            }
        });
        cco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)aoList);
                Intent intent = new Intent(getActivity(),ItemAllActivity.class);
                intent.putExtra("data", args);
                startActivity(intent);
            }
        });
        qc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),QuestionAllActivity.class);
                intent.putExtra("data", (Serializable) qcList);
                startActivity(intent);
            }
        });
        qa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),QuestionAllActivity.class);
                intent.putExtra("data", (Serializable) qaList);
                startActivity(intent);
            }
        });
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),QuestionAllActivity.class);
                intent.putExtra("data", (Serializable) qrList);
                startActivity(intent);
            }
        });
        qf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),QuestionAllActivity.class);
                intent.putExtra("data", (Serializable) qfList);
                startActivity(intent);
            }
        });
        qo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),QuestionAllActivity.class);
                intent.putExtra("data", (Serializable) qoList);
                startActivity(intent);
            }
        });



        new HomeFragment.AsyncGet().execute();
        return root;
    }
    private class AsyncGet extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_GET_ALL);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray data11 = jsonObj.getJSONArray("n");
                    if (data11 != null && data11.length() != 0) {
                        JSONObject f=data11.getJSONObject(0);
                        tv1.setText(f.getString("heading"));
                        tv2.setText(f.getString("description"));
                        Picasso.get().load(f.getString("url")).into(iv11);
                        JSONObject st=data11.getJSONObject(1);
                        Article m = new Article(st.getString("id"),st.getString("heading"),st.getString("description"), st.getString("datetime"),st.getString("likes"),st.getString("comments"),st.getString("url"),st.getString("status").equals("1")?st.getString("status"):"");
                        nList.add(m);
                        int len = data11.length();
                        for (int i=2;i<len;i++){
                            JSONObject mt=data11.getJSONObject(i);
                            m= new Article(mt.getString("id"),mt.getString("heading"),mt.getString("description"), mt.getString("datetime"),mt.getString("likes"),mt.getString("comments"),mt.getString("url"),mt.getString("status").equals("1")?mt.getString("status"):"");
                            nList.add(m);
                        }
                    }

                    JSONArray data = jsonObj.getJSONArray("qc");
                    JSONArray data2 = jsonObj.getJSONArray("qf");
                    JSONArray data3 = jsonObj.getJSONArray("qa");
                    JSONArray data4 = jsonObj.getJSONArray("qr");
                    JSONArray data5 = jsonObj.getJSONArray("qo");
                    if (data != null && data.length() != 0) {
                        JSONObject st=data.getJSONObject(0);
                        Article m = new Article(st.getString("id"),st.getString("heading"),st.getString("description"), st.getString("datetime"),st.getString("likes"),st.getString("comments"),st.getString("url"),st.getString("status").equals("1")?st.getString("status"):"");
                        qcList.add(m);
                        int len = data.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data.getJSONObject(i);
                            m= new Article(mt.getString("id"),mt.getString("heading"),mt.getString("description"), mt.getString("datetime"),mt.getString("likes"),mt.getString("comments"),mt.getString("url"),mt.getString("status").equals("1")?mt.getString("status"):"");
                            qcList.add(m);
                        }
                    }
                    if (data2 != null && data2.length() != 0) {
                        JSONObject st=data2.getJSONObject(0);
                        Article m = new Article(st.getString("id"),st.getString("heading"),st.getString("description"), st.getString("datetime"),st.getString("likes"),st.getString("comments"),st.getString("url"),st.getString("status").equals("1")?st.getString("status"):"");
                        qfList.add(m);
                        int len = data2.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data2.getJSONObject(i);
                            m= new Article(mt.getString("id"),mt.getString("heading"),mt.getString("description"), mt.getString("datetime"),mt.getString("likes"),mt.getString("comments"),mt.getString("url"),mt.getString("status").equals("1")?mt.getString("status"):"");
                            qfList.add(m);
                        }
                    }
                    if (data3 != null && data3.length() != 0) {
                        JSONObject st=data3.getJSONObject(0);
                        Article m = new Article(st.getString("id"),st.getString("heading"),st.getString("description"), st.getString("datetime"),st.getString("likes"),st.getString("comments"),st.getString("url"),st.getString("status").equals("1")?st.getString("status"):"");
                        qaList.add(m);
                        int len = data3.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data3.getJSONObject(i);
                            m= new Article(mt.getString("id"),mt.getString("heading"),mt.getString("description"), mt.getString("datetime"),mt.getString("likes"),mt.getString("comments"),mt.getString("url"),mt.getString("status").equals("1")?mt.getString("status"):"");
                            qaList.add(m);
                        }
                    }
                    if (data4 != null && data4.length() != 0) {
                        JSONObject st=data4.getJSONObject(0);
                        Article m = new Article(st.getString("id"),st.getString("heading"),st.getString("description"), st.getString("datetime"),st.getString("likes"),st.getString("comments"),st.getString("url"),st.getString("status").equals("1")?st.getString("status"):"");
                        qrList.add(m);
                        int len = data4.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data4.getJSONObject(i);
                            m= new Article(mt.getString("id"),mt.getString("heading"),mt.getString("description"), mt.getString("datetime"),mt.getString("likes"),mt.getString("comments"),mt.getString("url"),mt.getString("status").equals("1")?mt.getString("status"):"");
                            qrList.add(m);
                        }
                    }
                    if (data5 != null && data5.length() != 0) {
                        JSONObject st=data5.getJSONObject(0);
                        Article m = new Article(st.getString("id"),st.getString("heading"),st.getString("description"), st.getString("datetime"),st.getString("likes"),st.getString("comments"),st.getString("url"),st.getString("status").equals("1")?st.getString("status"):"");
                        qoList.add(m);
                        int len = data5.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data5.getJSONObject(i);
                            m= new Article(mt.getString("id"),mt.getString("heading"),mt.getString("description"), mt.getString("datetime"),mt.getString("likes"),mt.getString("comments"),mt.getString("url"),mt.getString("status").equals("1")?mt.getString("status"):"");
                            qoList.add(m);
                        }
                    }
                    JSONArray data6 = jsonObj.getJSONArray("ac");
                    JSONArray data7 = jsonObj.getJSONArray("af");
                    JSONArray data8 = jsonObj.getJSONArray("ar");
                    JSONArray data9 = jsonObj.getJSONArray("aa");
                    JSONArray data10 = jsonObj.getJSONArray("ao");

                    if (data6 != null && data6.length() != 0) {
                        JSONObject st=data6.getJSONObject(0);
                        Article m = new Article(st.getString("id"),st.getString("heading"),st.getString("description"), st.getString("datetime"),st.getString("likes"),st.getString("comments"),st.getString("url"),st.getString("status").equals("1")?st.getString("status"):"");
                        acList.add(m);
                        int len = data6.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data6.getJSONObject(i);
                            m= new Article(mt.getString("id"),mt.getString("heading"),mt.getString("description"), mt.getString("datetime"),mt.getString("likes"),mt.getString("comments"),mt.getString("url"),mt.getString("status").equals("1")?mt.getString("status"):"");
                            acList.add(m);
                        }
                    }

                    if (data7 != null && data7.length() != 0) {
                        JSONObject st=data7.getJSONObject(0);
                        Article m = new Article(st.getString("id"),st.getString("heading"),st.getString("description"), st.getString("datetime"),st.getString("likes"),st.getString("comments"),st.getString("url"),st.getString("status").equals("1")?st.getString("status"):"");
                        afList.add(m);
                        int len = data7.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data7.getJSONObject(i);
                            m= new Article(mt.getString("id"),mt.getString("heading"),mt.getString("description"), mt.getString("datetime"),mt.getString("likes"),mt.getString("comments"),mt.getString("url"),mt.getString("status").equals("1")?mt.getString("status"):"");
                            afList.add(m);
                        }
                    }

                    if (data8 != null && data8.length() != 0) {
                        JSONObject st=data8.getJSONObject(0);
                        Article m = new Article(st.getString("id"),st.getString("heading"),st.getString("description"), st.getString("datetime"),st.getString("likes"),st.getString("comments"),st.getString("url"),st.getString("status").equals("1")?st.getString("status"):"");
                        arList.add(m);
                        int len = data8.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data8.getJSONObject(i);
                            m= new Article(mt.getString("id"),mt.getString("heading"),mt.getString("description"), mt.getString("datetime"),mt.getString("likes"),mt.getString("comments"),mt.getString("url"),mt.getString("status").equals("1")?mt.getString("status"):"");
                            arList.add(m);
                        }
                    }
                    if (data9 != null && data9.length() != 0) {
                        JSONObject st=data9.getJSONObject(0);
                        Article m = new Article(st.getString("id"),st.getString("heading"),st.getString("description"), st.getString("datetime"),st.getString("likes"),st.getString("comments"),st.getString("url"),st.getString("status").equals("1")?st.getString("status"):"");
                        aaList.add(m);
                        int len = data9.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data9.getJSONObject(i);
                            m= new Article(mt.getString("id"),mt.getString("heading"),mt.getString("description"), mt.getString("datetime"),mt.getString("likes"),mt.getString("comments"),mt.getString("url"),mt.getString("status").equals("1")?mt.getString("status"):"");
                            aaList.add(m);
                        }
                    }
                    if (data10 != null && data10.length() != 0) {
                        JSONObject st=data10.getJSONObject(0);
                        Article m = new Article(st.getString("id"),st.getString("heading"),st.getString("description"), st.getString("datetime"),st.getString("likes"),st.getString("comments"),st.getString("url"),st.getString("status").equals("1")?st.getString("status"):"");
                        aoList.add(m);
                        int len = data10.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data10.getJSONObject(i);
                            m= new Article(mt.getString("id"),mt.getString("heading"),mt.getString("description"), mt.getString("datetime"),mt.getString("likes"),mt.getString("comments"),mt.getString("url"),mt.getString("status").equals("1")?mt.getString("status"):"");
                            aoList.add(m);
                        }
                    }

                } catch (JSONException e) {

                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            // Dismiss the progress dialog
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}