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

public class taps2 extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager ;
    fragmentAdapter2 fragmentAdapter;

    //it is made to create the taps for the Doctor side

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taps2);

        tabLayout=findViewById(R.id.tab_latout2);

        viewPager=findViewById(R.id.view_pager3);

        FragmentManager manager =getSupportFragmentManager();
        fragmentAdapter= new fragmentAdapter2(manager, getLifecycle());
        viewPager.setAdapter(fragmentAdapter);


        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Requests"));
        tabLayout.addTab(tabLayout.newTab().setText("Hospital"));


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

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));

            }
        });
    }
}