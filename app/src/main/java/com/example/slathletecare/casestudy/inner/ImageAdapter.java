package com.example.slathletecare.casestudy.inner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.slathletecare.R;
import com.example.slathletecare.model.Image;
import com.example.slathletecare.model.WorkoutSchedule;
import com.squareup.picasso.Picasso;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {
    private List<Image> mList;
    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView i;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.case_img_h);
            i = (ImageView) itemView.findViewById(R.id.case_img);
        }
    }


    public ImageAdapter(Context context,List<Image> mList) {
        mContext=context;
        this.mList = mList;
    }

    @NonNull
    @NotNull
    @Override
    public ImageAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item, parent, false);
        return new ImageAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ImageAdapter.MyViewHolder holder, int position) {
        Image s = mList.get(position);
        holder.title.setText(s.getHeading());
        Picasso.get().load(s.getUrl()).into(holder.i);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
