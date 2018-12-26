package com.tvo.htc.view.main.news.market;

import com.android.lib.model.News;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface NewsMarketContract {
    interface View extends BaseContract.View {
        void getData(List<News> items);

        void updateData(List<News> mList);

        void getDataFailed();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadData(int startOffset, boolean refreshing);
    }
}
