package com.tvo.htc.view.main.profile.card.history.item;

import android.util.Pair;

import com.android.lib.model.History;
import com.tvo.htc.view.BaseContract;

import java.util.Date;
import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface ItemContract {
    interface View extends BaseContract.View {
        void displayListHistory(List<History> histories);

        void updateListLoadMore(List<History> histories);

        void getDataFailed();

        void getDate(Date date, boolean isStartDate);

        void startLoadData();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadData(History.Type type, int cardId, String time,String cardNumber, String membershipCode, boolean refreshing);

        void loadMoreData(int skipCount, boolean refreshing);

        void loadDate(boolean isStartDate);

        void changeTimeHistory(String timeStart, String timeEnd);
    }
}
