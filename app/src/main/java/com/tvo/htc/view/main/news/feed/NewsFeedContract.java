package com.tvo.htc.view.main.news.feed;

import com.android.lib.model.response.NewsFeedResponse;
import com.android.lib.model.response.NewsListResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface NewsFeedContract {
    interface View extends BaseContract.View {

        void addList(List<NewsFeedResponse.Data> list, boolean isAllowLoadMore);

        void getLoadFail();

        void updateList(List<NewsFeedResponse.Data> mList, boolean isAllowLoadMore);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData(int skipCount, boolean refreshing);
    }
}
