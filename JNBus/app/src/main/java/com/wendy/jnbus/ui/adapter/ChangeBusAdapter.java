package com.wendy.jnbus.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusStep;
import com.eagle.androidlib.baseUI.BaseListAdapter;
import com.eagle.androidlib.utils.Logger;
import com.wendy.jnbus.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：
 * 创建人：XieWQ
 * 创建时间：2017/8/18 0018 下午 15:41
 */
public class ChangeBusAdapter extends BaseListAdapter<BusPath> {

    public ChangeBusAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.item_change_bus, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }

        BusPath busPath = itemList.get(i);
        StringBuilder contentStr = new StringBuilder();
        contentStr.append(busPath.getDuration()).append("分钟 ")
                .append(busPath.getCost()).append("元 ")
                .append("步行").append(busPath.getWalkDistance()).append("米");
        viewHolder.costTv.setText(contentStr.toString());

        StringBuilder pathStr = new StringBuilder("");
        for (BusStep busStep: busPath.getSteps()) {
            if (busStep.getBusLines() != null && busStep.getBusLines().size() >0){
                Logger.d(context, "buslines is not null");
                for (BusLineItem busLineItem: busStep.getBusLines()){
                    if ( busLineItem != null && busLineItem.getBusLineName() != null){
                        Logger.d(context, "busLineItem is not null");
                        pathStr.append( busLineItem.getBusLineName()).append("-");
                    }
                }
            }
        }
        viewHolder.pathTv.setText( pathStr.toString() );
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.path_tv)
        TextView pathTv;
        @BindView(R.id.cost_tv)
        TextView costTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
