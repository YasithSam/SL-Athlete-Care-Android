package com.example.slathletecare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.model.Article;
import com.example.slathletecare.model.Articles;
import com.example.slathletecare.model.Conversation;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder>{
    private List<Article> aList;
    private View.OnClickListener mOnItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView heading;
        public TextView desc;
        public TextView likes;
        public TextView d;
        public ImageView imgUrl;
        public TextView status;

        public MyViewHolder(View itemView) {
            super(itemView);
            heading=(TextView) itemView.findViewById(R.id.tv_header);
            desc=(TextView) itemView.findViewById(R.id.tv_article_des);
            likes=(TextView) itemView.findViewById(R.id.tv_l);
            d=(TextView) itemView.findViewById(R.id.tv_d);
            imgUrl=(ImageView) itemView.findViewById(R.id.imageView1);
            status=(TextView) itemView.findViewById(R.id.t_status);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
    public ArticleAdapter(List<Article> aList) {
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
        Article a= aList.get(position);
        holder.heading.setText(a.getHeading());
        holder.desc.setText(a.getDescription());
        holder.likes.setText(a.getLikes());
        holder.d.setText(a.getDatetime());

        Picasso.get().load(a.getUrl()).into(holder.imgUrl);
    }

    @Override
    public int getItemCount() {
        return aList.size();
    }

}
