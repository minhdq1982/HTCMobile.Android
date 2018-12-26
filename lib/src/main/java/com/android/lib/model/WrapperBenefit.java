package com.android.lib.model;

import java.util.List;

/**
 * Create by Ngocji on 10/23/2018
 **/


public class WrapperBenefit {
    int carId;
    String carName;
    String carLicensePlates;
    String agencyName;
    Card card;
    List<WrapperDetailBenefit> list;

    boolean isShowExpand = false;

    public boolean isShowExpand() {
        return isShowExpand;
    }

    public void setShowExpand(boolean showExpand) {
        isShowExpand = showExpand;
    }

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

    public String getCarLicensePlates() {
        return carLicensePlates;
    }

    public void setCarLicensePlates(String carLicensePlates) {
        this.carLicensePlates = carLicensePlates;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<WrapperDetailBenefit> getList() {
        return list;
    }

    public void setList(List<WrapperDetailBenefit> list) {
        this.list = list;
    }

    public static class WrapperDetailBenefit {
        private int id;
        private String carLisencePlate;
        private String content;
        private String detail;

        private String address;
        private String hotline;
        private String website;

        public String getCarLisencePlate() {
            return carLisencePlate;
        }

        public void setCarLisencePlate(String carLisencePlate) {
            this.carLisencePlate = carLisencePlate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getHotline() {
            return hotline;
        }

        public void setHotline(String hotline) {
            this.hotline = hotline;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
