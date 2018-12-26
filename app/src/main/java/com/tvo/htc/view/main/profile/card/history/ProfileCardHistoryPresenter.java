package com.tvo.htc.view.main.profile.card.history;

import android.content.Context;
import android.util.Pair;

import com.android.lib.util.LibUtils;
import com.tvo.htc.R;
import com.tvo.htc.view.BasePresenter;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ProfileCardHistoryPresenter extends BasePresenter<ProfileCardHistoryContract.View> implements ProfileCardHistoryContract.Presenter {
    private List<String> mTabList;
    private int mCardId;
    private Pair<String, String> mPreviousTime;

    private String mMembershipCode;

    public ProfileCardHistoryPresenter(Context context) {
        super(context);
    }

    @Override
    public void initData(int cardId, String cardNumber, String membershipCode) {
        mMembershipCode = membershipCode;
        if (cardId == -1) {
            getView().displayErrorLoadHistoryCard();
        } else {
            mCardId = cardId;
            mTabList = Arrays.asList(getContext().getResources().getStringArray(R.array.profile_card_history_arr_tab));
            mPreviousTime = getDefaultTime();
            getView().displayListViewPager(mTabList, cardId, mPreviousTime, cardNumber, membershipCode);
        }
    }

    @Override
    public void handleViewPagerChange(int i) {
        getView().changeTabSelect(mTabList.get(i));
    }

    @Override
    public void handleTabSelect(String tabTitle) {
        int index = mTabList.indexOf(tabTitle);
        if (index != -1) {
            getView().changeViewPagerItem(index);
        } else {
            getView().changeViewPagerItem(0);
        }
    }

    private Pair<String, String> getDefaultTime() {
        Calendar calendar = Calendar.getInstance();
        String timeEnd = LibUtils.converterDateToString(calendar.getTime());
        calendar.add(Calendar.YEAR, -1);
        String timeStart = LibUtils.converterDateToString(calendar.getTime());
        return new Pair(timeStart, timeEnd);
    }
}
