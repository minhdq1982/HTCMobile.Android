package com.tvo.htc.view.main.buycar.select_car.choose_car;

import com.android.lib.model.Car;
import com.android.lib.model.response.CarsResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

public interface ChooseCarContract {

    interface View extends BaseContract.View {
        void displayListCar(List<Car> list);

        void showErrorParseCar();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData(String dataQuestion);
    }

}
