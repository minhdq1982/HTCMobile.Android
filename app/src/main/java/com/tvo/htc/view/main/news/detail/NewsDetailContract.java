package com.tvo.htc.view.main.news.detail;

import com.android.lib.model.News;
import com.tvo.htc.view.BaseContract;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface NewsDetailContract {
    interface View extends BaseContract.View {
        void getNewsDetail(News news);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getNewsDetail(int id);
    }
}
