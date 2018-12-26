package com.tvo.htc.view.main.news;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.SimpleCustomTabScrollAdapter;
import com.tvo.htc.view.component.CpnCustomTabView;
import com.tvo.htc.view.main.news.feed.NewsFeedFragment;
import com.tvo.htc.view.main.news.group.NewsGroupFragment;
import com.tvo.htc.view.main.news.hyundai.NewsHyundaiFragment;
import com.tvo.htc.view.main.news.market.NewsMarketFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public class NewsFragment extends BaseFragment<NewsContract.Presenter> implements
        NewsContract.View, CpnCustomTabView.TabListener {
    @BindView(R.id.vpNews)
    ViewPager vpNews;

    @BindView(R.id.cpnNews)
    CpnCustomTabView cpnNews;

    @BindView(R.id.rvNews)
    RecyclerView rvNews;

    private List<String> mListTitle;
    private SimpleCustomTabScrollAdapter mSimpleCustomTabAdapter;

    public static NewsFragment newInstance() {

        Bundle args = new Bundle();

        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected NewsContract.Presenter createPresenterInstance() {
        return new NewsPresenter(getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().loadDataNews();
    }

    @Override
    public void onTabSelected(String tabTitle) {
        Logger.d("tabTitle: " + tabTitle);
        for (int i = 0; i < mListTitle.size(); i++) {
            if (mListTitle.get(i).equals(tabTitle)) {
                vpNews.setCurrentItem(i);
            }
        }
    }


    @Override
    public void displayDataNews(List<String> listTitle, boolean hasNewsfeed) {
        mListTitle = listTitle;
        mSimpleCustomTabAdapter = new SimpleCustomTabScrollAdapter(getContext(), listTitle);
        if (hasNewsfeed) {
            rvNews.setVisibility(View.VISIBLE);
            cpnNews.setVisibility(View.GONE);
            rvNews.setAdapter(mSimpleCustomTabAdapter);

            mSimpleCustomTabAdapter.setOnItemClickListener((adapter, view, position) -> {
                vpNews.setCurrentItem(position);
            });
        } else {
            rvNews.setVisibility(View.GONE);
            cpnNews.setVisibility(View.VISIBLE);

            cpnNews.setContent(listTitle);
            cpnNews.setTabListener(this);
        }

        NewsPagerAdapter newsPagerAdapter = new NewsPagerAdapter(getActivity().getSupportFragmentManager(), listTitle);
        newsPagerAdapter.addFragment(NewsHyundaiFragment.newInstance());
        newsPagerAdapter.addFragment(NewsMarketFragment.newInstance());
        if (listTitle.size() == 4) {
            newsPagerAdapter.addFragment(NewsFeedFragment.newInstance());
            newsPagerAdapter.addFragment(NewsGroupFragment.newInstance());
        }

        vpNews.setOffscreenPageLimit(5);
        vpNews.setAdapter(newsPagerAdapter);
        vpNews.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                cpnNews.setTabSelected(listTitle.get(i));
                mSimpleCustomTabAdapter.setSelectedPosition(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
