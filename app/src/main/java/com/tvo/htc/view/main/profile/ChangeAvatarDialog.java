package com.tvo.htc.view.main.profile;

import android.view.View;

import com.tvo.htc.R;
import com.tvo.htc.view.dialog.BaseDialogFragment;

import butterknife.OnClick;

/**
 * Create by Ngocji on 10/30/2018
 **/


public class ChangeAvatarDialog extends BaseDialogFragment {
    Callback callback;

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void init(View view) { }

    @Override
    protected int getLayoutID() {
        return R.layout.dialog_change_avatar;
    }

    @OnClick({R.id.llPickGallery, R.id.llPickCamera})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llPickGallery:
                dismiss();
                if (callback != null) callback.onPickGalleryClicked();
                break;
            case R.id.llPickCamera:
                dismiss();
                if (callback != null) callback.onPickCameraClicked();
                break;
        }
    }

    @Override
    public boolean isCancel() {
        return true;
    }

    public interface Callback {
        void onPickGalleryClicked();

        void onPickCameraClicked();
    }
}
