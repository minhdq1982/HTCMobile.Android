package com.tvo.htc.view.main.buycar.select_car.choose_car;

import android.content.Context;

import com.android.lib.model.Car;
import com.google.gson.reflect.TypeToken;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BasePresenter;

import java.util.List;

public class ChooseCarPresenter extends BasePresenter<ChooseCarContract.View> implements ChooseCarContract.Presenter {

    public ChooseCarPresenter(Context context) {
        super(context);
    }

    @Override
    public void loadData(String dataListCar) {
        try {
            if (dataListCar != null && !dataListCar.isEmpty()) {
                List<Car> list = (List<Car>) Utils.stringToList(dataListCar, new TypeToken<List<Car>>() {
                }.getType());
                if (list != null) {
                    getView().displayListCar(list);
                } else {
                    getView().showErrorParseCar();
                }
            }
        } catch (Exception e) {
            getView().showErrorParseCar();
        }
    }

}
