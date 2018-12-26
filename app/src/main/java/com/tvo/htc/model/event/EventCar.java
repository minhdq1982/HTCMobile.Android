package com.tvo.htc.model.event;

import com.android.lib.model.response.LoginResponse;

/**
 * Create by Ngocji on 11/1/2018
 **/


public class EventCar {
    private LoginResponse.Data.Car car;
    private boolean isAddNewCar;

    public EventCar(LoginResponse.Data.Car car, boolean isAddNewCar) {
        this.car = car;
        this.isAddNewCar = isAddNewCar;
    }

    public LoginResponse.Data.Car getCar() {
        return car;
    }

    public boolean isAddNewCar() {
        return isAddNewCar;
    }
}
