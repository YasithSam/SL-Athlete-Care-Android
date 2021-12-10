package com.example.slathletecare.casestudy.inner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.R;
import com.example.slathletecare.model.Advice;
import com.example.slathletecare.model.Medicine;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.MyViewHolder> {
    private List<Advice> aList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView desc;
        public MyViewHolder(View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.tv_a_a);
            desc=(TextView) itemView.findViewById(R.id.tv_a_d);

        }
    }


    public AdviceAdapter(List<Advice> mList) {
        this.aList= mList;
    }

    @NonNull
    @NotNull
    @Override
    public AdviceAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advice_item, parent, false);
        return new AdviceAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdviceAdapter.MyViewHolder holder, int position) {
        Advice s= aList.get(position);
        holder.title.setText(s.getTitle());
        holder.desc.setText(s.getDesc());
    }

    @Override
    public int getItemCount() {
        return aList.size();
    }
}
