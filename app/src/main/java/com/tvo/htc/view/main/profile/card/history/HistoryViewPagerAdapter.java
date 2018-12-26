package com.tvo.htc.view.main.profile.card.history;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Pair;

import com.tvo.htc.view.main.profile.card.history.item.ItemFragment;

import java.util.List;

/**
 * Create by Ngocji on 10/25/2018
 **/


public class HistoryViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<String> mTabTitles;
    private final int cardId;
    private final Pair<String, String> mTime;
    private String mMembershipCode;
    private String mCardNumber;


    public HistoryViewPagerAdapter(FragmentManager fm, List<String> mTabTitles, int cardId, Pair<String, String> time, String cardNumber, String membershipCode) {
        super(fm);
        this.mTabTitles = mTabTitles;
        this.cardId = cardId;
        this.mTime = time;
        this.mMembershipCode = membershipCode;
        this.mCardNumber = cardNumber;
    }

    @Override
    public Fragment getItem(int i) {
        return ItemFragment.newInstance(i, cardId, mTime, mCardNumber, mMembershipCode);
    }

    @Override
    public int getCount() {
        return mTabTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles.get(position);
    }
}
