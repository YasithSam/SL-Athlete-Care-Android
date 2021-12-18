package com.example.slathletecare.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.R;
import com.example.slathletecare.model.Article;
import com.example.slathletecare.model.Comment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder>{
    private List<Comment> cList;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView user;
        public TextView comment;
        public TextView d;

        public MyViewHolder(View itemView) {
            super(itemView);
            user=(TextView) itemView.findViewById(R.id.tv_c_header);
            comment=(TextView) itemView.findViewById(R.id.tv_c_comment);
            d=(TextView) itemView.findViewById(R.id.tv_c_d);
        }
    }
    public CommentAdapter(List<Comment> aList) {
        this.cList= aList;
    }

    @NonNull
    @NotNull
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        return new CommentAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CommentAdapter.MyViewHolder holder, int position) {
       Comment c= cList.get(position);
        holder.user.setText(c.getUserName());
        holder.comment.setText(c.getComment());
        holder.d.setText(c.getDatetime());
    }

    @Override
    public int getItemCount() {
        return cList.size();
    }
}
