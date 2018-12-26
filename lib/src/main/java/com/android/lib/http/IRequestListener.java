package com.android.lib.http;


/**
 * Callback interface for delivering request responses
 * <p/>
 * Created by PRDCV-TrungT on 5/29/2015.
 */
public class IRequestListener<T> {
    protected HTTPRequestOption mOptional;

    public IRequestListener() {
        this.mOptional = new HTTPRequestOption();
        if (mOptional.isShowLoading()) {
            DialogHandler.getInstance().displayWaitDialog();
        }
    }

    public IRequestListener(HTTPRequestOption optional) {
        this.mOptional = optional;
        if (optional == null) {
            this.mOptional = new HTTPRequestOption();
        }
        if (mOptional.isShowLoading()) {
            DialogHandler.getInstance().displayWaitDialog();
        }
    }

    public void onCompleted(T data) {
        if (mOptional.isShowLoading()) {
            DialogHandler.getInstance().dismissDialog(DialogHandler.ID_WAIT_DIALOG);
        }
    }

    public void onFailed(Throwable throwable) {
        if (mOptional.isShowLoading()) {
            DialogHandler.getInstance().dismissDialog(DialogHandler.ID_WAIT_DIALOG);
        }
        if (mOptional.isShowError()) {
            int featureID = mOptional == null ? -1 : mOptional.getFeatureID();
            DialogHandler.getInstance().displayMessage(throwable, featureID);
        }
    }

    public HTTPRequestOption getOptional() {
        if (mOptional == null) {
            mOptional = new HTTPRequestOption();
        }
        return mOptional;
    }
}
