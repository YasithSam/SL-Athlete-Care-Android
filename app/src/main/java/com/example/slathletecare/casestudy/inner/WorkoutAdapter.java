package com.example.slathletecare.casestudy.inner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.R;
import com.example.slathletecare.model.DietSchedule;
import com.example.slathletecare.model.WorkoutSchedule;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.MyViewHolder> {
    private List<WorkoutSchedule> mList;
    private View.OnClickListener mOnItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView id;
        public TextView d;
        public ImageButton arrow;
        public LinearLayout l1;
        public CardView c1;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.w_schedule_name);
            id = (TextView) itemView.findViewById(R.id.w_id);
            d = (TextView) itemView.findViewById(R.id.w_d);
            c1= (CardView) itemView.findViewById(R.id.cccx);
            l1=(LinearLayout) itemView.findViewById(R.id.l1) ;
            arrow=(ImageButton) itemView.findViewById(R.id.arrow_button);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);

        }
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }


    public WorkoutAdapter(List<WorkoutSchedule> mList) {
        this.mList = mList;
    }

    @NonNull
    @NotNull
    @Override
    public WorkoutAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_item, parent, false);
        return new WorkoutAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WorkoutAdapter.MyViewHolder holder, int position) {
        WorkoutSchedule s = mList.get(position);
        holder.title.setText(s.getTitle());
        holder.id.setText(s.getId());
        holder.d.setText(s.getDescription());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
