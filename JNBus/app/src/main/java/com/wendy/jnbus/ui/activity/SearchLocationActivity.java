package com.wendy.jnbus.ui.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.base.BaseAppActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wendy on 2017/8/12.
 * 查询地点
 */

public class SearchLocationActivity extends BaseAppActivity {
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.list_lv)
    ListView listLv;

    @Override
    public int getLayoutID() {
        return R.layout.activity_search_location;
    }

    @Override
    public void initBundle() {

    }

    @Override
    public void initDataCreate() {

    }

    @Override
    public void initDataResume() {

    }

}
