package com.tvo.htc.view.main.services.make_appointment.info;

import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.CityResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface InfoAppointmentContract {
    interface View extends BaseContract.View {
        void onNextStep(String date, String time, String staffName, int cityId, String cityName, int agencyId, String agencyName);

        void displayTime(List<String> listTime);

        void displayListAgency(List<AgenciesResponse.Agency> agencies);

        void displayCity(List<CityResponse.City> cities);

        void showDatePicker(int year, int month, int dayOfMonth);

    }

    interface Presenter extends BaseContract.Presenter<View> {
        void handleDataInfoAppointment(String date, String time, String staffName, int cityId, String cityName, int agencyId, String agencyName);

        void loadData();

        void filterAgency(int cityId);

        void handlePickDate(String date);
    }
}
