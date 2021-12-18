package com.example.slathletecare.casestudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slathletecare.R;
import com.example.slathletecare.model.feedback;

import java.util.List;

public class FeedbackAdapter extends ArrayAdapter<feedback>
{

    public FeedbackAdapter(Context context, int resource, List<feedback> fList)
    {
        super(context,resource,fList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        feedback f= getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.feedback_item, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tv_f_f);
        TextView tv2 = (TextView) convertView.findViewById(R.id.tv_f_d);


        tv.setText(f.getFeedback());
        tv2.setText(f.getDate());

        return convertView;
    }
}
