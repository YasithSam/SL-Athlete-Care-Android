package com.example.slathletecare.casestudy.inner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.R;
import com.example.slathletecare.model.DietSchedule;
import com.example.slathletecare.model.Medicine;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.MyViewHolder> {
    private List<DietSchedule> mList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;
        public TextView id;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.schedule_name);
            desc = (TextView) itemView.findViewById(R.id.s_s_d);
            id= (TextView) itemView.findViewById(R.id.s_id);
        }
    }


    public DietAdapter(List<DietSchedule> mList) {
        this.mList = mList;
    }

    @NonNull
    @NotNull
    @Override
    public DietAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diet_item, parent, false);
        return new DietAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DietAdapter.MyViewHolder holder, int position) {
        DietSchedule s = mList.get(position);
        holder.title.setText(s.getTitle());
        holder.desc.setText(s.getDescription());
        holder.id.setText(s.getId());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
