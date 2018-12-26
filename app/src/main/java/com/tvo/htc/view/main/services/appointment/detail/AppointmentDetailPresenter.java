package com.tvo.htc.view.main.services.appointment.detail;

import android.content.Context;
import android.os.Bundle;

import com.android.lib.model.response.AppointmentResponse;
import com.tvo.htc.view.BasePresenter;

import static com.tvo.htc.view.main.services.appointment.detail.AppointmentDetailFragment.KEY_DATA;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class AppointmentDetailPresenter extends BasePresenter<AppointmentDetailContract.View> implements AppointmentDetailContract.Presenter {
    public AppointmentDetailPresenter(Context context) {
        super(context);
    }

    private AppointmentResponse.Data data;

    @Override
    public void initData(Bundle arguments) {
        if (arguments == null) {
            getView().displayErrorGetAppointmentDetail();
        } else {
            data = arguments.getParcelable(KEY_DATA);
            if (data != null) {
                getView().displayAppointmentDetail(data);
            } else {
                getView().displayErrorGetAppointmentDetail();
            }
        }
    }
}
