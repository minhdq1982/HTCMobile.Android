package com.tvo.htc.view.main.news.feed;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.android.lib.model.response.NewsFeedResponse;
import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.BaseLoadMoreAdapter;
import com.tvo.htc.view.component.CpnCustomRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class NewsFeedFragment extends BaseFragment<NewsFeedContract.Presenter> implements NewsFeedContract.View,
        BaseAdapter.OnItemClickListener, CpnCustomRecyclerView.CustomRecyclerViewListener, BaseLoadMoreAdapter.OnLoadMoreListener {
    private NewsFeedAdapter adapter;
    @BindView(R.id.rvNews)
    CpnCustomRecyclerView rvNews;
    private List<NewsFeedResponse.Data> mList;
    private boolean isVisibleToUser;

    public static NewsFeedFragment newInstance() {

        Bundle args = new Bundle();

        NewsFeedFragment fragment = new NewsFeedFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected NewsFeedContract.Presenter createPresenterInstance() {
        NewsFeedPresenter newsFeedPresenter = new NewsFeedPresenter(getContext());

        return newsFeedPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_feed;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);

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
        if (mList.get(position).getType() == 4) {
            String id = mList.get(position).getId();
            if (id != null) {
                try {
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(getContext(), id);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                } catch (Exception ignored) {

                }
            }
        } else {
            String link = mList.get(position).getLink();
            watchYoutubeVideo(getContext(), link);
        }
    }

    @Override
    public void addList(List<NewsFeedResponse.Data> list, boolean isAllowLoadMore) {
        this.mList = list;
        setAdapter(list, isAllowLoadMore, true);
    }

    @Override
    public void onLoadMore(int skipCount) {
        getPresenter().loadData(skipCount, false);
    }

    @Override
    public void getLoadFail() {
        rvNews.setRefreshing(false);
    }

    @Override
    public void updateList(List<NewsFeedResponse.Data> mList, boolean isAllowLoadMore) {
        setAdapter(mList, isAllowLoadMore, false);
    }

    public void watchYoutubeVideo(Context context, String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(/*"user:" + */id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    public String getFacebookPageURL(Context context, String id) {
        PackageManager packageManager = context.getPackageManager();
        id = "https://www.facebook.com/" + id;
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            ApplicationInfo ai = getActivity().getPackageManager().getApplicationInfo("com.facebook.katana", 0);
            if (ai.enabled) {
                if (versionCode >= 3002850) { //newer versions of fb app
                    return "fb://facewebmodal/f?href=" + id;
                } else { //older versions of fb app
                    return id;
                }
            } else {
                return id;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return id; //normal web url
        } catch (ActivityNotFoundException e) {
            return id; //normal web url
        }
    }

    @Override
    public void onRefresh() {
        getPresenter().loadData(AppConstants.START_OFFSET, true);
    }


    private void setAdapter(List<NewsFeedResponse.Data> items, boolean isAllowLoadMore, boolean isUpdate) {
        Logger.d(items.size() + "items");
        if (adapter == null) {
            rvNews.setRefreshing(false);
            adapter = new NewsFeedAdapter(getActivity(), rvNews.getRecyclerView(), mList, AppConstants.PAGE_LIMIT * 2);
            rvNews.setAdapter(adapter);
            rvNews.setCustomRecyclerViewListener(this);
            adapter.setOnItemClickListener(this);
            adapter.setOnLoadMoreListener(this);
        } else {
            if (isUpdate) {
                adapter.updateData(items);
            } else {
                adapter.getDisplayItems().clear();
                adapter.addMoreData(items);
            }
        }
        adapter.setAllowLoadMore(isAllowLoadMore);
        rvNews.setRefreshing(false);
    }
}
