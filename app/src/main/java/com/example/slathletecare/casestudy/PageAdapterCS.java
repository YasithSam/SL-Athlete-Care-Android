package com.example.slathletecare.casestudy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class PageAdapterCS  extends FragmentPagerAdapter {
    private int numOfTabs;
    private int Id;

    PageAdapterCS(FragmentManager fm, int numOfTabs,int Id) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.Id=Id;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle=new Bundle();
        bundle.putInt("id",Id);
        switch (position) {
            case 0:
                Fragment fragment=new UpdatesFragment();
                fragment.setArguments(bundle);
                return fragment;
            case 1:
                Fragment fragment2=new PreFragment();
                fragment2.setArguments(bundle);
                return fragment2;
            case 2:
                Fragment fragment3=new PostFragment();
                fragment3.setArguments(bundle);
                return fragment3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
