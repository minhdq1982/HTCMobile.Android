package com.android.lib.model.response;

import com.android.lib.model.Car;

import java.util.List;

/**
 * Create by Ngocji on 10/31/2018
 **/


public class CarsResponse extends BaseResponse {


    private List<Car> data;

    public List<Car> getData() {
        return data;
    }

    public void setData(List<Car> data) {
        this.data = data;
    }

}
