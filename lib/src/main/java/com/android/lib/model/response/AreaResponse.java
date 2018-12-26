package com.android.lib.model.response;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreaResponse extends BaseResponse {
    /**
     * data : [{"code":"MIENBAC","name":"Miền Bắc","fee":null,"note":null,"id":1},{"code":"MTR","name":"Miền Trung","fee":null,"note":null,"id":2},{"code":"MNAM","name":"Miền Nam","fee":null,"note":null,"id":3},{"code":"VN","name":"Viet Nam","fee":null,"note":null,"id":9},{"code":"TN","name":"Tây Nguyên","fee":null,"note":null,"id":11},{"code":"MĐ","name":"Miền Đông","fee":null,"note":null,"id":13},{"code":"trungbo","name":"Trung Bô","fee":null,"note":null,"id":17}]
     * status : null
     * message : null
     */

    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        /**
         * code : MIENBAC
         * name : Miền Bắc
         * fee : null
         * note : null
         * id : 1
         */

        @SerializedName("code")
        private String codeX;
        private String name;
        private Object fee;
        private Object note;
        private int id;

        public Data(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @NonNull
        @Override
        public String toString() {
            return name;
        }

        public String getCode() {
            return codeX;
        }

        public void setCode(String codeX) {
            this.codeX = codeX;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getFee() {
            return fee;
        }

        public void setFee(Object fee) {
            this.fee = fee;
        }

        public Object getNote() {
            return note;
        }

        public void setNote(Object note) {
            this.note = note;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


    }


}
