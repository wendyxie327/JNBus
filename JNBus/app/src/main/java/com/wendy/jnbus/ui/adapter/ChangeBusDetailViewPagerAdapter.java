package com.wendy.jnbus.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 类描述：
 * 创建人：XieWQ
 * 创建时间：2017/8/21 0021 下午 17:19
 */
public class ChangeBusDetailViewPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private List<Fragment> fragments;

    public ChangeBusDetailViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
