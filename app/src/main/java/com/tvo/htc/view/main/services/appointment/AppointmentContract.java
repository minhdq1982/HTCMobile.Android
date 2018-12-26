package com.tvo.htc.view.main.services.appointment;

import com.android.lib.model.response.AppointmentResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by Ngocji on 3/21/2018.
 */

public interface AppointmentContract {
    interface View extends BaseContract.View {
        void displayListAppointment(List<AppointmentResponse.Data> list);

        void updateListAppointment(List<AppointmentResponse.Data> list);

        void displayDetailAppointment(AppointmentResponse.Data data);
        }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadListAppointment();

        void handleAppointmentClicked(int position);
    }
}
