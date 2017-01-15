package com.wendy.jnbus.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.eagle.androidlib.net.SubscriberOnNextListener;
import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;
import com.wendy.jnbus.net.BusHttpMethod;
import com.wendy.jnbus.persistence.BusShare;
import com.wendy.jnbus.ui.activity.LineBusActivity;
import com.wendy.jnbus.ui.activity.SearchActivity;
import com.wendy.jnbus.ui.adapter.SearchBusAdapter;
import com.wendy.jnbus.ui.base.BaseListFragment;
import com.wendy.jnbus.util.PubInfo;
import com.wendy.jnbus.vo.BusLine;
import com.wendy.jnbus.vo.PageInfoResult;
import com.wendy.jnbus.vo.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wendy on 2017/1/1.
 */
public class SearchBusListFragment extends BaseListFragment<BusLine> implements SearchActivity.RefreshFrag {

    private static final String TAG = "SearchBusListFragment";
    private static final int REQUEST_BUSLINE = 1000;

    private String searchLine ;
    private List<BusLine> busLines;
    private EditText searchContentET;

    @Override
    public Response<BusLine> getListResultFromService() {
        stopRefresh();
        this.searchLine = searchContentET.getText().toString();
        if ( searchLine!=null && !"".equals(searchLine)){
            // 根据线路，获取具体线路
            SubscriberOnNextListener<PageInfoResult<BusLine>> busLineSub = new SubscriberOnNextListener<PageInfoResult<BusLine>>() {
                @Override
                public void onNext(PageInfoResult<BusLine> busLinePageInfoResult) {
                    if (busLinePageInfoResult !=null && busLinePageInfoResult.getResult()!=null){
                        busLines = busLinePageInfoResult.getResult();
                    } else{
                        Logger.d(TAG, "busLinePageInfoResult is null");
                    }

                    if ( busLines == null ) busLines = new ArrayList<>();
                    adapter.setItems( busLines);
                }
            };
            BusHttpMethod.queryBusLine(getContext() , busLineSub, searchLine, 0,20,refreshSrl  );

        }else {
            busLines = BusShare.getKeySearchHistory();
            if ( busLines == null ) busLines = new ArrayList<>();
            Collections.reverse(busLines);// 查询记录倒序显示，最新添加的在最上方
            adapter.setItems( busLines);
        }

        return null;
    }

    @Override
    public void initAdapter() {
        searchContentET = (EditText) getActivity().findViewById(R.id.search_content_et);
        adapter = new SearchBusAdapter( getActivity());
    }

    @Override
    public int setNothingException() {
        return 0;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), LineBusActivity.class);
        intent.putExtra("lineId", busLines.get(position).getId() );
        intent.putExtra("stationName",busLines.get(position).getLineName());
        BusShare.addKeySearchHistory(busLines.get(position));
        getActivity().startActivityForResult(intent, PubInfo.SEARCH2BUSLINE_REQUEST);
    }

    @Override
    public void refresh(String searchLine) {
        getListResultFromService();
    }
}
