package com.wendy.jnbus.ui.widget;

import android.content.Context;
import android.view.ViewGroup;

import com.wendy.jnbus.vo.BusDetail;

import java.util.List;

/**
 * Created by Administrator on 2016/12/30 0030.
 */

public class BusViews extends ViewGroup {

    public List<BusDetail> busDetails;

    public BusViews(Context context){
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if ( busDetails !=null && busDetails.size()>0 ){
            for(int i=0; i< busDetails.size() ; i++){
                BusView busView = new BusView(getContext());
                busView.setCarId( busDetails.get(i).getCardId() );
                busView.layout();
            }
        }
    }

    public List<BusDetail> getBusDetails() {
        return busDetails;
    }

    public void setBusDetails(List<BusDetail> busDetails) {
        this.busDetails = busDetails;
    }
}
