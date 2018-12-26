package com.tvo.htc.view.main.support.guide.technical_guide.detail;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.response.TechnicalGuideResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.view.BaseFragment;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class TechnicalGuideDetailFragment extends BaseFragment<TechnicalGuideDetailContract.Presenter> implements TechnicalGuideDetailContract.View {
    private static final String KEY_DATA = "KEY_DATA";
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

    public static TechnicalGuideDetailFragment newInstance(TechnicalGuideResponse.Item data) {

        Bundle args = new Bundle();
        args.putParcelable(KEY_DATA, data);
        TechnicalGuideDetailFragment fragment = new TechnicalGuideDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected TechnicalGuideDetailContract.Presenter createPresenterInstance() {
        return new TechnicalGuideDetailPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_support_guide_technical_detail;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setInitialScale(100);
        webView.getSettings().setDefaultFontSize((int) getResources().getDimension(R.dimen.text_size));
        webView.getSettings().setDefaultTextEncodingName("utf-8");
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
        getPresenter().loadData(getArguments().getParcelable(KEY_DATA));
    }


    @Override
    public void showInfo(String imgLink, String title, String time) {
        ImageLoader.loadImage(getContext(), imPreview, R.drawable.img_no_image, imgLink);
        tvTitleSecond.setText(title);
        tvTime.setText(time);
    }

    @Override
    public void showData(String content) {
        flProgress.setVisibility(View.GONE);
        webView.loadData(content, "text/html; charset=utf-8", "UTF-8");
    }
}
