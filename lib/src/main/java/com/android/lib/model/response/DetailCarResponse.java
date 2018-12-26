package com.android.lib.model.response;

import com.android.lib.model.Car;

public class DetailCarResponse extends BaseResponse {

    /**
     * data : {"name":"string","isActive":true,"isDeleted":true,"creationTime":"2018-11-16T07:35:47.139Z","lastModificationTime":"2018-11-16T07:35:47.139Z","deletionTime":"2018-11-16T07:35:47.139Z","creatorUserId":0,"lastModifierUserId":0,"deleterUserId":0,"slogan":"string","shortDescription":"string","highlights":"string","exterior":"string","furniture":"string","operation":"string","safe":"string","convenient":"string","catalog":"string","images":["string"],"version":[{"carId":0,"versionName":"string","price":0,"unit":"string","spec":[{"name":"string","details":[{"name":"string","value":"string"}]}],"id":0}],"id":0}
     */

    private Car data;

    public DetailCarResponse(Car data) {
        this.data = data;
    }

    public Car getData() {
        return data;
    }

    public void setData(Car data) {
        this.data = data;
    }

}
