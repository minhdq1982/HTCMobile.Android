package com.android.lib.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PriceAdviceResponse extends BaseResponse {

    /**
     * data : {"carVersionId":0,"carVersionName":"string","areaId":0,"areaName":"string","price":0,"unit":"string","stampDuty":0,"registrationFee":0,"otherFees":[{"code":"string","feeName":"string","feeValue":0,"unit":"string","order":0,"isActive":true,"isDeleted":true,"deleterUserId":0,"deletionTime":"2018-12-24T06:47:09.537Z","lastModificationTime":"2018-12-24T06:47:09.537Z","lastModifierUserId":0,"creationTime":"2018-12-24T06:47:09.537Z","creatorUserId":0,"id":0}],"totalRegistrationFee":0,"totalPay":0,"image":"string"}
     */

    private Price data;

    public Price getData() {
        return data;
    }

    public void setData(Price data) {
        this.data = data;
    }

    public static class Price {
        /**
         * carVersionId : 0
         * carVersionName : string
         * areaId : 0
         * areaName : string
         * price : 0
         * unit : string
         * stampDuty : 0
         * registrationFee : 0
         * otherFees : [{"code":"string","feeName":"string","feeValue":0,"unit":"string","order":0,"isActive":true,"isDeleted":true,"deleterUserId":0,"deletionTime":"2018-12-24T06:47:09.537Z","lastModificationTime":"2018-12-24T06:47:09.537Z","lastModifierUserId":0,"creationTime":"2018-12-24T06:47:09.537Z","creatorUserId":0,"id":0}]
         * totalRegistrationFee : 0
         * totalPay : 0
         * image : string
         */

        private int carVersionId;
        private String carVersionName;
        private int areaId;
        private String areaName;
        private int price;
        private String unit;
        private int stampDuty;
        private int registrationFee;
        private int totalRegistrationFee;
        private int totalPay;
        private String image;
        private List<OtherFees> otherFees;

        public int getCarVersionId() {
            return carVersionId;
        }

        public void setCarVersionId(int carVersionId) {
            this.carVersionId = carVersionId;
        }

        public String getCarVersionName() {
            return carVersionName;
        }

        public void setCarVersionName(String carVersionName) {
            this.carVersionName = carVersionName;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getStampDuty() {
            return stampDuty;
        }

        public void setStampDuty(int stampDuty) {
            this.stampDuty = stampDuty;
        }

        public int getRegistrationFee() {
            return registrationFee;
        }

        public void setRegistrationFee(int registrationFee) {
            this.registrationFee = registrationFee;
        }

        public int getTotalRegistrationFee() {
            return totalRegistrationFee;
        }

        public void setTotalRegistrationFee(int totalRegistrationFee) {
            this.totalRegistrationFee = totalRegistrationFee;
        }

        public int getTotalPay() {
            return totalPay;
        }

        public void setTotalPay(int totalPay) {
            this.totalPay = totalPay;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<OtherFees> getOtherFees() {
            return otherFees;
        }

        public void setOtherFees(List<OtherFees> otherFees) {
            this.otherFees = otherFees;
        }

        public static class OtherFees {
            /**
             * code : string
             * feeName : string
             * feeValue : 0
             * unit : string
             * order : 0
             * isActive : true
             * isDeleted : true
             * deleterUserId : 0
             * deletionTime : 2018-12-24T06:47:09.537Z
             * lastModificationTime : 2018-12-24T06:47:09.537Z
             * lastModifierUserId : 0
             * creationTime : 2018-12-24T06:47:09.537Z
             * creatorUserId : 0
             * id : 0
             */

            @SerializedName("code")
            private String codeX;
            private String feeName;
            private int feeValue;
            private String unit;
            private int order;
            private boolean isActive;
            private boolean isDeleted;
            private int deleterUserId;
            private String deletionTime;
            private String lastModificationTime;
            private int lastModifierUserId;
            private String creationTime;
            private int creatorUserId;
            private int id;

            public String getCodeX() {
                return codeX;
            }

            public void setCodeX(String codeX) {
                this.codeX = codeX;
            }

            public String getFeeName() {
                return feeName;
            }

            public void setFeeName(String feeName) {
                this.feeName = feeName;
            }

            public int getFeeValue() {
                return feeValue;
            }

            public void setFeeValue(int feeValue) {
                this.feeValue = feeValue;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public boolean isIsActive() {
                return isActive;
            }

            public void setIsActive(boolean isActive) {
                this.isActive = isActive;
            }

            public boolean isIsDeleted() {
                return isDeleted;
            }

            public void setIsDeleted(boolean isDeleted) {
                this.isDeleted = isDeleted;
            }

            public int getDeleterUserId() {
                return deleterUserId;
            }

            public void setDeleterUserId(int deleterUserId) {
                this.deleterUserId = deleterUserId;
            }

            public String getDeletionTime() {
                return deletionTime;
            }

            public void setDeletionTime(String deletionTime) {
                this.deletionTime = deletionTime;
            }

            public String getLastModificationTime() {
                return lastModificationTime;
            }

            public void setLastModificationTime(String lastModificationTime) {
                this.lastModificationTime = lastModificationTime;
            }

            public int getLastModifierUserId() {
                return lastModifierUserId;
            }

            public void setLastModifierUserId(int lastModifierUserId) {
                this.lastModifierUserId = lastModifierUserId;
            }

            public String getCreationTime() {
                return creationTime;
            }

            public void setCreationTime(String creationTime) {
                this.creationTime = creationTime;
            }

            public int getCreatorUserId() {
                return creatorUserId;
            }

            public void setCreatorUserId(int creatorUserId) {
                this.creatorUserId = creatorUserId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }

    public static class PriceAdviceList {
        private String title, price;

        public PriceAdviceList(String title, String price) {
            this.title = title;
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
