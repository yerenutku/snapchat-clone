package com.headonelab.hacknbreaksnapchat.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> pageList;


    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> pages) {
        super(fm);
        pageList = new ArrayList<>();
        pageList = pages;
    }

    @Override
    public Fragment getItem(int position) {
        return pageList.get(position);
    }

    @Override
    public int getCount() {
        return pageList.size();
    }
}