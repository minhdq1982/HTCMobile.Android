package com.android.lib.model.response;

public class SurveyResponse extends BaseResponse {

    /**
     * data : {"number":0,"token":"aca","fullName":"","phoneNumber":"","email":"","address":"","dayPayment":"0001-01-01T00:00:00","carName":"","agencyName":"","status":0,"dateAnswer":"","daySent":"0001-01-01T00:00:00","dayExpired":"","surveyType":0,"id":0}
     */

    public enum SurveyType {
        NEW_CAR("NewCar"), REPAIR_PROTECTION("RepairProtection");
        public String name;

        SurveyType(String name) {
            this.name = name;
        }
    }

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        /**
         * number : 0
         * token :
         * fullName :
         * phoneNumber :
         * email :
         * address :
         * dayPayment : 0001-01-01T00:00:00
         * carName :
         * agencyName :
         * status : 0
         * dateAnswer :
         * daySent : 0001-01-01T00:00:00
         * dayExpired :
         * surveyType : 0
         * id : 0
         */

        private int number;
        private String token;
        private String fullName;
        private String phoneNumber;
        private String email;
        private String address;
        private String dayPayment;
        private String carName;
        private String agencyName;
        private int status;
        private String dateAnswer;
        private String daySent;
        private String dayExpired;
        private int surveyType;
        private int id;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDayPayment() {
            return dayPayment;
        }

        public void setDayPayment(String dayPayment) {
            this.dayPayment = dayPayment;
        }

        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public String getAgencyName() {
            return agencyName;
        }

        public void setAgencyName(String agencyName) {
            this.agencyName = agencyName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDateAnswer() {
            return dateAnswer;
        }

        public void setDateAnswer(String dateAnswer) {
            this.dateAnswer = dateAnswer;
        }

        public String getDaySent() {
            return daySent;
        }

        public void setDaySent(String daySent) {
            this.daySent = daySent;
        }

        public String getDayExpired() {
            return dayExpired;
        }

        public void setDayExpired(String dayExpired) {
            this.dayExpired = dayExpired;
        }

        public SurveyType getSurveyType() {
            return SurveyType.values()[surveyType];
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
