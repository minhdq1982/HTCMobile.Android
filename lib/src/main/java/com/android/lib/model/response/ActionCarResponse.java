package com.android.lib.model.response;

/**
 * Create by Ngocji on 10/31/2018
 **/


public class ActionCarResponse extends BaseResponse {

    /**
     * data : {"carId":0,"carName":"string","vinNo":"string","licensePlate":"string","agencyId":0,"agencyName":"string","purchaseDate":"2018-10-31T09:42:43.154Z","id":0}
     */

    private LoginResponse.Data.Car data;

    public LoginResponse.Data.Car getData() {
        return data;
    }

    public void setData(LoginResponse.Data.Car data) {
        this.data = data;
    }

}
