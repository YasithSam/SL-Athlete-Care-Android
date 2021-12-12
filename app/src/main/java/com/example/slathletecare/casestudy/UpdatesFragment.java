package com.example.slathletecare.casestudy;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.slathletecare.R;
import com.example.slathletecare.app.AppConfig;
import com.example.slathletecare.app.HttpHandler;
import com.example.slathletecare.databinding.FragmentUpdatesBinding;
import com.example.slathletecare.model.Update;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.slathletecare.AppController.TAG;

public class UpdatesFragment extends Fragment {
    Bundle bundle;
    TextView textView;
    private RecyclerView recyclerView;
    private UpdateAdapter mAdapter;
    private List<Update> uList = new ArrayList<>();
    private FragmentUpdatesBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        binding= FragmentUpdatesBinding.inflate(inflater, container, false);
        View v=binding.getRoot();
        textView=v.findViewById(R.id.te);
        recyclerView = (RecyclerView) v.findViewById(R.id.u_u);

        mAdapter = new UpdateAdapter(uList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        new AsyncGetUpdates().execute();

        return v;
    }

    private class AsyncGetUpdates extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            bundle= getArguments();
            String id=String.valueOf(bundle.getInt("id"));
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(AppConfig.URL_CASE_STUDY_UPDATES+"?case_id="+id);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("data");
                    if (data != null) {
                        JSONObject st=data.getJSONObject(0);
                        Update s = new Update(st.getString("username"), st.getString("name"), st.getString("datetime"));
                        uList.add(s);
                        int len = data.length();
                        for (int i=1;i<len;i++){
                            JSONObject update=data.getJSONObject(i);
                            s=new Update(update.getString("username"), update.getString("name"), update.getString("datetime"));
                            uList.add(s);
                        }
                    }

                } catch (JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");

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