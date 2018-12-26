package com.tvo.htc.view.main.promotion;

import com.android.lib.model.News;
import com.tvo.htc.view.BaseContract;

import java.util.List;

public interface PromotionContract {

    interface View extends BaseContract.View {

        void displayNewsHome(List<News> news);

        void updateNewsHome(List<News> news);

    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getListNewsHome(int id, int skipCount, boolean refreshing);
    }
}
