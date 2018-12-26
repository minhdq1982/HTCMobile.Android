package com.tvo.htc.view.main.promotion;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.News;
import com.android.lib.model.response.NewsListResponse;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import static com.tvo.htc.util.AppConstants.START_OFFSET;

public class PromotionPresenter extends BasePresenter<PromotionContract.View> implements PromotionContract.Presenter {

    private List<News> mList;

    PromotionPresenter(Context context) {
        super(context);
    }

    @Override
    public void getListNewsHome(int id, int skipCount, boolean refreshing) {
        if (refreshing) {
            mList = new ArrayList<>();
        }
        HTTPRequestOption httpRequestOption = new HTTPRequestOption(false, false);
        if (skipCount == 0 && !refreshing) {
            httpRequestOption = new HTTPRequestOption(true, true);
        }
        RESTManager.getInstance().getListNewsHome(id, skipCount, AppConstants.PAGE_LIMIT,
                new IRequestListener<NewsListResponse>(httpRequestOption) {
                    @Override
                    public void onCompleted(NewsListResponse data) {
                        super.onCompleted(data);
                        if (skipCount == START_OFFSET) {
                            mList = data.getData().getItems();
                            if (getView() != null) {
                                getView().displayNewsHome(mList);
                            }
                        } else {
                            if (getView() != null) {
                                getView().updateNewsHome(data.getData().getItems());
                            }
                        }
                    }
                });
    }
}
