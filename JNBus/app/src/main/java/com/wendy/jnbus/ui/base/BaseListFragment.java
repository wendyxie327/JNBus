package com.wendy.jnbus.ui.base;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.eagle.androidlib.baseUI.BaseFragment;
import com.eagle.androidlib.baseUI.BaseListAdapter;
import com.eagle.androidlib.widget.RefreshLayout;
import com.wendy.jnbus.R;
import com.wendy.jnbus.vo.Response;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/2/24 0024.
 */
public abstract class BaseListFragment<T> extends BaseFragment implements AdapterView.OnItemClickListener {

    @BindView(R.id.list_lv)
    ListView listLv;
    @BindView(R.id.refresh_srl)
    protected RefreshLayout refreshSrl;

    protected ArrayList<T> list;
    protected BaseListAdapter<T> adapter;


    @Override
    public int getLayoutID() {
        return R.layout.base_fragment_list;
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
        initAdapter();
        listLv.setAdapter(adapter);
        listLv.setOnItemClickListener(this);

        getListResultFromService();

        refreshSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getListResultFromService();
            }
        });
    }


    @Override
    public void initDataCreate() {

    }

    @Override
    public void initDataResume() {

    }

    /**
     * 停止刷新转圈的显示
     */
    public void stopRefresh(){
        if ( refreshSrl!=null && refreshSrl.isRefreshing()){
            refreshSrl.setRefreshing(false);
        }
    }

    /**
     * 获取列表数据
     *
     * @return
     */
    abstract public Response<T> getListResultFromService();

    /**
     * 初始化adapter
     */
    abstract public void initAdapter();

    /**
     * 设置没有数据时的显示内容
     *
     * @return
     */
    abstract public int setNothingException();

}
