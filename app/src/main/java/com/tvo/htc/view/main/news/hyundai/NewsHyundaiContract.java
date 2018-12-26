package com.tvo.htc.view.main.news.hyundai;

import com.android.lib.model.News;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface NewsHyundaiContract {
    interface View extends BaseContract.View {
        void getData(List<News> items);

        void updateData(List<News> items);

        void getDataFailed();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadData(int skipCount, boolean refreshing);
    }
}
