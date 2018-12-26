package com.tvo.htc.view.main.profile.setting;

import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.view.BaseContract;

public interface SettingContract {
    interface View extends BaseContract.View {

        void displaySetting(boolean notification, boolean newsfeed);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadSetting(LoginResponse loginResponse);

        void changeSetting(boolean checked, boolean isNoti);

        void logout();
    }
}
