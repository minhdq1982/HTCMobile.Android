package com.tvo.htc.view.main.login;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.SimpleResponse;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BasePresenter;

/**
 * Created by ThinhNH on 3/20/2018.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    public LoginPresenter(Context context) {
        super(context);
    }

    @Override
    public void login(String phone) {
        String deviceId = LocalDataManager.getInstance(getContext()).getDeviceID();
        if (!Utils.isValidPhoneNumber(phone)) {
            getView().showMessageError(getContext().getString(R.string.make_appointment_error_phone));
            return;
        }
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
}
