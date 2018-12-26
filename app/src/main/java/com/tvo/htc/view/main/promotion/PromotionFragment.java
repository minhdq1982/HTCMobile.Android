package com.tvo.htc.view.main.promotion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.model.News;
import com.android.lib.model.response.GroupNews;
import com.tvo.htc.R;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.BaseLoadMoreAdapter;
import com.tvo.htc.view.component.CpnCustomRecyclerView;
import com.tvo.htc.view.main.news.detail.NewsDetailFragment;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromotionFragment extends BaseFragment<PromotionContract.Presenter> implements
        PromotionContract.View, BaseLoadMoreAdapter.OnLoadMoreListener, BaseAdapter.OnItemClickListener, CpnCustomRecyclerView.CustomRecyclerViewListener {

    @BindView(R.id.rvPromotions)
    CpnCustomRecyclerView rvPromotions;

    private DetailPromotionAdapter adapter;
    private GroupNews mGroupNews;

    public static PromotionFragment newInstance(GroupNews groupNews) {
        Bundle args = new Bundle();

        PromotionFragment fragment = new PromotionFragment();
        fragment.setArguments(args);
        fragment.mGroupNews = groupNews;
        return fragment;
    }

    @Override
    protected PromotionContract.Presenter createPresenterInstance() {
        return new PromotionPresenter(getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_agency_promotion;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);

        setTitle(mGroupNews.getName());

        getPresenter().getListNewsHome(mGroupNews.getId(), AppConstants.START_OFFSET, false);
//        displayNewsHome(mGroupNews.getItems());
    }

    @Override
    public void onLoadMore(int skipCount) {
        getPresenter().getListNewsHome(mGroupNews.getId(), skipCount, false);
    }

    @Override
    public void displayNewsHome(List<News> news) {
        setAdapter(news, true);
    }

    @Override
    public void updateNewsHome(List<News> news) {
        setAdapter(news, false);
    }

    @Override
    public void onItemClick(BaseAdapter adapter, View view, int position) {
        News item = (News) adapter.getItemAtPosition(position);
        FragmentUtil.startFragment(getContext(), NewsDetailFragment.newInstance(mGroupNews, item), null);
    }

    @Override
    public void onRefresh() {
        getPresenter().getListNewsHome(mGroupNews.getId(), AppConstants.START_OFFSET, true);
    }

    private void setAdapter(List<News> items, boolean isUpdate) {
        if (adapter == null) {
            adapter = new DetailPromotionAdapter(getActivity(), rvPromotions.getRecyclerView(), items, AppConstants.PAGE_LIMIT);
            adapter.setOnLoadMoreListener(this);
            adapter.setOnItemClickListener(this);
            rvPromotions.setAdapter(adapter);
            rvPromotions.setCustomRecyclerViewListener(this);
        } else {
            if (isUpdate) {
                adapter.updateData(items);
            } else {
                adapter.addMoreData(items);
            }
        }
        rvPromotions.setRefreshing(false);
    }
}
