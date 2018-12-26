package com.tvo.htc.view.main.profile.card.history;

import android.util.Pair;

import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface ProfileCardHistoryContract {
    interface View extends BaseContract.View {
        void displayListViewPager(List<String> listTab, int cardId, Pair<String, String> time, String cardNumber, String membershipCode);

        void changeTabSelect(String title);

        void changeViewPagerItem(int i);

        void displayErrorLoadHistoryCard();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void initData(int cardId, String cardNumber, String membershipCode);

        void handleViewPagerChange(int i);

        void handleTabSelect(String tabTitle);
    }
}
