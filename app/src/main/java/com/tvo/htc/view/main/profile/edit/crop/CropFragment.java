package com.tvo.htc.view.main.profile.edit.crop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.lib.util.Logger;
import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;
import com.isseiaoki.simplecropview.callback.LoadCallback;
import com.tvo.htc.R;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.RealFilePath;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseFragment;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class CropFragment extends BaseFragment<CropContract.Presenter> implements CropContract.View {
    private static final String EXTRAS_INPUT = "extrasInput";
    @BindView(R.id.cropImageView)
    CropImageView cropImageView;

    private String mPathInput = "";
    private CallBack mCallBack;

    public static CropFragment newInstance(String pathInput, CallBack callBack) {

        Bundle args = new Bundle();
        args.putString(EXTRAS_INPUT, pathInput);
        CropFragment fragment = new CropFragment();
        fragment.mCallBack = callBack;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected CropContract.Presenter createPresenterInstance() {
        return new CropPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_crop;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        //Config crop imageview
        cropImageView.setOutputMaxSize(AppConstants.OUT_PUT_CROP, AppConstants.OUT_PUT_CROP);
        cropImageView.setOutputWidth(AppConstants.OUT_PUT_CROP / 4);
        cropImageView.setMinFrameSizeInPx(AppConstants.OUT_PUT_CROP);
        cropImageView.setCompressFormat(Bitmap.CompressFormat.JPEG);
        cropImageView.setCompressQuality(80);

        mPathInput = getArguments().getString(EXTRAS_INPUT);
        cropImageView.load(Uri.parse(mPathInput))
                .initialFrameScale(0.85f)
                .execute(null);
    }

    @Override
    protected boolean isShowGridBackground() {
        return false;
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @OnClick({R.id.cpnbCrop, R.id.cpnbCancel})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.cpnbCrop:
                cropImageView.cropAsync(Uri.parse(mPathInput), new CropCallback() {
                    @Override
                    public void onSuccess(Bitmap cropped) {
                        File file = RealFilePath.getRealFileFromBitmap(getContext(), cropped);
                        if (file != null) {
                            if (mCallBack != null) {
                                mCallBack.croped(file.getPath());
                            }
                            FragmentUtil.removeFragment(getContext());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), getString(R.string.crop_error), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.cpnbCancel:
                FragmentUtil.removeFragment(getContext());
                break;
        }
    }

    public interface CallBack {
        void croped(String path);
    }
}
