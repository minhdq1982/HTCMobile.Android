package com.tvo.htc.view.main.news.hyundai;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.News;
import com.android.lib.model.response.NewsListResponse;
import com.android.lib.util.LibUtils;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.tvo.htc.util.AppConstants.START_OFFSET;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class NewsHyundaiPresenter extends BasePresenter<NewsHyundaiContract.View> implements NewsHyundaiContract.Presenter {

    private List<News> mList;

    public NewsHyundaiPresenter(Context context) {
        super(context);
    }

    @Override
    public void loadData(int skipCount, boolean refreshing) {
        if(refreshing) {
            mList = new ArrayList<>();
        }
        HTTPRequestOption httpRequestOption = new HTTPRequestOption(false, false);
        if(skipCount == 0 && !refreshing) {
            httpRequestOption = new HTTPRequestOption(false, true);
        }
        RESTManager.getInstance().getHTCList(skipCount, AppConstants.PAGE_LIMIT,
                new IRequestListener<NewsListResponse>(httpRequestOption) {
                    @Override
                    public void onCompleted(NewsListResponse data) {
                        super.onCompleted(data);
                        if (skipCount == START_OFFSET) {
                            mList = data.getData().getItems();
                            if (getView() != null) {
                                getView().getData(mList);
                            }
                        } else {
                            if (getView() != null) {
                                getView().updateData(data.getData().getItems());
                            }
                        }
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        super.onFailed(throwable);

                        if (skipCount == START_OFFSET) {
                            if (getView() != null) {
                                getView().getData(new ArrayList<>());
                                getView().getDataFailed();
                            }
                        }
                    }
                });
    }
}
