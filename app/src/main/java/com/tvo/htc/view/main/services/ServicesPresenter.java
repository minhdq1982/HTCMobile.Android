package com.tvo.htc.view.main.services;

import android.content.Context;

import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.view.BasePresenter;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ServicesPresenter extends BasePresenter<ServicesContract.View> implements ServicesContract.Presenter {
    public ServicesPresenter(Context context) {
        super(context);
    }

    @Override
    public void saveAccepted() {
        LocalDataManager.getInstance(getContext()).saveAcceptedTermConditional();
    }
}
