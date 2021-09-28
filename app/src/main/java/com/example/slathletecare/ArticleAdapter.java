package com.example.slathletecare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.model.Articles;
import com.example.slathletecare.model.Conversation;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder>{
    private List<Articles> aList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView heading;
        public TextView desc;
        public TextView likes;
        public TextView comments;
        //public String imgUrl;
        public MyViewHolder(View itemView) {
            super(itemView);
            heading=(TextView) itemView.findViewById(R.id.tv_header);
            desc=(TextView) itemView.findViewById(R.id.tv_article_des);
            likes=(TextView) itemView.findViewById(R.id.tv_l);
            comments=(TextView) itemView.findViewById(R.id.tv_c);

        }
    }
    public ArticleAdapter(List<Articles> aList) {
        this.aList= aList;
    }

    @NonNull
    @NotNull
    @Override
    public ArticleAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item, parent, false);
        return new ArticleAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ArticleAdapter.MyViewHolder holder, int position) {
        Articles a= aList.get(position);
        holder.heading.setText(a.getHeader());
        holder.desc.setText(a.getDesc());
        holder.likes.setText(a.getLikes());
        holder.comments.setText(a.getComments());

    }

    @Override
    public int getItemCount() {
        return aList.size();
    }

}
