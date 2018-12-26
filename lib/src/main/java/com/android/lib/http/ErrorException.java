package com.android.lib.http;

/**
 * Created by ThinhNH on 3/26/2018.
 */

public class ErrorException extends Exception {
    private int responseCode;
    private ErrorBody errorBody;

    public ErrorException(String detailMessage) {
        super(detailMessage);
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public ErrorBody getErrorBody() {
        return errorBody;
    }

    public void setErrorBody(ErrorBody errorBody) {
        this.errorBody = errorBody;
    }

    public static class ErrorBody {
        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
