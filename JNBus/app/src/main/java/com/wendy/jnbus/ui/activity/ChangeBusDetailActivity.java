package com.wendy.jnbus.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.adapter.ChangeBusDetailViewPagerAdapter;
import com.wendy.jnbus.ui.base.BaseAppActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：
 * 创建人：XieWQ
 * 创建时间：2017/8/21 0021 下午 16:42
 */
public class ChangeBusDetailActivity extends BaseAppActivity {

    @BindView(R.id.tab_ll)
    TabLayout tabLl;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    public int getLayoutID() {
        return R.layout.activity_change_bus_detail;
    }

    @Override
    public void initBundle() {

    }

    @Override
    public void initDataCreate() {
        viewPager.setAdapter(new ChangeBusDetailViewPagerAdapter(getSupportFragmentManager()));
        tabLl.setupWithViewPager(viewPager);
    }

    @Override
    public void initDataResume() {

    }

}
