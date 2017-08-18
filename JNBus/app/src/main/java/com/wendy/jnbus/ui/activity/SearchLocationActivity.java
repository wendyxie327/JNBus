package com.wendy.jnbus.ui.activity;

import android.widget.ListView;
import android.widget.SearchView;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.adapter.SearchLocationAdapter;
import com.wendy.jnbus.ui.base.BaseAppActivity;
import com.wendy.jnbus.util.PubInfo;
import com.wendy.jnbus.util.amap.MapUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Wendy on 2017/8/12.
 * 查询地点
 */

public class SearchLocationActivity extends BaseAppActivity implements Inputtips.InputtipsListener {
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.list_lv)
    ListView listLv;

    private MapUtil mapUtil;
    private InputtipsQuery inputtipsQuery;
    private SearchLocationAdapter searchLocationAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_search_location;
    }

    @Override
    public void initBundle() {

    }

    @Override
    public void initDataCreate() {
        mapUtil = new MapUtil();
        searchLocationAdapter = new SearchLocationAdapter(context);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Logger.d(context, "onQueryTextChange, "+s);
                inputtipsQuery = new InputtipsQuery(s, PubInfo.KEY_LOCATION);
                inputtipsQuery.setCityLimit(true);
                mapUtil.searchPoiInputAsyn(context, SearchLocationActivity.this, inputtipsQuery);
                return false;
            }
        });
    }

    @Override
    public void initDataResume() {

    }
    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        Logger.d(context, "onGetInputtips, i = "+i +", " );
        if ( list == null) list = new ArrayList<>();
        searchLocationAdapter.setItems(list);
        listLv.setAdapter(searchLocationAdapter);
    }

}
