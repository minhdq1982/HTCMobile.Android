package com.tvo.htc.view.main.profile.info;

import android.content.Context;

import com.android.lib.model.response.LoginResponse;
import com.android.lib.util.LibUtils;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.view.BasePresenter;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ProfileInfoPresenter extends BasePresenter<ProfileInfoContract.View> implements ProfileInfoContract.Presenter {
    public ProfileInfoPresenter(Context context) {
        super(context);
    }

    @Override
    public void loadInfo(LoginResponse response) {
        if (response != null && response.getData() != null) {
            LoginResponse.Data data = response.getData();
            getView().displayInfo(
                    data.getPhoneNumber(),
                    LibUtils.getFormatBirthday(data.getBirthday()),
                    data.getIdentityCard(),
                    data.getEmailAddress(),
                    data.getAddress());
        }
    }

    @Override
    public void updateProfile() {
        loadInfo(LocalDataManager.getInstance(getContext()).getLoginResponse());
    }
}
