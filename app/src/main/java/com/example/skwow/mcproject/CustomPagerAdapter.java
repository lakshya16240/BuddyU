package com.example.skwow.mcproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    private int PageCount;
    private String[] PageTitle = {"Movies", "Sports", "Event", "Trips"};

    public CustomPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        PageCount = PageTitle.length;
    }

    @Override
    public Fragment getItem(int position) {
        PageFragment pageFragment = PageFragment.newInstance(position);
        return pageFragment;
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
