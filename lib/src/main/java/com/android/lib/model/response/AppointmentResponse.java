package com.android.lib.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Create by Ngocji on 10/18/2018
 **/


public class AppointmentResponse extends BaseResponse implements Parcelable {
    private List<Data> data;

    protected AppointmentResponse(Parcel in) {
    }

    public static final Creator<AppointmentResponse> CREATOR = new Creator<AppointmentResponse>() {
        @Override
        public AppointmentResponse createFromParcel(Parcel in) {
            return new AppointmentResponse(in);
        }

        @Override
        public AppointmentResponse[] newArray(int size) {
            return new AppointmentResponse[size];
        }
    };

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    //TODO: missing field email, list services name, car version name, car license plate, car year, service staff name, note
    enum AppointmentStatusType {
        NEW_APPOINTMENT,
        APPROVED,
        PROCESSING,
        FINISH,
        CANCEL
    }


    public static class Data implements Parcelable {
        /**
         * customerName : string
         * customerEmail : string
         * customerPhone : string
         * customerNote : string
         * dateAppointment : 2018-11-14T01:46:04.608Z
         * staffName : string
         * carName : string
         * carId : 0
         * cityId : 0
         * cityName : string
         * agencyId : 0
         * agencyName : string
         * status : 0
         * honorifics : string
         * carYear : 0
         * licensePlates : string
         * serviceName : string
         * id : 0
         */

        private String customerName;
        private String customerEmail;
        private String customerPhone;
        private String customerNote;
        private String dateAppointment;
        private String staffName;
        private String carName;
        private int carId;
        private int cityId;
        private String cityName;
        private int agencyId;
        private String agencyName;
        @SerializedName("status")
        private int statusX;
        private String honorifics;
        private int carYear;
        private String licensePlates;
        private String serviceName;
        private int id;
        private String versionName;

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        @Override
        public String toString() {
            return serviceName + "\n" + carName + "\n" + customerPhone + "\n" + agencyName + "\n" + dateAppointment;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerEmail() {
            return customerEmail;
        }

        public void setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getCustomerNote() {
            return customerNote;
        }

        public void setCustomerNote(String customerNote) {
            this.customerNote = customerNote;
        }

        public String getDateAppointment() {
            return dateAppointment;
        }

        public void setDateAppointment(String dateAppointment) {
            this.dateAppointment = dateAppointment;
        }

        public String getStaffName() {
            return staffName;
        }

        public void setStaffName(String staffName) {
            this.staffName = staffName;
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

        public int getStatusX() {
            return statusX;
        }

        public void setStatusX(int statusX) {
            this.statusX = statusX;
        }

        public String getHonorifics() {
            return honorifics;
        }

        public void setHonorifics(String honorifics) {
            this.honorifics = honorifics;
        }

        public int getCarYear() {
            return carYear;
        }

        public void setCarYear(int carYear) {
            this.carYear = carYear;
        }

        public String getLicensePlates() {
            return licensePlates;
        }

        public void setLicensePlates(String licensePlates) {
            this.licensePlates = licensePlates;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
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
            dest.writeString(this.customerName);
            dest.writeString(this.customerEmail);
            dest.writeString(this.customerPhone);
            dest.writeString(this.customerNote);
            dest.writeString(this.dateAppointment);
            dest.writeString(this.staffName);
            dest.writeString(this.carName);
            dest.writeInt(this.carId);
            dest.writeInt(this.cityId);
            dest.writeString(this.cityName);
            dest.writeInt(this.agencyId);
            dest.writeString(this.agencyName);
            dest.writeInt(this.statusX);
            dest.writeString(this.honorifics);
            dest.writeInt(this.carYear);
            dest.writeString(this.licensePlates);
            dest.writeString(this.serviceName);
            dest.writeInt(this.id);
        }

        public Data() {
        }

        protected Data(Parcel in) {
            this.customerName = in.readString();
            this.customerEmail = in.readString();
            this.customerPhone = in.readString();
            this.customerNote = in.readString();
            this.dateAppointment = in.readString();
            this.staffName = in.readString();
            this.carName = in.readString();
            this.carId = in.readInt();
            this.cityId = in.readInt();
            this.cityName = in.readString();
            this.agencyId = in.readInt();
            this.agencyName = in.readString();
            this.statusX = in.readInt();
            this.honorifics = in.readString();
            this.carYear = in.readInt();
            this.licensePlates = in.readString();
            this.serviceName = in.readString();
            this.id = in.readInt();
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
}
