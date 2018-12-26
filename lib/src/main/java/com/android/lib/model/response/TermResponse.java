package com.android.lib.model.response;

public class TermResponse {

    /**
     * data : {"content":"ewrewrfdfdd"}
     * status : Success
     * code : 200
     * message : null
     */

    private Data data;
    private String status;
    private int code;
    private Object message;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public static class Data {
        /**
         * content : ewrewrfdfdd
         */

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
