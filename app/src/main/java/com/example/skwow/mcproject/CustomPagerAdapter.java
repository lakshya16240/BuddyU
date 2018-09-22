package com.example.skwow.mcproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    private int PageCount;
    private String[] PageTitle = {"All", "Movies", "Sports", "Event", "Trips"};

    public CustomPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        PageCount = 5;
    }

    @Override
    public Fragment getItem(int position) {
        MainFragment mainFragment = MainFragment.newInstance(position);
        return mainFragment;
    }

    @Override
    public int getCount() {
        return PageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return PageTitle[position];
    }
}
