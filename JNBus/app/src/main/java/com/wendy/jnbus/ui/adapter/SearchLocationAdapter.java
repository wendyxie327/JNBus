package com.wendy.jnbus.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Tip;
import com.eagle.androidlib.baseUI.BaseListAdapter;
import com.wendy.jnbus.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：展示地址列表
 * 创建人：XieWQ
 * 创建时间：2017/8/18 0018 上午 9:50
 */
public class SearchLocationAdapter extends BaseListAdapter<Tip> {


    public SearchLocationAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.item_search_location, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Tip tip = itemList.get(i);
        viewHolder.addressLocationTv.setText(tip.getAddress());
        viewHolder.addressNameTv.setText(tip.getName());
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.address_name_tv)
        TextView addressNameTv;
        @BindView(R.id.address_location_tv)
        TextView addressLocationTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
