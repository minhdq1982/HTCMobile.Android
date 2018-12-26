package com.tvo.htc.view.main.profile.setting;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.LoginResponse;
import com.android.lib.model.response.UpdateResponse;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.view.BasePresenter;

public class SettingPresenter extends BasePresenter<SettingContract.View> implements SettingContract.Presenter {
    public SettingPresenter(Context context) {
        super(context);
    }

    private LoginResponse mLoginResponse;

    @Override
    public void loadSetting(LoginResponse loginResponse) {
        mLoginResponse = loginResponse;
        boolean notification = mLoginResponse.getData().isNotification();
        boolean newsfeed = mLoginResponse.getData().isNewsfeed();

        getView().displaySetting(notification, newsfeed);
    }

    @Override
    public void changeSetting(boolean checked, boolean isNoti) {
        boolean notification, newsfeed;
        if (isNoti) {
            notification = checked;
            newsfeed = mLoginResponse.getData().isNewsfeed();
        } else {
            notification = mLoginResponse.getData().isNotification();
            newsfeed = checked;
        }
        RESTManager.getInstance().updateSetting(
                notification,
                newsfeed,
                new IRequestListener<UpdateResponse>(
                        new HTTPRequestOption(
                                false,
                                false
                        )
                ) {
                    @Override
                    public void onCompleted(UpdateResponse data) {
                        super.onCompleted(data);
                        if (data.getMessage() != null) {
                            mLoginResponse.getData().setNotification(notification);
                            mLoginResponse.getData().setNewsfeed(newsfeed);
                            LocalDataManager.getInstance(getContext()).saveLoginResponse(mLoginResponse);
                        }
                    }
                });
    }

    @Override
    public void logout() {
        RESTManager.getInstance().logout();
    }
}
