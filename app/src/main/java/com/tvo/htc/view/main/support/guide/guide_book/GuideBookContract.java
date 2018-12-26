package com.tvo.htc.view.main.support.guide.guide_book;

import com.android.lib.model.response.GuideBookResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface GuideBookContract {
    interface View extends BaseContract.View {
        void displayListGuideBook(List<GuideBookResponse.Data.Items> list);

        void updateSearchList(List<GuideBookResponse.Data.Items> listSearch);

        void hideEmptyView();

        void showEmptyView();

        void showErrorShowDetailBook();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData(int skipCount, int maxResultCount, String keySearch, int carId);

        void handleBookClick(int position);

        void handleSearch(String key);
    }
}
