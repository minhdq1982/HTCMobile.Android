package com.android.lib.model.response;

import com.android.lib.model.Car.Version;

import java.util.List;

public class CarVersionResponse extends BaseResponse {

    /**
     * data : [{"carId":0,"versionName":"string","price":0,"unit":"string","code":"string","status":0,"id":0}]
     * status : string
     * code : 0
     * message : string
     */

    private List<Version> data;

    public List<Version> getData() {
        return data;
    }

    public void setData(List<Version> data) {
        this.data = data;
    }

}
