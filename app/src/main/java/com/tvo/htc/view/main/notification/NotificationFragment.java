package com.tvo.htc.view.main.notification;

import android.os.Bundle;
import android.view.View;

import com.android.lib.model.History;
import com.android.lib.model.response.NotificationResponse.Notification;
import com.tvo.htc.R;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.switch_page.SwitchPageImpl;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.BaseLoadMoreAdapter;
import com.tvo.htc.view.component.CpnCustomRecyclerView;
import com.tvo.htc.view.main.notification.detail.NotificationDetailFragment;
import com.tvo.htc.view.main.profile.card.history.item.HistoryAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class NotificationFragment extends BaseFragment<NotificationContract.Presenter> implements NotificationContract.View, BaseLoadMoreAdapter.OnLoadMoreListener, BaseAdapter.OnItemClickListener, CpnCustomRecyclerView.CustomRecyclerViewListener {

    @BindView(R.id.rvNotification)
    CpnCustomRecyclerView rvNotification;

    private NotificationAdapter adapter;
    private List<Notification> mList;

    public static NotificationFragment newInstance() {

        Bundle args = new Bundle();

        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected NotificationContract.Presenter createPresenterInstance() {
        return new NotificationPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notification;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        rvNotification.setCustomRecyclerViewListener(this);
        getPresenter().loadData(AppConstants.START_OFFSET, true);
    }

    @Override
    public void onLoadMore(int skipCount) {
        getPresenter().loadData(skipCount, false);
    }

    @Override
    public void onItemClick(BaseAdapter adapter, View view, int position) {
        Notification item = (Notification) adapter.getItemAtPosition(position);

        SwitchPageImpl.getInstance().handleNotification(getContext(), item);

        //todo update adapter
        item.setRead(true);
        adapter.notifyItemChanged(position);
    }

    @Override
    public void getData(List<Notification> data) {
        mList = data;
        setAdapter(data, true);
    }

    @Override
    public void updateData(List<Notification> data) {
        setAdapter(data, false);
    }

    @Override
    public void getDataFailed() {
        rvNotification.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        getPresenter().loadData(AppConstants.START_OFFSET, true);
    }


    private void setAdapter(List<Notification> items, boolean isUpdate) {
        if (adapter == null) {
            adapter = new NotificationAdapter(getContext(), rvNotification.getRecyclerView(), mList, AppConstants.PAGE_LIMIT);
            adapter.setOnLoadMoreListener(this);
            adapter.setOnItemClickListener(this);
            rvNotification.setAdapter(adapter);
        } else {
            if (isUpdate) {
                adapter.updateData(items);
            } else {
                adapter.addMoreData(items);
            }
        }
        rvNotification.setRefreshing(false);
    }
}
