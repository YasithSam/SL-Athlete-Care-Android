package com.example.slathletecare.casestudy;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slathletecare.R;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.casestudy.inner.AdviceActivity;
import com.example.slathletecare.casestudy.inner.DietActivity;
import com.example.slathletecare.casestudy.inner.DietAdapter;
import com.example.slathletecare.casestudy.inner.ImageActivity;
import com.example.slathletecare.casestudy.inner.MedicineActivity;
import com.example.slathletecare.casestudy.inner.WorkoutActivity;
import com.example.slathletecare.casestudy.inner.WorkoutAdapter;
import com.example.slathletecare.databinding.FragmentHomeBinding;
import com.example.slathletecare.databinding.FragmentPostBinding;
import com.example.slathletecare.model.DietSchedule;
import com.example.slathletecare.model.WorkoutSchedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.slathletecare.AppController.TAG;

public class PostFragment extends Fragment {
    CardView c1,c2,c3,c4;
    Bundle bundle;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private FragmentPostBinding binding;
    private DietAdapter mAdapter;
    private List<DietSchedule> mList2 = new ArrayList<>();
    private WorkoutAdapter mAdapter2;
    private List<WorkoutSchedule> mList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPostBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        c1=root.findViewById(R.id.aa);
        c2=root.findViewById(R.id.a2);

        mAdapter = new DietAdapter(mList2);
        recyclerView = (RecyclerView) root.findViewById(R.id.r_d_s_p);
        recyclerView2 = (RecyclerView) root.findViewById(R.id.r_w_s_p);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter2 = new WorkoutAdapter(mList);
        RecyclerView.LayoutManager mLayoutManager2 = new GridLayoutManager(getContext(), 2);
        recyclerView2.setLayoutManager(mLayoutManager2);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(mAdapter2);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), AdviceActivity.class);
                myIntent.putExtra("id", bundle.getInt("id"));
                myIntent.putExtra("type", 0);
                startActivity(myIntent);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), ImageActivity.class);
                myIntent.putExtra("id", bundle.getInt("id"));
                myIntent.putExtra("type", 1);
                startActivity(myIntent);
            }
        });

        new AsyncGet().execute();
        mAdapter.setOnItemClickListener(onItemClickListener);
        mAdapter2.setOnItemClickListener(onItemClickListener2);
        return root;
    }
    private View.OnClickListener onItemClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAbsoluteAdapterPosition();
            WorkoutSchedule cs = mList.get(position);
            Intent myIntent = new Intent(getActivity(), WorkoutActivity.class);
            myIntent.putExtra("id",cs.getId());
            myIntent.putExtra("desc",cs.getDescription());
            startActivity(myIntent);

        }
    };
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAbsoluteAdapterPosition();
            DietSchedule cs = mList2.get(position);
            Intent myIntent = new Intent(getActivity(), DietActivity.class);
            myIntent.putExtra("id",cs.getId());
            myIntent.putExtra("desc",cs.getDescription());
            startActivity(myIntent);

        }
    };

    private class AsyncGet extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            bundle= getArguments();
            String id=String.valueOf(bundle.getInt("id"));

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_SCHEDULES+"?id="+id+"&&type="+1);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("workout");
                    if (data != null) {
                        JSONObject st=data.getJSONObject(0);
                        WorkoutSchedule m = new WorkoutSchedule(st.getString("id"),st.getString("title"), st.getString("description"));
                        mList.add(m);
                        int len = data.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data.getJSONObject(i);
                            m= new WorkoutSchedule(mt.getString("id"),mt.getString("title"), mt.getString("description"));
                            mList.add(m);
                        }
                    }
                    JSONArray data2 = jsonObj.getJSONArray("diet");
                    if (data2 != null) {
                        JSONObject st=data2.getJSONObject(0);
                        DietSchedule m = new DietSchedule(st.getString("id"),st.getString("title"), st.getString("description"));
                        mList2.add(m);
                        int len = data2.length();
                        for (int i=1;i<len;i++){
                            JSONObject mt=data2.getJSONObject(i);
                            m= new DietSchedule(mt.getString("id"),mt.getString("title"), mt.getString("description"));
                            mList2.add(m);
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
            // Dismiss the progress dialog
            mAdapter.notifyDataSetChanged();
            mAdapter2.notifyDataSetChanged();
        }
    }



}