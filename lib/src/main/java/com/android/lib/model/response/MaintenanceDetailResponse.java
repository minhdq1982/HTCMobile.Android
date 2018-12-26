package com.android.lib.model.response;

import java.util.List;

public class MaintenanceDetailResponse extends BaseResponse {


    /**
     * data : {"carVersionName":"Grand i10 1.2 AT (BA)","agencyId":2,"agencyName":"Huyndai Da Nang tvo","maintenanceLevelId":1,"maintenanceLevelName":"1","completionTime":"20 phút","laborCost":180000,"engineOil":560000,"boilFlushWasher":10000,"engineOilFilter":null,"engineAirFilter":null,"fuelFilter":null,"acAirFilter":null,"bugi":null,"gearCaseOil":null,"engineCoolant":null,"brakeFluid":null,"windshieldWasherFluid":115000,"totalPrice":865000}
     * status : null
     * message : null
     */

    private MaintenanceDetail data;
    private List<MaintenanceList> list;

    public List<MaintenanceList> getList() {
        return list;
    }

    public void setList(List<MaintenanceList> list) {
        this.list = list;
    }

    public MaintenanceDetail getData() {
        return data;
    }

    public void setData(MaintenanceDetail data) {
        this.data = data;
    }

    public static class MaintenanceDetail {
        /**
         * carVersionName : Grand i10 1.2 AT (BA)
         * agencyId : 2
         * agencyName : Huyndai Da Nang tvo
         * maintenanceLevelId : 1
         * maintenanceLevelName : 1
         * completionTime : 20 phút
         * laborCost : 180000
         * engineOil : 560000
         * boilFlushWasher : 10000
         * engineOilFilter : null
         * engineAirFilter : null
         * fuelFilter : null
         * acAirFilter : null
         * bugi : null
         * gearCaseOil : null
         * engineCoolant : null
         * brakeFluid : null
         * windshieldWasherFluid : 115000
         * totalPrice : 865000
         */

        private String carVersionName;
        private int agencyId;
        private String agencyName;
        private int maintenanceLevelId;
        private String maintenanceLevelName;
        private String completionTime;
        private int laborCost;
        private int engineOil;
        private int boilFlushWasher;
        private int engineOilFilter;
        private int engineAirFilter;
        private int fuelFilter;
        private int acAirFilter;
        private int bugi;
        private int gearCaseOil;
        private int engineCoolant;
        private int brakeFluid;
        private int windshieldWasherFluid;
        private int totalPrice;

        public String getCarVersionName() {
            return carVersionName;
        }

        public void setCarVersionName(String carVersionName) {
            this.carVersionName = carVersionName;
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

        public int getMaintenanceLevelId() {
            return maintenanceLevelId;
        }

        public void setMaintenanceLevelId(int maintenanceLevelId) {
            this.maintenanceLevelId = maintenanceLevelId;
        }

        public String getMaintenanceLevelName() {
            return maintenanceLevelName;
        }

        public void setMaintenanceLevelName(String maintenanceLevelName) {
            this.maintenanceLevelName = maintenanceLevelName;
        }

        public String getCompletionTime() {
            return completionTime;
        }

        public void setCompletionTime(String completionTime) {
            this.completionTime = completionTime;
        }

        public int getLaborCost() {
            return laborCost;
        }

        public void setLaborCost(int laborCost) {
            this.laborCost = laborCost;
        }

        public int getEngineOil() {
            return engineOil;
        }

        public void setEngineOil(int engineOil) {
            this.engineOil = engineOil;
        }

        public int getBoilFlushWasher() {
            return boilFlushWasher;
        }

        public void setBoilFlushWasher(int boilFlushWasher) {
            this.boilFlushWasher = boilFlushWasher;
        }

        public int getEngineOilFilter() {
            return engineOilFilter;
        }

        public void setEngineOilFilter(int engineOilFilter) {
            this.engineOilFilter = engineOilFilter;
        }

        public int getEngineAirFilter() {
            return engineAirFilter;
        }

        public void setEngineAirFilter(int engineAirFilter) {
            this.engineAirFilter = engineAirFilter;
        }

        public int getFuelFilter() {
            return fuelFilter;
        }

        public void setFuelFilter(int fuelFilter) {
            this.fuelFilter = fuelFilter;
        }

        public int getAcAirFilter() {
            return acAirFilter;
        }

        public void setAcAirFilter(int acAirFilter) {
            this.acAirFilter = acAirFilter;
        }

        public int getBugi() {
            return bugi;
        }

        public void setBugi(int bugi) {
            this.bugi = bugi;
        }

        public int getGearCaseOil() {
            return gearCaseOil;
        }

        public void setGearCaseOil(int gearCaseOil) {
            this.gearCaseOil = gearCaseOil;
        }

        public int getEngineCoolant() {
            return engineCoolant;
        }

        public void setEngineCoolant(int engineCoolant) {
            this.engineCoolant = engineCoolant;
        }

        public int getBrakeFluid() {
            return brakeFluid;
        }

        public void setBrakeFluid(int brakeFluid) {
            this.brakeFluid = brakeFluid;
        }

        public int getWindshieldWasherFluid() {
            return windshieldWasherFluid;
        }

        public void setWindshieldWasherFluid(int windshieldWasherFluid) {
            this.windshieldWasherFluid = windshieldWasherFluid;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }
    }

    public static class MaintenanceList {
        private String name;
        private int price;

        public MaintenanceList(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
