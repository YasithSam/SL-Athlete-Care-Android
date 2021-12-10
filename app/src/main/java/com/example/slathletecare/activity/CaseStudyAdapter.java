package com.example.slathletecare.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.R;
import com.example.slathletecare.model.CaseStudy;
import com.example.slathletecare.model.Sport;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CaseStudyAdapter extends RecyclerView.Adapter<CaseStudyAdapter.MyViewHolder> {
    private List<CaseStudy> cList;
    private View.OnClickListener mOnItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView description;
        public  TextView id;


        public MyViewHolder(View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.tv_article_heading);
            description=(TextView) itemView.findViewById(R.id.tv_article_des);
            id=(TextView) itemView.findViewById(R.id.id);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);

        }
    }
    public CaseStudyAdapter(List<CaseStudy> cList) {
        this.cList= cList;
    }
    @NonNull
    @NotNull
    @Override
    public CaseStudyAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.case_study_item, parent, false);
        return new CaseStudyAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CaseStudyAdapter.MyViewHolder holder, int position) {
        CaseStudy s= cList.get(position);
        String si=String.valueOf(s.getId());
        holder.id.setText(si);
        holder.title.setText(s.getTitle());
        holder.description.setText(s.getDescription());
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return cList.size();
    }
}
