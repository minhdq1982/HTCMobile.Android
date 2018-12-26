package com.android.lib.model.response;

/**
 * Create by Ngocji on 11/13/2018
 **/


public class PostAppointmentResponse extends BaseResponse {
    private AppointmentResponse.Data data;

    public AppointmentResponse.Data getData() {
        return data;
    }

    public void setData(AppointmentResponse.Data data) {
        this.data = data;
    }
}
