package com.android.lib.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AgenciesResponse extends BaseResponse {

    private List<Agency> data;

    public List<Agency> getData() {
        return data;
    }

    public void setData(List<Agency> data) {
        this.data = data;
    }

    public static class Agency implements Parcelable {
        /**
         * cityId : 0
         * cityName : string
         * code : string
         * address : string
         * phone : string
         * hotline : string
         * website : string
         * image : string
         * longitude : 0
         * latitude : 0
         * display : true
         * name : string
         * detail : string
         * isActive : true
         * id : 0
         */

        private int cityId;
        private String cityName;
        @SerializedName("code")
        private String codeX;
        private String address;
        private String phone;
        private String hotline;
        private String website;
        private String image;
        private double longitude;
        private double latitude;
        private boolean display;
        private String name;
        private String detail;
        private boolean isActive;
        private int id;
        private String email;
        private String hotlineSale;
        private String hotlineService;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHotlineSale() {
            return hotlineSale;
        }

        public void setHotlineSale(String hotlineSale) {
            this.hotlineSale = hotlineSale;
        }

        public String getHotlineService() {
            return hotlineService;
        }

        public void setHotlineService(String hotlineService) {
            this.hotlineService = hotlineService;
        }

        public Agency(String name, int id, int cityId) {
            this.cityId = cityId;
            this.name = name;
            this.id = id;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCodeX() {
            return codeX;
        }

        public void setCodeX(String codeX) {
            this.codeX = codeX;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public boolean isDisplay() {
            return display;
        }

        public void setDisplay(boolean display) {
            this.display = display;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public boolean isIsActive() {
            return isActive;
        }

        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        @Override
        public String toString() {
            return getName();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.cityId);
            dest.writeString(this.cityName);
            dest.writeString(this.codeX);
            dest.writeString(this.address);
            dest.writeString(this.phone);
            dest.writeString(this.hotline);
            dest.writeString(this.website);
            dest.writeString(this.image);
            dest.writeDouble(this.longitude);
            dest.writeDouble(this.latitude);
            dest.writeByte(this.display ? (byte) 1 : (byte) 0);
            dest.writeString(this.name);
            dest.writeString(this.detail);
            dest.writeByte(this.isActive ? (byte) 1 : (byte) 0);
            dest.writeInt(this.id);
        }

        public Agency() {
        }

        protected Agency(Parcel in) {
            this.cityId = in.readInt();
            this.cityName = in.readString();
            this.codeX = in.readString();
            this.address = in.readString();
            this.phone = in.readString();
            this.hotline = in.readString();
            this.website = in.readString();
            this.image = in.readString();
            this.longitude = in.readDouble();
            this.latitude = in.readDouble();
            this.display = in.readByte() != 0;
            this.name = in.readString();
            this.detail = in.readString();
            this.isActive = in.readByte() != 0;
            this.id = in.readInt();
        }

        public static final Creator<Agency> CREATOR = new Creator<Agency>() {
            @Override
            public Agency createFromParcel(Parcel source) {
                return new Agency(source);
            }

            @Override
            public Agency[] newArray(int size) {
                return new Agency[size];
            }
        };
    }
}
