package com.example.slathletecare.casestudy;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class PageAdapterCS  extends FragmentPagerAdapter {
    private int numOfTabs;

    PageAdapterCS(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new UpdatesFragment();
            case 1:
                return new PreFragment();
            case 2:
                return new PostFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
