package com.tvo.htc.view.account.term;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.TermResponse;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.view.BasePresenter;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class TermPresenter extends BasePresenter<TermContract.View> implements TermContract.Presenter {
    public TermPresenter(Context context) {
        super(context);
    }

    @Override
    public void saveAccepted() {
        LocalDataManager.getInstance(getContext()).saveAcceptedTermConditional();
    }

    @Override
    public void loadData() {
        RESTManager.getInstance().getTerm(new IRequestListener<TermResponse>() {
            @Override
            public void onCompleted(TermResponse data) {
                super.onCompleted(data);
                getView().getDataSuccess(data);
            }
        });
    }
}
