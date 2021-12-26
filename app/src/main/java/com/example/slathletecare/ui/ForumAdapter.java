package com.example.slathletecare.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.ArticleAdapter;
import com.example.slathletecare.R;
import com.example.slathletecare.model.Article;
import com.example.slathletecare.model.ForumEvent;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.MyViewHolder>{
private List<ForumEvent> aList;
private View.OnClickListener mOnItemClickListener;

public class MyViewHolder extends RecyclerView.ViewHolder{
    public TextView heading;
    public TextView date;
    public TextView comments;
    public TextView status;

    public MyViewHolder(View itemView) {
        super(itemView);
        heading=(TextView) itemView.findViewById(R.id.fo_heading);
        date=(TextView) itemView.findViewById(R.id.fo_date);
        comments=(TextView) itemView.findViewById(R.id.fo_c);
        status=(TextView) itemView.findViewById(R.id.fo_status);
        itemView.setTag(this);
        itemView.setOnClickListener(mOnItemClickListener);
    }
}
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
    public ForumAdapter(List<ForumEvent> aList) {
        this.aList= aList;
    }

    @NonNull
    @NotNull
    @Override
    public ForumAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forum_item, parent, false);
        return new ForumAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ForumAdapter.MyViewHolder holder, int position) {
        ForumEvent a= aList.get(position);
        holder.heading.setText(a.getTitle());
        holder.date.setText(a.getDate());
        holder.comments.setText(a.getComment());
        if(a.getStatus().equals("0")) {
            holder.status.setTextColor(Color.parseColor("#0000FF"));
            holder.status.setText("Pending");
        }
        else if(a.getStatus().equals("1")){
            holder.status.setTextColor(Color.parseColor("#00FF00"));
            holder.status.setText("Published");
        }
        else{
            holder.status.setTextColor(Color.parseColor("#FF0000"));
            holder.status.setText("Rejected , We have sent an email about the rejection. Please contact back for any concerns through email");
        }

    }

    @Override
    public int getItemCount() {
        return aList.size();
    }
}
