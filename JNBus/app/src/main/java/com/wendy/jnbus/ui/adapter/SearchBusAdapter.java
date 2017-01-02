package com.wendy.jnbus.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eagle.androidlib.baseUI.BaseListAdapter;
import com.wendy.jnbus.R;
import com.wendy.jnbus.vo.BusLine;

/**
 * Created by Wendy on 2017/1/1.
 */
public class SearchBusAdapter extends BaseListAdapter<BusLine> {

    public SearchBusAdapter(Context context){
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_search_bus, null);
            viewHolder.stationNameTV = (TextView)view.findViewById(R.id.stationNameTV);
            viewHolder.lineNameTV = (TextView)view.findViewById(R.id.lineNameTV);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.lineNameTV.setText(itemList.get(i).getLineName());
        viewHolder.stationNameTV.setText(itemList.get(i).getStartStationName()+" - "+ itemList.get(i).getEndStationName());

        return view;
    }

    private class ViewHolder{
        private TextView lineNameTV , stationNameTV;
    }
}
