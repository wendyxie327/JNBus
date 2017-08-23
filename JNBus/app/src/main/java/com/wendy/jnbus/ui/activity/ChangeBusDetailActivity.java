package com.wendy.jnbus.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.adapter.ChangeBusDetailViewPagerAdapter;
import com.wendy.jnbus.ui.base.BaseAppActivity;
import com.wendy.jnbus.ui.fragment.ChangeBusDetailFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private BusRouteResult busRouteResult;

    @Override
    public int getLayoutID() {
        return R.layout.activity_change_bus_detail;
    }

    @Override
    public void initBundle() {
        busRouteResult = getIntent().getParcelableExtra("busRouteResult");
    }

    @Override
    public void initDataCreate() {
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        if (busRouteResult != null && busRouteResult.getPaths()!= null && busRouteResult.getPaths().size()>0){
            int count = 0;
            for (BusPath busPath : busRouteResult.getPaths()) {
                titles.add("方案"+(count+1));
                Fragment fragment = new ChangeBusDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("busPath", busPath);
                fragment.setArguments(bundle);
                fragments.add(fragment);
                count++;
            }
        }else {
            //TODO 没有可选方案时，提供提示页面

        }

        viewPager.setAdapter(
                new ChangeBusDetailViewPagerAdapter(getSupportFragmentManager(), fragments, titles.toArray(new String[titles.size()])));
        tabLl.setupWithViewPager(viewPager);
    }

    @Override
    public void initDataResume() {

    }

}
