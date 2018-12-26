package com.android.lib.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.lib.util.Logger;

import java.util.List;
import java.util.Random;

/**
 * Create by Ngocji on 10/29/2018
 **/


public class GuideBookResponse extends BaseResponse {

    /**
     * data : {"totalCount":0,"items":[{"carId":0,"carName":"string","title":"string","orderNumber":0,"isActive":true,"lastModificationTime":"2018-11-09T07:10:50.557Z","id":0}]}
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
         * items : [{"carId":0,"carName":"string","title":"string","orderNumber":0,"isActive":true,"lastModificationTime":"2018-11-09T07:10:50.557Z","id":0}]
         */

        private int totalCount;
        private List<Items> items;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<Items> getItems() {
            return items;
        }

        public void setItems(List<Items> items) {
            this.items = items;
        }

        public static class Items implements Parcelable {
            /**
             * carId : 0
             * carName : string
             * title : string
             * orderNumber : 0
             * isActive : true
             * lastModificationTime : 2018-11-09T07:10:50.557Z
             * id : 0
             */

            private int carId;
            private String carName;
            private String title;
            private int orderNumber;
            private boolean isActive;
            private String lastModificationTime;
            private int id;
            private String image;
            private int bgInt;

            public int getBgInt(int position) {
                if (bgInt == 0) {
                    int pos = position % 8;
                    bgInt = pos + 1;
                }
                Logger.e("Bg: " + bgInt);
                return bgInt;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getOrderNumber() {
                return orderNumber;
            }

            public void setOrderNumber(int orderNumber) {
                this.orderNumber = orderNumber;
            }

            public boolean isIsActive() {
                return isActive;
            }

            public void setIsActive(boolean isActive) {
                this.isActive = isActive;
            }

            public String getLastModificationTime() {
                return lastModificationTime;
            }

            public void setLastModificationTime(String lastModificationTime) {
                this.lastModificationTime = lastModificationTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.carId);
                dest.writeString(this.carName);
                dest.writeString(this.title);
                dest.writeInt(this.orderNumber);
                dest.writeByte(this.isActive ? (byte) 1 : (byte) 0);
                dest.writeString(this.lastModificationTime);
                dest.writeInt(this.id);
                dest.writeString(this.image);
                dest.writeInt(this.bgInt);
            }

            public Items() {
            }

            protected Items(Parcel in) {
                this.carId = in.readInt();
                this.carName = in.readString();
                this.title = in.readString();
                this.orderNumber = in.readInt();
                this.isActive = in.readByte() != 0;
                this.lastModificationTime = in.readString();
                this.id = in.readInt();
                this.image = in.readString();
                this.bgInt = in.readInt();
            }

            public static final Creator<Items> CREATOR = new Creator<Items>() {
                @Override
                public Items createFromParcel(Parcel source) {
                    return new Items(source);
                }

                @Override
                public Items[] newArray(int size) {
                    return new Items[size];
                }
            };
        }
    }
}
