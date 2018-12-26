package com.tvo.htc.view.main.login.confirm;

import com.tvo.htc.view.BaseContract;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface LoginConfirmContract {
    interface View extends BaseContract.View {
        void displayErrorConfirmCode(String message);

        void displaySuccessConfirmCode(boolean isProfileFull);

        void loginSuccess(String phone);

        void onTimeOut(String message);

        void onTimeCount(String message);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void handleConfirmCode(String phone, String code);

        void resentCode(String phone);

        void getCountDown();

        void stopCountDown();
    }
}
