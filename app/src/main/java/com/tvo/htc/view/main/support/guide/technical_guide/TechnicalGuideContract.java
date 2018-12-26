package com.tvo.htc.view.main.support.guide.technical_guide;

import com.android.lib.model.response.TechnicalGuideResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface TechnicalGuideContract {
    interface View extends BaseContract.View {

        void displayList(List<TechnicalGuideResponse.Item> items);

        void addMoreData(List<TechnicalGuideResponse.Item> items);

        void updateListSearch(List<TechnicalGuideResponse.Item> listSearch);

        void changeRefreshing(boolean refreshing);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData(boolean refreshing);

        void loadMoreData(int skipCount);

        void handleSearch(String key,int skipCount, boolean refresh);

        void saveSessionData();

        void revertCurrentList();
    }
}
