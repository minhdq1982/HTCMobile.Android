package com.tvo.htc.model;

import com.google.gson.annotations.Expose;

/**
 * Create by Ngocji on 10/18/2018
 **/


public class WrapperDataMakeAppoint {
    @Expose
    private String serviceName;
    @Expose
    private String dateAppointment;
    @Expose
    private int cityId;
    private String cityName;
    @Expose
    private int agencyId;
    private String agencyName;
    @Expose
    private String staffName;
    @Expose
    private String honorifics;
    @Expose
    private String customerName;
    @Expose
    private String customerEmail;
    @Expose
    private String customerPhone;
    @Expose
    private String customerNote;
    @Expose
    private int carId;
    private String carName;

    private int  carVersionId;
    @Expose
    private String versionName;
    @Expose
    private String licensePlates;
    private int status = 0;
    private int id = 0;
    @Expose
    private boolean isUpdateProfile;
    @Expose
    private boolean isAddNewCar;

    public boolean isUpdateProfile() {
        return isUpdateProfile;
    }

    public void setUpdateProfile(boolean updateProfile) {
        isUpdateProfile = updateProfile;
    }

    public boolean isAddNewCar() {
        return isAddNewCar;
    }

    public void setAddNewCar(boolean addNewCar) {
        isAddNewCar = addNewCar;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(String dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCarId() {
        return carId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getHonorifics() {
        return honorifics;
    }

    public void setHonorifics(String honorifics) {
        this.honorifics = honorifics;
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

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarVersionId() {
        return carVersionId;
    }

    public void setCarVersionId(int carVersionId) {
        this.carVersionId = carVersionId;
    }

    public String getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
