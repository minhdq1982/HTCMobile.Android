package com.tvo.htc.view.main.survey;

import android.view.View;

import com.tvo.htc.R;
import com.tvo.htc.view.dialog.BaseDialogFragment;

import butterknife.OnClick;

/**
 * Create by Ngocji on 10/26/2018
 **/


public class SurveySuccessDialog extends BaseDialogFragment {

   public interface Callback {
        void onConfirmClicked();
    }

    Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void init(View view) {
    }

    @Override
    protected int getLayoutID() {
        return R.layout.dialog_survey_success;
    }

    @OnClick(R.id.cpnbOk)
    public void onViewClicked() {
        dismiss();
        if (callback != null) callback.onConfirmClicked();
    }
}
