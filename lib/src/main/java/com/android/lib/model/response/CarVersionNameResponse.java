package com.android.lib.model.response;

import android.support.annotation.NonNull;

import java.util.List;

public class CarVersionNameResponse extends BaseResponse {


    /**
     * data : [{"carVersionName":"Grand i10 1.2 AT (BA)"}]
     * status : null
     */

    private List<CarVersion> data;

    public List<CarVersion> getData() {
        return data;
    }

    public void setData(List<CarVersion> data) {
        this.data = data;
    }

    public static class CarVersion {
        /**
         * carVersionName : Grand i10 1.2 AT (BA)
         */

        private String carVersionName;

        public String getCarVersionName() {
            return carVersionName;
        }

        public void setCarVersionName(String carVersionName) {
            this.carVersionName = carVersionName;
        }

        public CarVersion(String carVersionName) {
            this.carVersionName = carVersionName;
        }
        @NonNull
        @Override
        public String toString() {
            return carVersionName;
        }
    }
}
