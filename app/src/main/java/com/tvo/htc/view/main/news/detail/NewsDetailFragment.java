package com.tvo.htc.view.main.news.detail;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.News;
import com.android.lib.model.response.GroupNews;
import com.android.lib.util.LibUtils;
import com.tvo.htc.R;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseFragment;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class NewsDetailFragment extends BaseFragment<NewsDetailContract.Presenter> implements NewsDetailContract.View {

    @BindView(R.id.imPreview)
    ImageView imPreview;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvTitleSecond)
    TextView tvTitleSecond;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.flProgress)
    FrameLayout flProgress;
    private GroupNews mGroupNews;
    private News mNews;

    public static NewsDetailFragment newInstance(GroupNews groupNews, News news) {
        Bundle args = new Bundle();

        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.mGroupNews = groupNews;
        fragment.mNews = news;
        fragment.setArguments(args);
        return fragment;
    }

    public static NewsDetailFragment newInstance(News news) {
        Bundle args = new Bundle();

        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.mNews = news;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected NewsDetailContract.Presenter createPresenterInstance() {
        return new NewsDetailPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_detail;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);

        if (mGroupNews != null) {
            setTitle(mGroupNews.getName());
        }


        webView.getSettings().setJavaScriptEnabled(true);
        webView.setInitialScale(100);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setDefaultFontSize((int) getResources().getDimension(R.dimen.text_size));
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 0 && flProgress.getVisibility() == View.GONE) {
                    flProgress.setVisibility(View.VISIBLE);
                }
                if (newProgress >= AppConstants.WEB_VIEW_LOADING_FINISH_PERCENT) {
                    flProgress.setVisibility(View.GONE);
                }
            }
        });

        getNewsDetail(mNews);

        if (mNews != null) {
            getPresenter().getNewsDetail(mNews.getId());
        }
    }

    @Override
    public void getNewsDetail(News news) {
        mNews = news;

        if (mNews == null) {
            return;
        }

        ImageLoader.loadImage(getActivity(), imPreview, R.drawable.img_no_image,
                Utils.getImagePath(mNews.getImage()));
        tvTitleSecond.setText(mNews.getTitle());
        tvTime.setText(LibUtils.getFormatTitleDate(mNews.getCreationTime()));
        webView.loadData(mNews.getFullContent(), "text/html; charset=utf-8", "UTF-8");
    }
}
