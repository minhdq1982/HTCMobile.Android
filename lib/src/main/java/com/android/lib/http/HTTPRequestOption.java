package com.android.lib.http;

public class HTTPRequestOption {
    private boolean isShowLoading = true;
    private boolean isShowError = true;
    private int mFeatureID = -1;

    public HTTPRequestOption(int featureID) {
        this.mFeatureID = featureID;
    }

    public HTTPRequestOption(boolean isShowError, boolean isShowLoading, int featureID) {
        this.isShowError = isShowError;
        this.isShowLoading = isShowLoading;
        this.mFeatureID = featureID;
    }

    public HTTPRequestOption(boolean isShowError, boolean isShowLoading) {
        this.isShowError = isShowError;
        this.isShowLoading = isShowLoading;
    }

    public HTTPRequestOption() {

    }

    public void setFeatureID(int mFeatureID) {
        this.mFeatureID = mFeatureID;
    }

    public boolean isShowError() {
        return isShowError;
    }

    public boolean isShowLoading() {
        return isShowLoading;
    }

    public int getFeatureID() {
        return mFeatureID;
    }

    public void setIsShowError(boolean isShowError) {
        this.isShowError = isShowError;
    }

    public void setIsShowLoading(boolean isShowLoading) {
        this.isShowLoading = isShowLoading;
    }
}
