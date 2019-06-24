package com.eagle.androidlib.baseUI;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public abstract class BaseFragment extends Fragment {

    protected View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutID(),null);
        initView(savedInstanceState);
        initDataCreate();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initDataResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    /**
     * 设置本界面layoutId
     * @return
     */
    public abstract int getLayoutID();

    /**
     * 初始化界面控件
     * @param savedInstanceState
     */
    public abstract void initView(Bundle savedInstanceState);

    /**
     * 在onCreate方法中初始化数据
     * 如果一些不及时的消息可在此处联网获取
     */
    public abstract void initDataCreate();

    /**
     * 在OnResume方法中初始化数据
     * 及时更新的数据，在此处联网获取
     */
    public abstract void initDataResume();
}
