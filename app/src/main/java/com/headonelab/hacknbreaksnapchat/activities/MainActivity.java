package com.headonelab.hacknbreaksnapchat.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.headonelab.hacknbreaksnapchat.R;
import com.headonelab.hacknbreaksnapchat.adapters.MainViewPagerAdapter;
import com.headonelab.hacknbreaksnapchat.fragments.CameraFragment;
import com.headonelab.hacknbreaksnapchat.fragments.InboxFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CameraFragment mCameraFragment;
    private InboxFragment mInboxFragment;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        loadViewPager();
    }

    private void loadViewPager() {
        mCameraFragment = CameraFragment.newInstance();
        mInboxFragment = InboxFragment.newInstance();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(mInboxFragment);
        fragmentList.add(mCameraFragment);
        mViewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(),fragmentList));
        mViewPager.setCurrentItem(1);
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.vpMain);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}