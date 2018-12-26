package com.tvo.htc.view.main.services.make_appointment.info;

import android.content.Context;

import com.android.lib.model.response.AgenciesResponse.Agency;
import com.android.lib.model.response.CityResponse.City;
import com.android.lib.util.LibUtils;
import com.tvo.htc.R;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.util.Utils;
import com.tvo.htc.util.ValidateFieldUtils;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.tvo.htc.util.ValidateFieldUtils.Type.DATE_TIME_APPOINTMENT;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class InfoAppointmentPresenter extends BasePresenter<InfoAppointmentContract.View> implements InfoAppointmentContract.Presenter {
    public InfoAppointmentPresenter(Context context) {
        super(context);
        validate = new ValidateFieldUtils(context);
    }

    private List<Agency> mListAgency;
    private ValidateFieldUtils validate;

    @Override
    public void loadData() {
        loadAgency();
        loadCity();
        loadTime();
    }

    @Override
    public void filterAgency(int cityId) {
        if (mListAgency != null) {
            List<Agency> list = new ArrayList<>();
            for (Agency item : mListAgency) {
                if (item.getCityId() == cityId) {
                    list.add(item);
                }
            }
            getView().displayListAgency(list);
        }
    }

    @Override
    public void handleDataInfoAppointment(String date, String time, String staffName, int cityId, String cityName, int agencyId, String agencyName) {
        date = Utils.normalizeString(date, false);
        staffName = Utils.normalizeString(staffName, false);
        if (!validate.hasValidate(date, time, DATE_TIME_APPOINTMENT)) return;
        getView().onNextStep(date, time, staffName, cityId, cityName, agencyId, agencyName);
    }

    @Override
    public void handlePickDate(String date) {
        Calendar cal;
        if (LibUtils.isValidTime(date)) {
            cal = LibUtils.getCalMakeAppointMent(date);
        } else {
            cal = Calendar.getInstance();
        }
        getView().showDatePicker(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    }

    private void loadAgency() {
        mListAgency = SessionDataManager.getInstance().getAgencies();
    }

    private void loadCity() {
        List<City> cities = SessionDataManager.getInstance().getCities();
        if (cities != null) {
            getView().displayCity(cities);
            filterAgency(cities.get(0).getId());
        }
    }

    private void loadTime() {
        getView().displayTime(Arrays.asList(getContext().getResources().getStringArray(R.array.make_appointment_info_arr_time)));
    }
}
