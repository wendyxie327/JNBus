package com.wendy.jnbus.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 类描述：
 * 创建人：XieWQ
 * 创建时间：2017/8/21 0021 下午 17:19
 */
public class ChangeBusDetailViewPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = {"test1", "test2"};

    public ChangeBusDetailViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
