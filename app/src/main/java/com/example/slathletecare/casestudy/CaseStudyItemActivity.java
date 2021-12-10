package com.example.slathletecare.casestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.slathletecare.R;
import com.example.slathletecare.tabbed.PageAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class CaseStudyItemActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapterCS pageAdapter;
    TabItem tab1;
    TabItem tab2;
    TabItem tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_study_item);
        tabLayout = findViewById(R.id.tabCaseStduy);
        tab1 = findViewById(R.id.tabUpdates);
        tab2 = findViewById(R.id.tabPre);
        tab3 = findViewById(R.id.tabPost);
        viewPager = findViewById(R.id.viewPagerCaseStudy);
        getSupportActionBar().hide();
        Intent myIntent = getIntent();
        int id = myIntent.getIntExtra("id",0);

        pageAdapter = new PageAdapterCS(getSupportFragmentManager(), tabLayout.getTabCount(),id);
        viewPager.setAdapter(pageAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}