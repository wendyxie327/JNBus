package com.wendy.jnbus.ui.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiAddrInfo;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.base.BaseAppActivity;
import com.wendy.jnbus.util.PubInfo;
import com.wendy.jnbus.util.baidumap.MapUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

/**
 * Created by Wendy on 2017/8/12.
 * 查询地点
 */

public class SearchLocationActivity extends BaseAppActivity implements OnGetPoiSearchResultListener {
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.list_lv)
    ListView listLv;

    private MapUtil mapUtil;
    private PoiSearch mPoiSearch;

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
    }

    @Override
    public void initDataResume() {

    }



    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        List<PoiAddrInfo> poiAddrInfos = poiResult.getAllAddr();

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }


    @OnTextChanged
    public void setSearchView(CharSequence charSequence){
        mPoiSearch = PoiSearch.newInstance();
        mapUtil.searchPoi(this, mPoiSearch,
                new PoiCitySearchOption().city(PubInfo.KEY_LOCATION).keyword(charSequence.toString()).pageNum(15));
    }
}
