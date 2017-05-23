package com.wendy.jnbus.ui.base;

import android.os.Bundle;
import com.eagle.androidlib.baseUI.BaseFragment;

import butterknife.ButterKnife;

/**
 * 类描述：
 * 创建人：XieWQ
 * 创建时间：2017/5/23 0023 下午 16:57
 */
public abstract class BaseAppFragment extends BaseFragment {

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
    }
}
