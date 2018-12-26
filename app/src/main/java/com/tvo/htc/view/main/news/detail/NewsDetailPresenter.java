package com.tvo.htc.view.main.news.detail;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.NewsResponse;
import com.tvo.htc.view.BasePresenter;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class NewsDetailPresenter extends BasePresenter<NewsDetailContract.View> implements NewsDetailContract.Presenter {
    public NewsDetailPresenter(Context context) {
        super(context);
    }

    @Override
    public void getNewsDetail(int id) {
        RESTManager.getInstance().getNewsDetail(id, new IRequestListener<NewsResponse>(
                new HTTPRequestOption(false, false)) {
            @Override
            public void onCompleted(NewsResponse data) {
                super.onCompleted(data);

                if (getView() != null) {
                    getView().getNewsDetail(data.getNews());
                }
            }
        });
    }
}
