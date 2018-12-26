package com.android.lib.model.response;

import java.util.List;

/**
 * Create by Ngocji on 11/8/2018
 **/


public class CarCategoryResponse extends BaseResponse {

    private List<Category> data;

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    public static class Category {
        /**
         * name : string
         * id : 0
         */

        private String name;
        private int id;

        public Category(int id, String name) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
