package com.tvo.htc.view.main.buycar.registration;

import com.android.lib.model.Car;
import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.CityResponse;
import com.tvo.htc.view.BaseContract;
import com.tvo.htc.view.component.CpnSpinner;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface RegistrationContract {
    interface View extends BaseContract.View {
        void displayCity(List<CityResponse.City> cities);

        void displayAgency(List<AgenciesResponse.Agency> strings);

        void displayCar(List<Car> buyCarResponses);

        void showMessageSuccess(String string);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData();

        void onRegister(CpnSpinner car, CpnSpinner city, CpnSpinner agency, String name, String phone, String email, String note, boolean updateProfile);

        void loadAgency(int carId);
    }
}
