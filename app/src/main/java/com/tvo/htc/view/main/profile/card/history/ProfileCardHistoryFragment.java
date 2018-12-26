package com.tvo.htc.view.main.profile.card.history;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Pair;
import android.view.View;

import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnCustomTabView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ProfileCardHistoryFragment extends BaseFragment<ProfileCardHistoryContract.Presenter> implements ProfileCardHistoryContract.View, CpnCustomTabView.TabListener {
    public static final String KEY_DATA_CARD_ID = "KEY_DATA_CARD_ID";
    public static final String KEY_MEMBERSHIP_CODE = "KEY_MEMBERSHIP_CODE";
    public static final String KEY_MEMBER_CARD_NUMBER = "KEY_CARD_NUMBER";
    @BindView(R.id.cpnCustomTabView)
    CpnCustomTabView cpnCustomTabView;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    HistoryViewPagerAdapter adapter;

    public static ProfileCardHistoryFragment newInstance(int cardId, String cardNumber, String membershipCode) {
        Bundle args = new Bundle();
        args.putInt(KEY_DATA_CARD_ID, cardId);
        args.putString(KEY_MEMBERSHIP_CODE, membershipCode);
        args.putString(KEY_MEMBER_CARD_NUMBER, cardNumber);
        ProfileCardHistoryFragment fragment = new ProfileCardHistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected ProfileCardHistoryContract.Presenter createPresenterInstance() {
        return new ProfileCardHistoryPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_card_history;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                getPresenter().handleViewPagerChange(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        cpnCustomTabView.setTabListener(this);
        getPresenter().initData(
                getArguments().getInt(KEY_DATA_CARD_ID, -1),
                getArguments().getString(KEY_MEMBER_CARD_NUMBER, ""),
                getArguments().getString(KEY_MEMBERSHIP_CODE)
        );
    }

    @Override
    public void displayListViewPager(List<String> listTab, int cardId, Pair<String, String> time, String cardNumber, String membershipCode) {
        int oldItem = viewPager.getCurrentItem();
        adapter = new HistoryViewPagerAdapter(getChildFragmentManager(), listTab, cardId, time, cardNumber, membershipCode);
        viewPager.setOffscreenPageLimit(listTab.size());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(oldItem);
        cpnCustomTabView.setContent(listTab);
        cpnCustomTabView.setTabSelected(listTab.get(oldItem));
    }

    @Override
    public void displayErrorLoadHistoryCard() {
        showMessage(getString(R.string.profile_card_history_error_load_card_id),
                () -> FragmentUtil.removeFragment(getContext()));
    }

    @Override
    public void changeTabSelect(String title) {
        cpnCustomTabView.setTabSelected(title);
    }

    @Override
    public void changeViewPagerItem(int i) {
        viewPager.setCurrentItem(i);
    }

    @Override
    public void onTabSelected(String tabTitle) {
        getPresenter().handleTabSelect(tabTitle);
    }
}


