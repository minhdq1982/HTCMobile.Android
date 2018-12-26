package com.tvo.htc.view.main.profile.info;

import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.view.BaseContract;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface ProfileInfoContract {
    interface View extends BaseContract.View {
        void displayInfo(String phone,
                         String birthday,
                         String identifyId,
                         String email,
                         String address);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadInfo(LoginResponse mLoginResponse);

        void updateProfile();
    }
}
