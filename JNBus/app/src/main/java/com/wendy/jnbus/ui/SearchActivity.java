package com.wendy.jnbus.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.base.BaseAppActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Wendy on 2017/1/1.
 */
public class SearchActivity extends BaseAppActivity {

    @BindView(R.id.search_btn)
    ImageButton searchBtn;
    @BindView(R.id.search_content_et)
    EditText searchContentET;
    SearchBusListFragment searchBusListFragment;

    private RefreshFrag refreshFrag;

    public interface RefreshFrag {
        public void refresh(String searchLine);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    public void initBundle() {

    }

    @Override
    public void initDataCreate() {
        if (searchBusListFragment==null){
            searchBusListFragment = new SearchBusListFragment();
            refreshFrag = searchBusListFragment;
            getSupportFragmentManager().beginTransaction().add(R.id.searchListFg, searchBusListFragment).commit();
        }
    }

    @Override
    public void initDataResume() {

    }

    @OnClick({R.id.search_btn})
    public void clickSearchBtn(){
        refreshFrag.refresh(searchContentET.getText().toString());
    }
}