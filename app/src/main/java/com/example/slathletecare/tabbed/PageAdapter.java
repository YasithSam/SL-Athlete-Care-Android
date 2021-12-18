package com.example.slathletecare.tabbed;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.slathletecare.activity.ItemAllActivity;
import com.example.slathletecare.casestudy.UpdatesFragment;
import com.example.slathletecare.model.Article;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private List<Article> aList;
    private List<Article> qList;

    PageAdapter(FragmentManager fm, int numOfTabs,List<Article> aList,List<Article> qList) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.aList=aList;
        this.qList=qList;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)aList);
                Fragment fragment=new ArticleFragment();
                fragment.setArguments(args);
                return fragment;
            case 1:
                Bundle args2 = new Bundle();
                args2.putSerializable("ARRAYLIST",(Serializable)qList);
                Fragment fragment2=new ForumFragment();
                fragment2.setArguments(args2);
                return fragment2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
