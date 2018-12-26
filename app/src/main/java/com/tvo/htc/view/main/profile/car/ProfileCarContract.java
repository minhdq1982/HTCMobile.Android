package com.tvo.htc.view.main.profile.car;

import com.android.lib.model.response.LoginResponse.Data.Car;
import com.tvo.htc.model.event.EventCar;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface ProfileCarContract {
    interface View extends BaseContract.View {
        void displayListCar(List<Car> listCar);

        void updateListCar();

        void startEditCar(Car car);

        void scrollToPosition(int i);

        void showSuccessDeleteCar();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadListCar();

        void handleDeleteCar(int position);

        void handleEditCar(int position);

        void handleEventCar(EventCar event);
    }
}
