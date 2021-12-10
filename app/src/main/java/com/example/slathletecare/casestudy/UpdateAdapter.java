package com.example.slathletecare.casestudy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.R;
import com.example.slathletecare.activity.SportsAdapter;
import com.example.slathletecare.model.Sport;
import com.example.slathletecare.model.Update;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.MyViewHolder>{
    private List<Update> uList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView person;
        public TextView section;
        public TextView date;

        public MyViewHolder(View itemView) {
            super(itemView);
            person=(TextView) itemView.findViewById(R.id.u_person);
            section=(TextView) itemView.findViewById(R.id.u_section);
            date=(TextView) itemView.findViewById(R.id.u_datetime);

        }
    }


    public UpdateAdapter(List<Update> uList) {
        this.uList= uList;
    }

    @NonNull
    @NotNull
    @Override
    public UpdateAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.update_item, parent, false);
        return new UpdateAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UpdateAdapter.MyViewHolder holder, int position) {
        Update u= uList.get(position);
        holder.person.setText(u.getPerson());
        holder.section.setText(u.getSection());
        holder.date.setText(u.getDate());
    }

    @Override
    public int getItemCount() {
        return uList.size();
    }
}