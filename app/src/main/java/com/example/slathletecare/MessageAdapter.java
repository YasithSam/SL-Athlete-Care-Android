package com.example.slathletecare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slathletecare.model.Conversation;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder>{
    private List<Conversation> conversationList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView date;
        public TextView message;
        public TextView time;
        public MyViewHolder(View itemView) {
            super(itemView);
            message=(TextView) itemView.findViewById(R.id.text_gchat_message_me);
            date=(TextView) itemView.findViewById(R.id.text_gchat_date_me);
            time=(TextView) itemView.findViewById(R.id.text_gchat_timestamp_me);

        }
    }
    public MessageAdapter(List<Conversation> cList) {
        this.conversationList= cList;
    }

    @NonNull
    @NotNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MessageAdapter.MyViewHolder holder, int position) {
        Conversation c= conversationList.get(position);
        holder.message.setText(c.getMessage());
        holder.date.setText(c.getDatetime());
        holder.time.setText(c.getTime());

    }

    @Override
    public int getItemCount() {
        return conversationList.size();
    }


}
