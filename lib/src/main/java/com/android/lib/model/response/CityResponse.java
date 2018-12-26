package com.android.lib.model.response;

import java.util.List;

public class CityResponse extends BaseResponse {

    /**
     * data : [{"name":"Ha Noi","areaId":1,"id":1},{"name":"Da Nang","areaId":2,"id":2},{"name":"Hue","areaId":2,"id":3},{"name":"Ho Chi Minh","areaId":3,"id":4},{"name":"Nam Định 123","areaId":1,"id":5}]
     * message : null
     */

    private List<City> data;

    public List<City> getData() {
        return data;
    }

    public void setData(List<City> data) {
        this.data = data;
    }

    public static class City {
        /**
         * name : Ha Noi
         * areaId : 1
         * id : 1
         */

        private String name;
        private int areaId;
        private int id;

        public City(String name, int areaId, int id) {
            this.name = name;
            this.areaId = areaId;
            this.id = id;
        }

        public City(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
