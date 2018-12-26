package com.tvo.htc.view.main.profile.edit.crop;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.TermResponse;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.view.BasePresenter;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class CropPresenter extends BasePresenter<CropContract.View> implements CropContract.Presenter {
    public CropPresenter(Context context) {
        super(context);
    }

}
