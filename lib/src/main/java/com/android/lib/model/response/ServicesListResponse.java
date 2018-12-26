package com.android.lib.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Ngocji on 10/17/2018
 **/


public class ServicesListResponse extends BaseResponse implements Parcelable {
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> Data) {
        this.data = Data;
    }

    public static class Data implements Parcelable {
        /**
         * Id : 0
         * Name : Bảo hành
         */

        private int id;
        private String iconRes;

        private String name;

        @Expose
        private boolean isSelected;

        public Data(int id, String iconRes) {
            this.id = id;
            this.iconRes = iconRes;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public int getId() {
            return id;
        }

        public String getIconRes() {
            return iconRes;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.iconRes);
            dest.writeString(this.name);
        }

        protected Data(Parcel in) {
            this.id = in.readInt();
            this.iconRes = in.readString();
            this.name = in.readString();
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel source) {
                return new Data(source);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.data);
    }

    public ServicesListResponse() {
    }

    protected ServicesListResponse(Parcel in) {
        this.data = new ArrayList<ServicesListResponse.Data>();
        in.readList(this.data, ServicesListResponse.Data.class.getClassLoader());
    }

    public static final Creator<ServicesListResponse> CREATOR = new Creator<ServicesListResponse>() {
        @Override
        public ServicesListResponse createFromParcel(Parcel source) {
            return new ServicesListResponse(source);
        }

        @Override
        public ServicesListResponse[] newArray(int size) {
            return new ServicesListResponse[size];
        }
    };
}
