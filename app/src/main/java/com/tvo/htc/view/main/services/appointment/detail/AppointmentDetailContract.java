package com.tvo.htc.view.main.services.appointment.detail;

import android.os.Bundle;

import com.android.lib.model.response.AppointmentResponse;
import com.tvo.htc.view.BaseContract;

/**
 * Created by Ngocji on 3/21/2018.
 */

public interface AppointmentDetailContract {
    interface View extends BaseContract.View {

        void displayErrorGetAppointmentDetail();

        void displayAppointmentDetail(AppointmentResponse.Data data);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void initData(Bundle arguments);
    }
}
