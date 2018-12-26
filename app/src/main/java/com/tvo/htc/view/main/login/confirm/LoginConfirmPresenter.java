package com.tvo.htc.view.main.login.confirm;

import android.content.Context;
import android.os.CountDownTimer;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.LoginResponse;
import com.android.lib.model.response.SimpleResponse;
import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.view.BasePresenter;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class LoginConfirmPresenter extends BasePresenter<LoginConfirmContract.View> implements LoginConfirmContract.Presenter {

    private CountDownTimer countDownTimer = null;
    private final long millisInFuture = 60000;
    private final long countDownInterval = 1000;

    LoginConfirmPresenter(Context context) {
        super(context);
    }

    @Override
    public void handleConfirmCode(String phone, String code) {
        if (code.isEmpty()) {
            getView().displayErrorConfirmCode(getContext().getString(R.string.login_confirm_input_empty));
        } else {
            Logger.d(phone + "-" + code);
            RESTManager.getInstance().verifyLogin(phone, code, new IRequestListener<LoginResponse>() {
                @Override
                public void onCompleted(LoginResponse data) {
                    super.onCompleted(data);
                    if (data.getData() != null) {
                        stopCountDown();
                        LocalDataManager.getInstance(getContext()).saveAccessToken(data.getData().getAccessToken());
                        LocalDataManager.getInstance(getContext()).saveLoginResponse(data);
                        getView().displaySuccessConfirmCode(data.getData().isProfileFull());
                    }
                }
            });
        }

    }

    @Override
    public void resentCode(String phone) {
        String deviceId = LocalDataManager.getInstance(getContext()).getDeviceID();
        if (deviceId == null || deviceId.isEmpty()) {
            deviceId = "sample_device_id";
        }
        RESTManager.getInstance().login(phone, deviceId, "", new IRequestListener<SimpleResponse>() {
            @Override
            public void onCompleted(SimpleResponse data) {
                super.onCompleted(data);
                getView().loginSuccess(phone);
            }
        });
    }

    @Override
    public void getCountDown() {
        stopCountDown();
        countDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long milliseconds) {
                long minutes = (milliseconds / 1000) / 60;
                long seconds = (milliseconds / 1000) % 60;
                getView().onTimeCount(String.format(getContext().getString(R.string.login_confirm_time_out_in), minutes, seconds));
            }

            @Override
            public void onFinish() {
                getView().onTimeOut(getContext().getString(R.string.login_confirm_time_out));
            }
        }.start();
    }

    @Override
    public void stopCountDown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}
