package com.wendy.jnbus.ui.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.widget.Toast;

import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.base.BaseAppActivity;

/**
 * Created by Wendy on 2017/8/12.
 */

public class SearchLocationActivity extends BaseAppActivity {
    @Override
    public int getLayoutID() {
        return R.layout.activity_search_location;
    }

    @Override
    public void initBundle() {

    }

    @Override
    public void initDataCreate() {
        Logger.d(context, "ini");
    }

    @Override
    public void initDataResume() {

    }

}
