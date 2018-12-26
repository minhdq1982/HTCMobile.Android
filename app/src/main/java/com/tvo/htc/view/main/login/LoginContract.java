package com.tvo.htc.view.main.login;

import com.tvo.htc.view.BaseContract;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface LoginContract {
    interface View extends BaseContract.View {
        void loginSuccess(String phone);

        void loginFailed(String message);

        void showMessageError(String message);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void login(String phone);
    }
}
