package com.tvo.htc.view.main.buycar.registration;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.Car;
import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.AgenciesResponse.Agency;
import com.android.lib.model.response.CarsResponse;
import com.android.lib.model.response.CityResponse;
import com.android.lib.model.response.CityResponse.City;
import com.android.lib.model.response.RegisterTestDriveResponse;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.util.Utils;
import com.tvo.htc.util.ValidateFieldUtils;
import com.tvo.htc.view.BasePresenter;
import com.tvo.htc.view.component.CpnSpinner;

import java.util.ArrayList;
import java.util.List;

import static com.tvo.htc.util.ValidateFieldUtils.Type.MAIL;
import static com.tvo.htc.util.ValidateFieldUtils.Type.NAME;
import static com.tvo.htc.util.ValidateFieldUtils.Type.PHONE;
import static com.tvo.htc.util.ValidateFieldUtils.Type.SPINNER_AGENCY;
import static com.tvo.htc.util.ValidateFieldUtils.Type.SPINNER_CITY;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class RegistrationPresenter extends BasePresenter<RegistrationContract.View> implements RegistrationContract.Presenter {

    private List<City> mListCity;
    private List<Agency> mListAgency;
    private List<Car> mListCar;
    private ValidateFieldUtils validate;

    public RegistrationPresenter(Context context) {
        super(context);
        mListCar = SessionDataManager.getInstance().getCars();
        mListCity = SessionDataManager.getInstance().getCities();
        mListAgency = SessionDataManager.getInstance().getAgencies();
        validate = new ValidateFieldUtils(context);
    }

    @Override
    public void loadData() {
        loadCity();
        loadCar();
    }

    @Override
    public void onRegister(CpnSpinner carSpinner, CpnSpinner citySpinner, CpnSpinner agencySpinner, String name, String phone, String email, String note, boolean updateProfile) {
        if (!validate.hasValidate(citySpinner.getSelectedPosition(), SPINNER_CITY)) return;
        if (!validate.hasValidate(agencySpinner.getSelectedPosition(), SPINNER_AGENCY)) return;
        if (!validate.hasValidate(name, NAME)) return;
        if (!validate.hasValidate(email, MAIL)) return;
        if (!validate.hasValidate(phone, PHONE)) return;

        Car car = carSpinner.getSelectedData();
        City city = citySpinner.getSelectedData();
        Agency agency = agencySpinner.getSelectedData();
        if (car != null && city != null && agency != null) {
            RESTManager.getInstance().registerTestDrive(
                    car.getId(),
                    city.getId(),
                    agency.getId(),
                    name,
                    phone,
                    email,
                    note,
                    updateProfile,
                    new IRequestListener<RegisterTestDriveResponse>() {
                        @Override
                        public void onCompleted(RegisterTestDriveResponse data) {
                            super.onCompleted(data);
                            getView().showMessageSuccess(getContext().getString(R.string.registration_register_success));
                            if (updateProfile)
                                LocalDataManager.getInstance(getContext()).updateProfile(name, email);
                        }
                    });
        }
    }

    private void loadCity() {
        if (mListCity == null) {
            RESTManager.getInstance().getCities(new IRequestListener<CityResponse>() {
                @Override
                public void onCompleted(CityResponse data) {
                    super.onCompleted(data);
                    if (data.getData() != null) {
                        mListCity = data.getData();
                        SessionDataManager.getInstance().setCities(mListCity);
                        getView().displayCity(data.getData());
                    }
                }
            });
        } else {
            getView().displayCity(mListCity);
        }
    }

    private void loadCar() {
        if (mListCar == null) {
            RESTManager.getInstance().getCars(new IRequestListener<CarsResponse>() {
                @Override
                public void onCompleted(CarsResponse data) {
                    super.onCompleted(data);
                    if (data.getData() != null) {
                        mListCar = data.getData();
                        SessionDataManager.getInstance().setCars(mListCar);
                        getView().displayCar(data.getData());
                    }
                }
            });
        } else {
            getView().displayCar(mListCar);
        }
    }

    @Override
    public void loadAgency(int carId) {
        if (mListAgency == null) {
            RESTManager.getInstance().getAgencies(new IRequestListener<AgenciesResponse>() {
                @Override
                public void onCompleted(AgenciesResponse data) {
                    super.onCompleted(data);
                    if (data.getData() != null) {
                        mListAgency = data.getData();
                        SessionDataManager.getInstance().setAgencies(mListAgency);
                        getView().displayAgency(getFilterAgency(data.getData(), carId));
                    }
                }
            });
        } else {
            getView().displayAgency(getFilterAgency(mListAgency, carId));
        }
    }

    private List<Agency> getFilterAgency(List<AgenciesResponse.Agency> agencies, int carId) {
        List<Agency> list = new ArrayList<>();
        for (Agency agencyItem : agencies) {
            if (agencyItem.getCityId() == carId) {
                list.add(agencyItem);
            }
        }
        return list;
    }
}
