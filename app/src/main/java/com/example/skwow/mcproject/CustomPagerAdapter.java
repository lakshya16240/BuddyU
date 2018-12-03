package com.example.skwow.mcproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    private int PageCount;
    private ArrayList<Fragment> fragmentArrayList;
    private String[] PageTitle = {"Events","Trade"}; //, "Sports", "Entertainment", "Trips"};

    // add arraylist of fragments

    public CustomPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragmentArrayList) {
        super(fragmentManager);
        PageCount = PageTitle.length;
        this.fragmentArrayList = fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        PageFragment pageFragment = PageFragment.newInstance(position);
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        //return PageCount;
        return fragmentArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return PageTitle[position];
    }
}
