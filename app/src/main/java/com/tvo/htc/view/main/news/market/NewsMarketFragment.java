package com.tvo.htc.view.main.news.market;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.android.lib.model.News;
import com.tvo.htc.R;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.BaseLoadMoreAdapter;
import com.tvo.htc.view.component.CpnCustomRecyclerView;
import com.tvo.htc.view.main.news.NewsAdapter;
import com.tvo.htc.view.main.news.detail.NewsDetailFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class NewsMarketFragment extends BaseFragment<NewsMarketContract.Presenter> implements NewsMarketContract.View, BaseAdapter.OnItemClickListener, BaseLoadMoreAdapter.OnLoadMoreListener, CpnCustomRecyclerView.CustomRecyclerViewListener {
    private NewsAdapter adapter;
    @BindView(R.id.rvNews)
    CpnCustomRecyclerView rvNews;
    private List<News> mList;
    private boolean isVisibleToUser;

    public static NewsMarketFragment newInstance() {

        Bundle args = new Bundle();

        NewsMarketFragment fragment = new NewsMarketFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected NewsMarketContract.Presenter createPresenterInstance() {
        return new NewsMarketPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_market;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        rvNews.setCustomRecyclerViewListener(this);

        if (isVisibleToUser && mList == null) {
            getPresenter().loadData(AppConstants.START_OFFSET, false);
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && mList == null && getPresenter() != null) {
            getPresenter().loadData(AppConstants.START_OFFSET, false);
        }
    }

    @Override
    public void onItemClick(BaseAdapter adapter, View view, int position) {
        FragmentUtil.startFragment(getContext(), NewsDetailFragment.newInstance(mList.get(position)), "NewsDetailFragment");
    }

    @Override
    public void getData(List<News> items) {
        mList = items;
        setAdapter(items, true);
    }

    @Override
    public void updateData(List<News> items) {
        setAdapter(items, false);
    }

    @Override
    public void getDataFailed() {
        rvNews.setRefreshing(false);
    }

    @Override
    public void onLoadMore(int skipCount) {
        getPresenter().loadData(skipCount, false);
    }

    @Override
    public void onRefresh() {
        getPresenter().loadData(AppConstants.START_OFFSET, true);
    }


    private void setAdapter(List<News> items, boolean isUpdate) {
        if (adapter == null) {
            adapter = new NewsAdapter(getContext(), rvNews.getRecyclerView(), mList, AppConstants.PAGE_LIMIT);
            adapter.setOnItemClickListener(this);
            adapter.setOnLoadMoreListener(this);
            rvNews.setAdapter(adapter);
        } else {
            if(isUpdate) {
                adapter.updateData(items);
            } else {
                adapter.addMoreData(items);
            }
        }
        rvNews.setRefreshing(false);
    }
}
