package com.wendy.jnbus.ui.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.eagle.androidlib.net.SubscriberOnNextListener;
import com.eagle.androidlib.utils.AppUtil;
import com.eagle.androidlib.widget.MaterialDialog;
import com.wendy.jnbus.R;
import com.wendy.jnbus.config.RequestActivityCode;
import com.wendy.jnbus.net.NoAddressHttpMethod;
import com.wendy.jnbus.persistence.BusShare;
import com.wendy.jnbus.ui.fragment.SearchBusListFragment;
import com.wendy.jnbus.ui.base.BaseAppActivity;
import com.wendy.jnbus.util.KeyboardUtil;
import com.wendy.jnbus.vo.fir.AppVersion;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Wendy on 2017/1/1.
 */
public class SearchActivity extends BaseAppActivity implements View.OnTouchListener, KeyboardUtil.KeyboardViewListener {

    private static final String TAG = "SearchActivity";

    @BindView(R.id.search_btn)
    ImageButton searchBtn;
    @BindView(R.id.search_content_et)
    EditText searchContentET;
    SearchBusListFragment searchBusListFragment;

    private RefreshFrag refreshFrag;
    private KeyboardUtil keyboardUtil;
    private int inputType;


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

        searchContentET.setOnTouchListener(this);
        keyboardUtil = new KeyboardUtil(this, this, searchContentET);
        inputType = searchContentET.getInputType();

//        checkAppUpdate();// 检查版本更新
        if (searchBusListFragment == null) {
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
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_clear:// 清除查询历史记录
                BusShare.setKeySearchHistory(null);
                clickSearchBtn();
                break;
            case R.id.action_setting:
                startActivity(new Intent(SearchActivity.this, SettingsActivity.class));
                break;
//            case R.id.action_haoyun:// 好运巴士
//                String url = getString(R.string.haoyun_bus_url);
//                String title = getString(R.string.menu_haoyun_bus);
//                Intent intent = new Intent(this, WebActivity.class);
//                intent.putExtra("url", url);
//                intent.putExtra("title", title);
//                startActivity(intent);
//                break;
//            case R.id.action_change:
//                startActivity(new Intent(context, ChangeBusActivity.class));
//                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RequestActivityCode.SEARCH_BUSLINE_REQUEST:
                if (searchContentET == null) return;// 防止页面关闭
                searchContentET.setText("");
                clickSearchBtn();
                break;
        }
    }

    /**
     * 点击查询按钮，进行查询，并刷新当前界面
     */
    @OnClick({R.id.search_btn})
    public void clickSearchBtn() {
        /*隐藏软键盘*/
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus().getWindowToken(), 0);
        }
        refreshFrag.refresh(searchContentET.getText().toString());
    }

    /**
     * 点击搜索输入框，弹出输入键盘
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public boolean onTouch(View v, MotionEvent event) {
        // 禁止软键盘弹出
        searchContentET.setShowSoftInputOnFocus(false);
        // 显示键盘
        keyboardUtil.showKeyboard();
        return false;
    }


    @Override
    public void onClickKey(int keyCode) {
        if (keyCode == Keyboard.KEYCODE_CANCEL) {
            clickSearchBtn();
        }
    }

    // 检查应用是否需要升级，如果需要升级，则弹出对话框提示
    // 当连接方式为Wifi时，检查更新
    private void checkAppUpdate() {
        // 当连接方式不是Wifi，并且设置页面“只在Wifi下更新”功能设置为-只在Wifi下更新，则不进行升级检查
        if (!AppUtil.isWifiConnection(getApplicationContext())
                && PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getString(R.string.preference_key_app_update_wifi),false)){
            return;
        }
        // 其他情况均视为需要更新
        NoAddressHttpMethod.getInstance().checkAppUpdate( getAppUpdateListener());
    }

    /**
     * 更新IP地址
     *
     * @return
     */
    private SubscriberOnNextListener<AppVersion> getAppUpdateListener() {
        SubscriberOnNextListener<AppVersion> onNextListener = new SubscriberOnNextListener<AppVersion>() {
            @Override
            public void onNext(AppVersion appVersion) {
                // 显示内容
                if (appVersion != null && appVersion.getVersion() != null) {
                    try {
                        int nowVersionCode = Integer.valueOf(appVersion.getVersion());
                        if (nowVersionCode > AppUtil.getVersionCode(getApplicationContext())) {
                            final MaterialDialog dialog = new MaterialDialog(SearchActivity.this);
                            dialog.setMessage(appVersion.getChangelog());// 更新信息
                            dialog.setTitle(getString(R.string.app_update_dialog_title));
                            dialog.setPositiveButton(R.string.dialog_sure, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    // 更新-“确认”
                                    Uri uri = Uri.parse(getString(R.string.app_update_url));
                                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(it);
                                }
                            });
                            dialog.setNegativeButton(R.string.dialog_no, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss(); //取消
                                }
                            });
                            dialog.show();
                        }
                    } catch (Exception e) {
                    }

                }

            }
        };
        return onNextListener;
    }

}