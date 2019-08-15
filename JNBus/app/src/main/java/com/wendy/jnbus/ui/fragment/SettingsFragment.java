package com.wendy.jnbus.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.View;

import com.eagle.androidlib.net.SubscriberOnNextListener;
import com.eagle.androidlib.utils.AppUtil;
import com.eagle.androidlib.utils.Logger;
import com.eagle.androidlib.widget.MaterialDialog;
import com.wendy.jnbus.R;
import com.wendy.jnbus.config.PubInfo;
import com.wendy.jnbus.net.NoAddressHttpMethod;
import com.wendy.jnbus.persistence.BusShare;
import com.wendy.jnbus.ui.JNBusApplication;
import com.wendy.jnbus.util.PackageUtil;
import com.wendy.jnbus.vo.Address;
import com.wendy.jnbus.vo.Version;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class SettingsFragment extends PreferenceFragment {

    private static final String TAG = "SettingsFragment";
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_settings);
        this.context = getActivity().getBaseContext();

        // 检查APP更新只在Wifi方式下执行
//        CheckBoxPreference wifiPref = (CheckBoxPreference) findPreference(getString(R.string.preference_key_app_update_wifi));
//        wifiPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                return true;// true保存更新后的值；false不保存更新后的值
//            }
//        });


        //显示当前应用版本号
        Preference versionPref = findPreference(getString(R.string.preference_key_app_version));
        versionPref.setSummary(AppUtil.getVersionName(JNBusApplication.getContext()));

        //更新公交IP地址
        Preference setUpdatePref = findPreference(getString(R.string.preference_key_set_update));
        setUpdatePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                NoAddressHttpMethod.getInstance().checkLastestVersion(getActivity(), getVersionListener());
                return false;
            }
        });

        // 跳转到应用市场:如果有酷安，则跳转到酷安应用市场，如果没有，则打开网页
        Preference appUpdatePre = findPreference(getString(R.string.preference_key_app_update));
        appUpdatePre.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Logger.d("TAG", "preference ------------------");
                if (PackageUtil.checkAppInstalled(getActivity().getApplicationContext(), PubInfo.COOL_MARKET_PACKAGE_NAME)){
                    Logger.d("TAG", "install ------------------");
                    PackageUtil.goToMarket(getActivity(), PubInfo.APP_PACKAGE, PubInfo.COOL_MARKET_PACKAGE_NAME, PubInfo.COOL_MARKET_ACT);
                }else {
                    PackageUtil.openUrl(getActivity(), PubInfo.APP_DOWNLOAD_URL);
                }
                return false;
            }
        });
    }

    /**
     * 获取微步最新版本号，并进行设置
     * @return
     */
    private SubscriberOnNextListener<Version> getVersionListener(){
        SubscriberOnNextListener<Version> onNextListener = new SubscriberOnNextListener<Version>() {
            @Override
            public void onNext(Version version) {
                Logger.d(TAG,"version="+version.toString());
                final MaterialDialog dialog = new MaterialDialog(getActivity());
                dialog.setPositiveButton(R.string.dialog_sure, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        NoAddressHttpMethod.getInstance().checkIP(getActivity(),getStatusListener());
                    }
                });

                if ( BusShare.getKeyVersionCode() == version.getVersionCode()){
                    dialog.setMessage(getString(R.string.pref_set_update_dialog_lastest)+ version.getVersionCode());
                }else {
                    BusShare.setKeyVersionCode(version.getVersionCode());
                    dialog.setMessage(getString(R.string.pref_set_update_dialog_change_left)+ version.getVersionCode());
                }
                dialog.show();
            }
        };
        return onNextListener;
    }

    /**
     * 更新IP地址
     * @return
     */
    private SubscriberOnNextListener<Address> getStatusListener(){
        SubscriberOnNextListener<Address> onNextListener = new SubscriberOnNextListener<Address>() {
            @Override
            public void onNext(Address address) {
                Logger.d(TAG,"address="+address.toString());
                final MaterialDialog dialog = new MaterialDialog(getActivity());
                dialog.setPositiveButton(R.string.dialog_sure, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                if ( address!=null){
                    BusShare.setKeyBusIpAndArea(address.getHttpAddr(), address.getName());
                    if (BusShare.getKeyBusIp().equals(address.getHttpAddr())){
                        dialog.setMessage(getString(R.string.pref_set_update_ip_dialog_lastest)+ address.getHttpAddr());
                    }else {
                        dialog.setMessage(getString(R.string.pref_set_update_ip_dialog_change_left)+ address.getHttpAddr());
                    }
                    dialog.show();
                }

            }
        };
        return onNextListener;
    }

}
