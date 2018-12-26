package com.tvo.htc.view.main.profile.car;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.ActionCarResponse;
import com.android.lib.model.response.LoginResponse;
import com.android.lib.model.response.LoginResponse.Data.Car;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.event.EventCar;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ProfileCarPresenter extends BasePresenter<ProfileCarContract.View> implements ProfileCarContract.Presenter {
    public ProfileCarPresenter(Context context) {
        super(context);
    }

    private ArrayList<Car> mListCar = new ArrayList<>();
    private LoginResponse mLoginResponse;

    @Override
    public void loadListCar() {
        mLoginResponse = LocalDataManager.getInstance(getContext()).getLoginResponse();
        if (mLoginResponse != null && mLoginResponse.getData() != null) {
            List<Car> cars = mLoginResponse.getData().getCars();
            if (cars != null) {
                mListCar.addAll(cars);
            }
            getView().displayListCar(mListCar);
        }
    }

    @Override
    public void handleDeleteCar(int position) {
        Car car = mListCar.get(position);
        RESTManager.getInstance().deleteCar(car.getId(), new IRequestListener<ActionCarResponse>() {
            @Override
            public void onCompleted(ActionCarResponse data) {
                super.onCompleted(data);
                mListCar.remove(position);
                updateLocalData();
                getView().updateListCar();
                getView().showSuccessDeleteCar();
            }
        });
    }

    @Override
    public void handleEditCar(int position) {
        getView().startEditCar(mListCar.get(position));
    }

    @Override
    public void handleEventCar(EventCar event) {
        if (event.isAddNewCar()) {
            mListCar.add(event.getCar());
            updateLocalData();
            getView().updateListCar();
            getView().scrollToPosition(mListCar.size());
        } else {
            for (int i = 0; i < mListCar.size(); i++) {
                if (event.getCar().getId() == mListCar.get(i).getId()) {
                    mListCar.set(i, event.getCar());
                    updateLocalData();
                    getView().updateListCar();
                    getView().scrollToPosition(i);
                    return;
                }
            }
        }
    }

    private void updateLocalData() {
        LoginResponse response = LocalDataManager.getInstance(getContext()).getLoginResponse();
        if (response != null && response.getData() != null && response.getData().getCars() != null) {
            response.getData().setCars(mListCar);
            LocalDataManager.getInstance(getContext()).saveLoginResponse(response);
        }
    }
}
