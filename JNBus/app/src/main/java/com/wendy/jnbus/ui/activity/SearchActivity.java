package com.wendy.jnbus.ui.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

import com.wendy.jnbus.R;
import com.wendy.jnbus.persistence.BusShare;
import com.wendy.jnbus.ui.fragment.SearchBusListFragment;
import com.wendy.jnbus.ui.base.BaseAppActivity;
import com.wendy.jnbus.util.PubInfo;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Wendy on 2017/1/1.
 */
public class SearchActivity extends BaseAppActivity {

    private static final String TAG = "SearchActivity";

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);// 去除返回按钮显示

        if (searchBusListFragment==null){
            searchBusListFragment = new SearchBusListFragment();
            refreshFrag = searchBusListFragment;
            getSupportFragmentManager().beginTransaction().add(R.id.searchListFg, searchBusListFragment).commit();
        }
    }

    @Override
    public void initDataResume() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_clear:// 清除查询历史记录
                BusShare.setKeySearchHistory(null);
                clickSearchBtn();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PubInfo.SEARCH2BUSLINE_REQUEST:
                searchContentET.setText("");
                clickSearchBtn();
                break;
        }
    }

    /**
     * 点击查询按钮，进行查询，并刷新当前界面
     */
    @OnClick({R.id.search_btn})
    public void clickSearchBtn(){
        refreshFrag.refresh(searchContentET.getText().toString());
    }
}