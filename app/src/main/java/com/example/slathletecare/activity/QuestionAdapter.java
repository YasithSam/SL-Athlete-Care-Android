package com.example.slathletecare.activity;

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
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder>{
    private List<Article> qList;
    private View.OnClickListener mOnItemClickListener;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView heading;
        public TextView desc;
        public TextView comments;
        public TextView d;

        public MyViewHolder(View itemView) {
            super(itemView);
            heading=(TextView) itemView.findViewById(R.id.q_header);
            desc=(TextView) itemView.findViewById(R.id.q_des);
            comments=(TextView) itemView.findViewById(R.id.q_c);
            d=(TextView) itemView.findViewById(R.id.q_d);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
    public void filterList(ArrayList<Article> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        qList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
    public QuestionAdapter(List<Article> aList) {
        this.qList= aList;
    }

    @NonNull
    @NotNull
    @Override
    public QuestionAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_item, parent, false);
        return new QuestionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull QuestionAdapter.MyViewHolder holder, int position) {
        Article a= qList.get(position);
        holder.heading.setText(a.getHeading());
        holder.desc.setText(a.getDescription());
        holder.comments.setText(a.getComments());
        holder.d.setText(a.getDatetime());
    }

    @Override
    public int getItemCount() {
        return qList.size();
    }
}
