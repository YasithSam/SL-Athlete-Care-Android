package com.example.slathletecare.casestudy.inner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.R;
import com.example.slathletecare.model.DietEvent;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DietEventsAdapter extends RecyclerView.Adapter<DietEventsAdapter.MyViewHolder> {

    private List<DietEvent> mList;
    private View.OnClickListener mOnItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView event_diet_heading;
        public TextView event_diet_body;
        public TextView event_diet_amount;

        public MyViewHolder(View itemView) {
            super(itemView);
            event_diet_heading= (TextView) itemView.findViewById(R.id.event_diet_heading);
            event_diet_body = (TextView) itemView.findViewById(R.id.event_diet_body);
            event_diet_amount = (TextView) itemView.findViewById(R.id.event_diet_amount);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);

        }
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }


    public DietEventsAdapter(List<DietEvent> mList) {
        this.mList = mList;
    }

    @NonNull
    @NotNull
    @Override
    public DietEventsAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diet_one, parent, false);
        return new DietEventsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DietEventsAdapter.MyViewHolder holder, int position) {
        DietEvent s = mList.get(position);
        holder.event_diet_heading.setText(s.getTitle());
        holder.event_diet_body.setText(s.getDesc());
        holder.event_diet_amount.setText(s.getType());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
