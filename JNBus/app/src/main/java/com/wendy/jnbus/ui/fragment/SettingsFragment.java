package com.wendy.jnbus.ui.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.View;

import com.eagle.androidlib.net.SubscriberOnNextListener;
import com.eagle.androidlib.utils.AppUtil;
import com.eagle.androidlib.utils.Logger;
import com.eagle.androidlib.widget.MaterialDialog;
import com.wendy.jnbus.R;
import com.wendy.jnbus.net.NoAddressHttpMethod;
import com.wendy.jnbus.persistence.BusShare;
import com.wendy.jnbus.ui.JNBusApplication;
import com.wendy.jnbus.vo.Version;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class SettingsFragment extends PreferenceFragment {

    private static final String TAG = "SettingsFragment";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_settings);


        Preference versionPref = findPreference("app_version");
        versionPref.setSummary(AppUtil.getVersionName(JNBusApplication.getContext()));

        Preference setUpdatePref = findPreference("set_update");
        setUpdatePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                NoAddressHttpMethod.getInstance().checkLastestVersion(getActivity(), new SubscriberOnNextListener<Version>() {
                    @Override
                    public void onNext(Version version) {
                        Logger.d(TAG,"version="+version.toString());
                        final MaterialDialog dialog = new MaterialDialog(getActivity());
                        dialog.setPositiveButton(R.string.dialog_sure, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
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
                });
                return false;
            }
        });
    }



}
