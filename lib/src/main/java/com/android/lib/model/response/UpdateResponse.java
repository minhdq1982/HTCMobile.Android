package com.android.lib.model.response;

public class UpdateResponse extends BaseResponse {

    /**
     * data : {"carId":0,"carName":"string","vinNo":"string","licensePlate":"string","agencyId":0,"agencyName":"string","purchaseDate":"2018-12-04T04:40:11.093Z","id":0}
     * status : string
     * code : 0
     * message : string
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
         * carId : 0
         * carName : string
         * vinNo : string
         * licensePlate : string
         * agencyId : 0
         * agencyName : string
         * purchaseDate : 2018-12-04T04:40:11.093Z
         * id : 0
         */

        private int carId;
        private String carName;
        private String vinNo;
        private String licensePlate;
        private int agencyId;
        private String agencyName;
        private String purchaseDate;
        private int id;

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public String getVinNo() {
            return vinNo;
        }

        public void setVinNo(String vinNo) {
            this.vinNo = vinNo;
        }

        public String getLicensePlate() {
            return licensePlate;
        }

        public void setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
        }

        public int getAgencyId() {
            return agencyId;
        }

        public void setAgencyId(int agencyId) {
            this.agencyId = agencyId;
        }

        public String getAgencyName() {
            return agencyName;
        }

        public void setAgencyName(String agencyName) {
            this.agencyName = agencyName;
        }

        public String getPurchaseDate() {
            return purchaseDate;
        }

        public void setPurchaseDate(String purchaseDate) {
            this.purchaseDate = purchaseDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
