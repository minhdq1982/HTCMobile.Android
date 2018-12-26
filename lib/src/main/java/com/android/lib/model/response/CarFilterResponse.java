package com.android.lib.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarFilterResponse extends BaseResponse {

    /**
     * data : {"totalCount":0,"items":[{"carId":0,"versionName":"string","price":0,"unit":"string","capacity":0,"gear":"string","image":"string"}]}
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
         * totalCount : 0
         * items : [{"carId":0,"versionName":"string","price":0,"unit":"string","capacity":0,"gear":"string","image":"string"}]
         */

        private int totalCount;
        private List<CarItem> items;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<CarItem> getItems() {
            return items;
        }

        public void setItems(List<CarItem> items) {
            this.items = items;
        }

        public static class CarItem {
            /**
             * carId : 0
             * versionName : string
             * price : 0
             * unit : string
             * capacity : 0
             * gear : string
             * image : string
             * carName: string
             */

            private int carId;
            private String carName;
            private String versionName;
            @SerializedName("id")
            private int versionId;
            private int price;
            private String unit;
            private int capacity;
            private String gear;
            private String image;

            public int getVersionId() {
                return versionId;
            }

            public void setVersionId(int versionId) {
                this.versionId = versionId;
            }

            public String getCarName() {
                return carName;
            }

            public void setCarName(String carName) {
                this.carName = carName;
            }

            public int getCarId() {
                return carId;
            }

            public void setCarId(int carId) {
                this.carId = carId;
            }

            public String getVersionName() {
                return versionName;
            }

            public void setVersionName(String versionName) {
                this.versionName = versionName;
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

            public int getCapacity() {
                return capacity;
            }

            public void setCapacity(int capacity) {
                this.capacity = capacity;
            }

            public String getGear() {
                return gear;
            }

            public void setGear(String gear) {
                this.gear = gear;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
