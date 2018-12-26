package com.tvo.htc.view.main.profile.car.add_car;

import android.os.Bundle;

import com.android.lib.model.Car;
import com.android.lib.model.response.AgenciesResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public interface AddCarContract {
    interface View extends BaseContract.View {

        void displayListCar(List<Car> listCars);

        void displayListAgency(List<AgenciesResponse.Agency> listAgencies);

        void displayMessage(String message);

        void displaySuccessAddCar();

        void displayWaitDialog(boolean isShowing);

        void displayCarEdit(int carPosition,
                            String vinNo,
                            String licensePlate,
                            int agencyPosition,
                            String displayPurchaseDate);

        void displayEditCar();

        void displayAddNewCar();

        void displaySuccessEditCar();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void initData(Bundle arguments);

        void loadData();

        void handleSaveCar(int carId, String VINNo, String licensePlate);
    }
}
