package com.tvo.htc.view.main.support;

import android.view.View;

import com.tvo.htc.R;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.dialog.BaseDialogFragment;

import butterknife.OnClick;

import static com.tvo.htc.util.AppConstants.HOT_LINE;

/**
 * Create by Ngocji on 10/22/2018
 **/


public class SentSupportDialog extends BaseDialogFragment {
    private Callback callback;

    @Override
    protected void init(View view) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.dialog_sent_support;
    }

    public void addListener(Callback callback) {
        this.callback = callback;
    }

    @OnClick({R.id.btnCallHotline, R.id.btnSentForm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCallHotline:
                dismiss();
                if (callback!=null) callback.onCallHotLineClicked();
                break;
            case R.id.btnSentForm:
                dismiss();
                if (callback != null) callback.onSentFormClicked();
                break;
        }
    }

    @Override
    public boolean isCancel() {
        return true;
    }

    public interface Callback {
        void onSentFormClicked();

        void onCallHotLineClicked();

    }
}
