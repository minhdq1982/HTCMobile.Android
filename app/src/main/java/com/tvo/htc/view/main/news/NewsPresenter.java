package com.tvo.htc.view.main.news;

import android.content.Context;

import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.view.BasePresenter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ThinhNH on 3/20/2018.
 */

public class NewsPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {

    private LoginResponse.Data mUserData;

    NewsPresenter(Context context) {
        super(context);
        if (LocalDataManager.getInstance(getContext()).getLoginResponse() != null)
            mUserData = LocalDataManager.getInstance(getContext()).getLoginResponse().getData();
    }

    @Override
    public void loadDataNews() {
        List<String> listTitle;
        if (mUserData != null) {
            if (mUserData.isNewsfeed()) {
                listTitle = Arrays.asList(getContext().getResources().getStringArray(R.array.news_arr_tab_title));
                getView().displayDataNews(listTitle, true);
            } else {
                listTitle = Arrays.asList(getContext().getResources().getStringArray(R.array.news_arr_tab_title_no_news_feed));
                getView().displayDataNews(listTitle, false);
            }
        } else {
            listTitle = Arrays.asList(getContext().getResources().getStringArray(R.array.news_arr_tab_title));
            getView().displayDataNews(listTitle, true);
        }
    }
}
