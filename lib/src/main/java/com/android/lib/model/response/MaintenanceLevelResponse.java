package com.android.lib.model.response;

import java.util.List;

/**
 * Create by Ngocji on 10/19/2018
 **/


public class MaintenanceLevelResponse extends BaseResponse {

    /**
     * data : [{"name":"1","completionTime":"20","description":"1K, 5K, 15K, 25K, 35K, ...","id":1},{"name":"2","completionTime":"30","description":"10K, 30K, 50K, 70K, ...","id":2},{"name":"3","completionTime":"45","description":"20K, 60K \u2026","id":3},{"name":"4","completionTime":"70","description":"40K, 80K, ...","id":4},{"name":"5","completionTime":"100","description":"100K, 150K, ...","id":5}]
     * message : null
     */

    private List<MaintenanceLevel> data;

    public List<MaintenanceLevel> getData() {
        return data;
    }

    public void setData(List<MaintenanceLevel> data) {
        this.data = data;
    }

    public static class MaintenanceLevel {
        /**
         * name : 1
         * completionTime : 20
         * description : 1K, 5K, 15K, 25K, 35K, ...
         * id : 1
         */

        private String name;
        private String completionTime;
        private String description;
        private int id;

        public MaintenanceLevel(String name, String completionTime, String description, int id) {
            this.name = name;
            this.completionTime = completionTime;
            this.description = description;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCompletionTime() {
            return completionTime;
        }

        public void setCompletionTime(String completionTime) {
            this.completionTime = completionTime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return description;
        }
    }
}
