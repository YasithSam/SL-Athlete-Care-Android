package com.example.slathletecare.activity.scheduleadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.MessageAdapter;
import com.example.slathletecare.R;
import com.example.slathletecare.model.Conversation;
import com.example.slathletecare.model.DietSchedule;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.MyViewHolder>{
    private List<DietSchedule> dList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //public boolean status;
        public TextView name;

        //public String imgUrl;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.schedule_name);


        }
    }
    public DietAdapter(List<DietSchedule> cList) {
        this.dList= cList;
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
        DietSchedule d= dList.get(position);
        holder.name.setText(d.getCaseStudy());



    }

    @Override
    public int getItemCount() {
        return dList.size();
    }
}
