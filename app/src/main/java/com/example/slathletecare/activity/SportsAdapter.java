package com.example.slathletecare.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.ArticleAdapter;
import com.example.slathletecare.R;
import com.example.slathletecare.model.Articles;
import com.example.slathletecare.model.Sport;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.MyViewHolder>{
    private List<Sport> sList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView sport;
        public TextView institution;
        public TextView level;

        public MyViewHolder(View itemView) {
            super(itemView);
            sport=(TextView) itemView.findViewById(R.id.sport);
            institution=(TextView) itemView.findViewById(R.id.level_p);
            level=(TextView) itemView.findViewById(R.id.ins_p);

        }
    }


    public SportsAdapter(List<Sport> sList) {
        this.sList= sList;
    }

    @NonNull
    @NotNull
    @Override
    public SportsAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sport_item, parent, false);
        return new SportsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SportsAdapter.MyViewHolder holder, int position) {
        Sport s= sList.get(position);
        holder.sport.setText(s.getSport());
        holder.institution.setText(s.getInstitution());
        holder.level.setText(s.getLevel());
    }

    @Override
    public int getItemCount() {
        return sList.size();
    }
}
