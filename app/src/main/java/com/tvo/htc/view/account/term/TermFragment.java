package com.tvo.htc.view.account.term;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.android.lib.model.response.TermResponse;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.account.AccountActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class TermFragment extends BaseFragment<TermContract.Presenter> implements TermContract.View {

    @BindView(R.id.webView)
    WebView webView;

    private String mContent;

    public static TermFragment newInstance() {

        Bundle args = new Bundle();

        TermFragment fragment = new TermFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected TermContract.Presenter createPresenterInstance() {
        return new TermPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_term;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);

        webView.setLongClickable(false);// Set a long click listener for web view
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // For final release of your app, comment the toast notification
                return true;
            }
        });
        // Disable haptic feedback for web view
        webView.setHapticFeedbackEnabled(false);
        webView.setBackgroundColor(0);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        //String content = getString(R.string.term_content);
        getPresenter().loadData();
    }

    @Override
    protected boolean isShowGridBackground() {
        return false;
    }

    @OnClick({R.id.cpnbAccept})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.cpnbAccept:
                getPresenter().saveAccepted();
                ((AccountActivity) getActivity()).startMainActivity();
                break;
            default:
                break;
        }
    }

    @Override
    public void getDataSuccess(TermResponse response) {
        if (response == null || response.getData() == null || response.getData().getContent() == null) {
            return;
        }
        mContent = response.getData().getContent();
        mContent = "<div style=\"color:#FFFFFF ;  \">" + mContent + "</div>";
        webView.loadDataWithBaseURL("", mContent,
                "text/html; charset=utf-8", "utf-8", "");
    }
}
