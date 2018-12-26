package com.tvo.htc.view.main.support;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.SettingResponse;
import com.android.lib.util.Logger;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.view.BasePresenter;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class SupportPresenter extends BasePresenter<SupportContract.View> implements SupportContract.Presenter {


    private SettingResponse.Setting mSetting;

    public SupportPresenter(Context context) {
        super(context);
        mSetting = SessionDataManager.getInstance().getSetting();
    }

    @Override
    public void loadHotline() {
        if (mSetting == null) {
            RESTManager.getInstance().getSetting(new IRequestListener<SettingResponse>() {
                @Override
                public void onCompleted(SettingResponse data) {
                    super.onCompleted(data);
                    Logger.d("Setting get api done!!!");
                    mSetting = data.getData();
                    LocalDataManager.getInstance(getContext()).saveSetting(data.getData());
                    SessionDataManager.getInstance().setSetting(data.getData());
                    getView().loadHotline(mSetting.getHotline(), mSetting.getHotline_Complain());
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                    SessionDataManager.getInstance().setSetting(LocalDataManager.getInstance(getContext()).getSetting());
                }
            });
        } else {
            getView().loadHotline(mSetting.getHotline(), mSetting.getHotline_Complain());
        }
    }
}
