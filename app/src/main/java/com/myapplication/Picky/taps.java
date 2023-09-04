package com.myapplication.Picky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class taps extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    fragmentAdapter fragmentAdapter;

//it is made to create the taps for the patient side
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taps);

        tabLayout=findViewById(R.id.tab_latout1);

        viewPager2=findViewById(R.id.view_pager2);

        FragmentManager manager =getSupportFragmentManager();
        fragmentAdapter= new fragmentAdapter(manager, getLifecycle());
        viewPager2.setAdapter(fragmentAdapter);


        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Requests"));
        tabLayout.addTab(tabLayout.newTab().setText("Hospital"));
        tabLayout.addTab(tabLayout.newTab().setText("Stats"));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));

            }
        });
    }
}