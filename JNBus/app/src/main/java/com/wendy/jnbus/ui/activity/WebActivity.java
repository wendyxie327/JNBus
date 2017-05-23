package com.wendy.jnbus.ui.activity;

import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.eagle.androidlib.utils.ToastManager;
import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.base.BaseAppActivity;

import butterknife.BindView;

public class WebActivity extends BaseAppActivity {

    @BindView(R.id.webview)
    WebView webView;

    private String title;
    private String url;

    @Override
    public int getLayoutID() {
        return R.layout.activity_web;
    }

    @Override
    public void initBundle() {
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
    }

    @Override
    public void initDataCreate() {
        initToolbar(title);

        if ( url == null || !url.startsWith("http")){
            ToastManager.getInstance(getApplicationContext()).show(getString(R.string.nothing_exception));
            return;
        }


        try {
            webView.loadUrl(url);// 设置访问地址,直接加载网页、图片并显示

            // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    view.loadUrl(url);

                    // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    return true;
                }
            });

            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    if (newProgress == 100) {
                        //加载结束

                    } else {

                    }

                }
            });
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);// 支持js
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);// 优先使用缓存

        }catch (Exception e){
            ToastManager.getInstance(getApplicationContext()).show(getString(R.string.nothing_exception));
        }

    }

    @Override
    public void initDataResume() {

    }


    /**
     * 设置返回上一界面
     */
    private boolean goBack() {

        if (webView.canGoBack()) {
            webView.goBack();// 返回上一页面

            return true;

        } else {
            this.finish();
            return false;
        }
    }



    // 改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            return goBack();
        }
        return super.onKeyDown(keyCode, event);
    }

}
