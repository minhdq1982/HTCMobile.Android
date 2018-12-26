package com.android.lib.model.response;

import com.google.gson.annotations.SerializedName;

public class RegisterTestDriveResponse extends BaseResponse {

    /**
     * data : {"carId":3,"cityId":1,"agencyId":1,"name":"string","phone":"string","email":"string","note":"string"}
     * message : null
     */

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public static class Data {
        /**
         * carId : 3
         * cityId : 1
         * agencyId : 1
         * name : string
         * phone : string
         * email : string
         * note : string
         */

        private int carId;
        private int cityId;
        private int agencyId;
        private String name;
        private String phone;
        private String email;
        private String note;

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getAgencyId() {
            return agencyId;
        }

        public void setAgencyId(int agencyId) {
            this.agencyId = agencyId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}
