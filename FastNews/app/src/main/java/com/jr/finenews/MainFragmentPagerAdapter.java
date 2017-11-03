package com.jr.finenews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by alfo6-6 on 2017-06-15.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    Fragment[] fragments=new Fragment[2];
    String[] titles=new String[]{"속보","뉴스룸"};


    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments[0]=new Tab1Fragment();
        fragments[1]=new Tab2Fragment();




    }


    @Override
    public Fragment getItem(int position) {
        return fragments[position];


    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }


}
