package com.example.slathletecare.casestudy.inner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.R;
import com.example.slathletecare.model.DietEvent;
import com.example.slathletecare.model.WorkoutEvent;
import com.example.slathletecare.model.WorkoutSchedule;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WorkoutEventsAdapter extends RecyclerView.Adapter<WorkoutEventsAdapter.MyViewHolder> {

    private List<WorkoutEvent> mList;
    private View.OnClickListener mOnItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView itd;
        public TextView tt;
        public TextView te;
        public TextView ibd;

        public MyViewHolder(View itemView) {
            super(itemView);
            itd = (TextView) itemView.findViewById(R.id.itd);
            tt = (TextView) itemView.findViewById(R.id.tt);
            te = (TextView) itemView.findViewById(R.id.te);
            ibd = (TextView) itemView.findViewById(R.id.ibd);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);

        }
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }


    public WorkoutEventsAdapter(List<WorkoutEvent> mList) {
        this.mList = mList;
    }

    @NonNull
    @NotNull
    @Override
    public WorkoutEventsAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_one, parent, false);
        return new WorkoutEventsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WorkoutEventsAdapter.MyViewHolder holder, int position) {
        WorkoutEvent s = mList.get(position);
        holder.te.setText(s.getTitle());
        holder.ibd.setText(s.getDesc());
        holder.tt.setText(s.getType());
        holder.itd.setText(s.getTypeDesc());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
