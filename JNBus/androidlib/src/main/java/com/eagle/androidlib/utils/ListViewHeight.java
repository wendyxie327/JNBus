package com.eagle.androidlib.utils;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 设置整个界面的滚动条
 * @author XieWenqian
 *
 */
public class ListViewHeight {

	// 动态设置listview的高度,设置整个界面的滚动条
	public void setListViewHeight(ListView listview,Context context) {
		int totalHeight = 0;
		ListAdapter adapter = listview.getAdapter();
		if (null != adapter) {
			for (int i = 0; i < adapter.getCount(); i++) {
				View listItem = adapter.getView(i, null, listview);
				if (null != listItem) {
					listItem.measure(MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels, MeasureSpec.EXACTLY), 0);// 注意listview子项必须为LinearLayout才能调用该方法
					totalHeight += listItem.getMeasuredHeight();
				}
			}

			ViewGroup.LayoutParams params = listview.getLayoutParams();
			params.height = totalHeight
					+ (listview.getDividerHeight() * (listview.getCount() - 1));
			listview.setLayoutParams(params);
			listview.setFocusable(false);//防止重绘listview后，页面显示在最下方
		}
	}
}
