package com.tvo.htc.view.main.profile.card.history.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.android.lib.model.History;
import com.android.lib.util.LibUtils;
import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.BaseLoadMoreAdapter;
import com.tvo.htc.view.component.CpnCustomRecyclerView;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tvo.htc.util.AppConstants.PAGE_LIMIT;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ItemFragment extends BaseFragment<ItemContract.Presenter> implements ItemContract.View, BaseLoadMoreAdapter.OnLoadMoreListener, CpnCustomRecyclerView.CustomRecyclerViewListener {
    public static final String KEY_TYPE_HISTORY = "KEY_TYPE_HISTORY";
    public static final String KEY_CARD_ID = "KEY_CARD_ID";
    public static final String KEY_TIME = "KEY_TIME";
    public static final String KEY_MEMBERSHIP_CODE = "KEY_MEMBERSHIP_CODE";
    public static final String KEY_CARD_NUMBER = "KEY_CARD_NUMBER";
    @BindView(R.id.rvCustomRecyclerView)
    CpnCustomRecyclerView rvHistory;
    @BindView(R.id.txtStartDate)
    TextView txtStartDate;
    @BindView(R.id.txtEndDate)
    TextView txtEndDate;

    private List<History> mList;

    private HistoryAdapter adapter;

    public static ItemFragment newInstance(int type, int cardId, Pair<String, String> time, String cardNumber, String membershipCode) {
        Bundle args = new Bundle();
        args.putInt(KEY_TYPE_HISTORY, type);
        args.putInt(KEY_CARD_ID, cardId);
        args.putString(KEY_TIME, time.first + "*" + time.second);
        args.putString(KEY_MEMBERSHIP_CODE, membershipCode);
        args.putString(KEY_CARD_NUMBER, cardNumber);
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected ItemContract.Presenter createPresenterInstance() {
        return new ItemPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_card_history_item;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        rvHistory.setCustomRecyclerViewListener(this);
        txtStartDate.setText(LibUtils.getLastMonthDate());
        txtEndDate.setText(LibUtils.getCurrentDate());
    }

    @Override
    public void startLoadData() {
        getPresenter().loadData(
                History.Type.values()[getArguments().getInt(KEY_TYPE_HISTORY, 0)],
                getArguments().getInt(KEY_CARD_ID, -1),
                getArguments().getString(KEY_TIME, ""),
                getArguments().getString(KEY_CARD_NUMBER, ""),
                getArguments().getString(KEY_MEMBERSHIP_CODE),
                false);
    }

    @Override
    public void displayListHistory(List<History> histories) {
        mList = histories;
        setAdapter(histories, true);
    }

    @Override
    public void updateListLoadMore(List<History> histories) {
        setAdapter(histories, false);
    }

    @Override
    public void getDataFailed() {
        rvHistory.setRefreshing(false);
    }

    @Override
    public void onLoadMore(int skipCount) {
        getPresenter().loadMoreData(skipCount, false);
    }

    @Override
    public void onRefresh() {
        getPresenter().loadData(
                History.Type.values()[getArguments().getInt(KEY_TYPE_HISTORY, 0)],
                getArguments().getInt(KEY_CARD_ID, -1),
                getArguments().getString(KEY_TIME, ""),
                getArguments().getString(KEY_CARD_NUMBER, ""),
                getArguments().getString(KEY_MEMBERSHIP_CODE),
                true);
    }

    @Override
    public void getDate(Date date, boolean isStartDate) {
        if (isStartDate) {
            txtStartDate.setText(LibUtils.converterDateToString(date));
        } else {
            txtEndDate.setText(LibUtils.converterDateToString(date));
        }
        updateDate();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateDate();
    }

    public void updateDate() {
        Date start = LibUtils.converterStringToDate(txtStartDate.getText().toString());
        Date end = LibUtils.converterStringToDate(txtEndDate.getText().toString());
        getPresenter().changeTimeHistory(LibUtils.convertDateToStringUTCTime(start), LibUtils.convertDateToStringUTCTime(end));
    }

    @OnClick({R.id.btnStartDate, R.id.btnEndDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnStartDate:
                getPresenter().loadDate(true);
                break;
            case R.id.btnEndDate:
                getPresenter().loadDate(false);
                break;
        }
    }

    private void setAdapter(List<History> items, boolean isUpdate) {
        if (adapter == null) {
            adapter = new HistoryAdapter(getContext(), rvHistory.getRecyclerView(), mList, AppConstants.PAGE_LIMIT);
            adapter.setOnLoadMoreListener(this);
            rvHistory.setAdapter(adapter);
        } else {
            if (isUpdate) {
                adapter.updateData(items);
            } else {
                adapter.addMoreData(items);
            }
        }
        rvHistory.setRefreshing(false);
    }
}
