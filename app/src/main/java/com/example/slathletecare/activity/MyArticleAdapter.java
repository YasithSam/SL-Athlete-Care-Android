package com.example.slathletecare.activity;

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
import com.example.slathletecare.model.Image;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyArticleAdapter extends RecyclerView.Adapter<MyArticleAdapter.MyViewHolder> {
    private List<Article> aList;
    private View.OnClickListener mOnItemClickListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView heading;
        public TextView datetime;
        public TextView status;
        public ImageView i1,i2;

        public MyViewHolder(View itemView) {
            super(itemView);
            heading = (TextView) itemView.findViewById(R.id.m_a);
            datetime = (TextView) itemView.findViewById(R.id.m_d);
            i2 = (ImageView) itemView.findViewById(R.id.imageView23);
            status=(TextView) itemView.findViewById(R.id.m_state);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public MyArticleAdapter(List<Article> aList) {
        this.aList = aList;
    }

    @NonNull
    @NotNull
    @Override
    public MyArticleAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_article, parent, false);
        return new MyArticleAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyArticleAdapter.MyViewHolder holder, int position) {
        Article a = aList.get(position);
        holder.heading.setText(a.getHeading());
        holder.datetime.setText(a.getDatetime());
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