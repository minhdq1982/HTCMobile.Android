package com.tvo.htc.view.main.notification.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.lib.model.response.NotificationResponse;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseFragment;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class NotificationDetailFragment extends BaseFragment<NotificationDetailContract.Presenter> implements NotificationDetailContract.View {
    public static final String EXTRAS_ID = "extrasId";
    public static final String EXTRAS_TITLE = "extrasTitle";
    public static final String EXTRAS_CONTENT = "extrasContent";


    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.webView)
    WebView webView;

    public static NotificationDetailFragment newInstance(long id, String title, String content) {
        Bundle args = new Bundle();
        args.putLong(EXTRAS_ID, id);
        args.putString(EXTRAS_TITLE, title);
        args.putString(EXTRAS_CONTENT, content);
        NotificationDetailFragment fragment = new NotificationDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected NotificationDetailContract.Presenter createPresenterInstance() {
        return new NotificationDetailPresenter(getContext());
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setInitialScale(100);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setDefaultFontSize((int) getResources().getDimension(R.dimen.text_size));
        getPresenter().loadData(getArguments());
    }


    @Override
    public void displayData(String title, String content) {
        txtTitle.setText(title);
        webView.loadData(content, "text/html; charset=utf-8", "UTF-8");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notification_detail;
    }

}
