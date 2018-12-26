package com.tvo.htc.view.main.support.guide.warranty_policy;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.WarrantyPolicyResponse;
import com.tvo.htc.view.BasePresenter;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class WarrantyPolicyPresenter extends BasePresenter<WarrantyPolicyContract.View> implements WarrantyPolicyContract.Presenter {
    public WarrantyPolicyPresenter(Context context) {
        super(context);
    }

    @Override
    public void loadData() {
        RESTManager.getInstance().getWarrantyPolicy(new IRequestListener<WarrantyPolicyResponse>() {
            @Override
            public void onCompleted(WarrantyPolicyResponse data) {
                super.onCompleted(data);
                getView().displayListWarrantyPolicy(data.getData());
            }
        });
    }
}
