package com.example.slathletecare.casestudy.inner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.R;

import com.example.slathletecare.model.Medicine;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MyViewHolder> {
    private List<Medicine> mList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView desc;
        public MyViewHolder(View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.tv_m_h);
            desc=(TextView) itemView.findViewById(R.id.tv_m_d);
        }
    }


    public MedicineAdapter(List<Medicine> mList) {
        this.mList= mList;
    }

    @NonNull
    @NotNull
    @Override
    public MedicineAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medicine_item, parent, false);
        return new MedicineAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MedicineAdapter.MyViewHolder holder, int position) {
        Medicine s= mList.get(position);
        holder.title.setText(s.getTitle());
        holder.desc.setText(s.getDesc());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
