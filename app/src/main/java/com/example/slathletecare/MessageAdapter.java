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
        //public boolean status;
        public TextView fromUser;
        public TextView topMessage;
        //public String imgUrl;
        public MyViewHolder(View itemView) {
            super(itemView);
            fromUser=(TextView) itemView.findViewById(R.id.tv_chatName);
            topMessage=(TextView) itemView.findViewById(R.id.tv_chatTop);

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
                .inflate(R.layout.conversation_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MessageAdapter.MyViewHolder holder, int position) {
        Conversation c= conversationList.get(position);
        holder.fromUser.setText(c.getFromUser());
        holder.topMessage.setText(c.getTopMessage());
    

    }

    @Override
    public int getItemCount() {
        return conversationList.size();
    }


}
