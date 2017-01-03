package com.wendy.jnbus.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.eagle.androidlib.net.SubscriberOnNextListener;
import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.net.BusHttpMethod;
import com.wendy.jnbus.ui.adapter.SearchBusAdapter;
import com.wendy.jnbus.ui.base.BaseListFragment;
import com.wendy.jnbus.vo.BusLine;
import com.wendy.jnbus.vo.PageInfoResult;
import com.wendy.jnbus.vo.Response;

import java.util.List;

/**
 * Created by Wendy on 2017/1/1.
 */
public class SearchBusListFragment extends BaseListFragment<BusLine> implements SearchActivity.RefreshFrag {

    private static final String TAG = "SearchBusListFragment";

    private String searchLine ;
    private List<BusLine> busLines;

    @Override
    public Response<BusLine> getListResultFromService() {
        if ( searchLine!=null){
            // 根据线路，获取具体线路
            SubscriberOnNextListener<PageInfoResult<BusLine>> busLineSub = new SubscriberOnNextListener<PageInfoResult<BusLine>>() {
                @Override
                public void onNext(PageInfoResult<BusLine> busLinePageInfoResult) {
                    if (busLinePageInfoResult !=null && busLinePageInfoResult.getResult()!=null){
                        Logger.d(TAG, busLinePageInfoResult.toString());
                        adapter.setItems( busLinePageInfoResult.getResult());
                        busLines = busLinePageInfoResult.getResult();
                    } else
                        Logger.d(TAG, "busLinePageInfoResult is null");
                }
            };
            BusHttpMethod.queryOtherBusLine(getContext() , busLineSub, searchLine, 0,20 );
        }

        return null;
    }

    @Override
    public void initAdapter() {
        adapter = new SearchBusAdapter( getActivity());

    }

    @Override
    public int setNothingException() {
        return 0;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("lineId", busLines.get(position).getId() );
        startActivity(intent);
    }

    @Override
    public void refresh(String searchLine) {
        this.searchLine = searchLine;
        getListResultFromService();
    }
}
