package com.tvo.htc.view.dialog;

import android.view.View;
import android.widget.TextView;

import com.tvo.htc.R;
import com.tvo.htc.view.component.CpnButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by Ngocji on 10/18/2018
 **/


public class ConfirmDialog extends BaseDialogFragment {
    public static final String TAG = ConfirmDialog.class.getSimpleName();
    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @BindView(R.id.cpnbCancel)
    CpnButton cpnbCancel;
    @BindView(R.id.cpnbOk)
    CpnButton cpnbOk;

    private Callback callback;
    private Builder builder;

    public interface Callback {
        void onConfirmClicked();

        void onCancelClicked();
    }

    @Override
    public void init(View view) {
        //check nullable text in builder
        checkNullableBuilder();
        tvMessage.setText(builder.txtMessage);
        cpnbOk.setText(builder.txtButtonConfirm);
        cpnbCancel.setText(builder.txtButtonCancel);
    }

    @Override
    public int getLayoutID() {
        return R.layout.dialog_confirm;
    }

    @OnClick({R.id.cpnbCancel, R.id.cpnbOk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cpnbOk:
                dismiss();
                if (callback != null) callback.onConfirmClicked();
                break;
            case R.id.cpnbCancel:
                dismiss();
                if (callback != null) callback.onCancelClicked();
                break;
        }
    }

    public void setData(Builder builder) {
        this.builder = builder;
    }

    public void addListener(Callback callback) {
        this.callback = callback;
    }

    private void checkNullableBuilder() {
        if (builder.txtMessage == null) {
            builder.setTextMessage(getString(R.string.dialog_confirm_txt_message));
        }
        if (builder.txtButtonCancel == null) {
            builder.setTextButtonCancel(getString(R.string.dialog_confirm_txt_button_cancel));
        }
        if (builder.txtButtonConfirm == null) {
            builder.setTextButtonConfirm(getString(R.string.dialog_confirm_txt_button_ok));
        }
    }

    public static class Builder {
        private String txtMessage;
        private String txtButtonConfirm;
        private String txtButtonCancel;

        public Builder setTextMessage(String textMessage) {
            this.txtMessage = textMessage;
            return this;
        }

        public Builder setTextButtonConfirm(String textButtonConfirm) {
            this.txtButtonConfirm = textButtonConfirm;
            return this;
        }

        public Builder setTextButtonCancel(String textButtonCancel) {
            this.txtButtonCancel = textButtonCancel;
            return this;
        }

        public ConfirmDialog create() {
            ConfirmDialog confirmDialog = new ConfirmDialog();
            confirmDialog.setData(this);
            return confirmDialog;
        }
    }
}
