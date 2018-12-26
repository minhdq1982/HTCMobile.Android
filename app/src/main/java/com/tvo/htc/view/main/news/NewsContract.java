package com.tvo.htc.view.main.news;

import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface NewsContract {
    interface View extends BaseContract.View {
        void displayDataNews(List<String> listTitle, boolean hasNewsfeed);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadDataNews();
    }
}
