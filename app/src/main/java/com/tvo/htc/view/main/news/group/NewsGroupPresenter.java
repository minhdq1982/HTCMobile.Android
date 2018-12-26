package com.tvo.htc.view.main.news.group;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.FacebookGroupResponse;
import com.tvo.htc.view.BasePresenter;

public class NewsGroupPresenter extends BasePresenter<NewsGroupContract.View> implements NewsGroupContract.Presenter {
    NewsGroupPresenter(Context context) {
        super(context);
    }

    @Override
    public void loadData() {
        RESTManager.getInstance().getListFacebookGroup(new IRequestListener<FacebookGroupResponse>() {
            @Override
            public void onCompleted(FacebookGroupResponse data) {
                super.onCompleted(data);
                if(data.getData() != null && getView() != null) {
                    getView().getData(data.getData().getItems());
                }
            }
        });
    }
}
