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
import com.example.slathletecare.model.WorkoutSchedule;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.MyViewHolder>{
    private List<WorkoutSchedule> wList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //public boolean status;
        public TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.w_schedule_name);


        }
    }
    public WorkoutAdapter(List<WorkoutSchedule> wList) {
        this.wList= wList;
    }

    @NonNull
    @Override
    public WorkoutAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_item, parent, false);
        return new WorkoutAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WorkoutAdapter.MyViewHolder holder, int position) {
        WorkoutSchedule w= wList.get(position);
        holder.name.setText(w.getCaseStudy());

    }

    @Override
    public int getItemCount() {
        return wList.size();
    }
}
