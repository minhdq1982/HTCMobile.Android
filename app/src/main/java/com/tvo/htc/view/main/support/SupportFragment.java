package com.tvo.htc.view.main.support;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.PermissionUtil;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.main.login.request.LoginRequestFragment;
import com.tvo.htc.view.main.support.comment_complaint.CommentComplaintFragment;
import com.tvo.htc.view.main.support.findlocation.FindLocationFragment;
import com.tvo.htc.view.main.support.guide.GuideFragment;

import butterknife.OnClick;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class SupportFragment extends BaseFragment<SupportContract.Presenter> implements SupportContract.View {
    public static final int REQ_CALL = 0;
    public static final int REQ_CALL_COMPLAIN = 1;

    private String mHotline, mHotlineComplain;

    public static SupportFragment newInstance() {

        Bundle args = new Bundle();

        SupportFragment fragment = new SupportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().loadHotline();
    }

    @Override
    protected SupportContract.Presenter createPresenterInstance() {
        return new SupportPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_support;
    }

    @OnClick({R.id.btnFindLocation, R.id.btnBookGuide, R.id.btnSentSupport, R.id.btnCallHotline})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnFindLocation:
                FragmentUtil.startFragment(getActivity(), FindLocationFragment.newInstance(), null);
                break;
            case R.id.btnBookGuide:
                FragmentUtil.startFragment(getContext(), GuideFragment.newInstance(), null);
                break;
            case R.id.btnSentSupport:
                SentSupportDialog supportDialog = new SentSupportDialog();
                supportDialog.addListener(new SentSupportDialog.Callback() {
                    @Override
                    public void onSentFormClicked() {
                        if (!hasLogin()) {
                            FragmentUtil.startFragment(getActivity(), LoginRequestFragment.newInstance(), null);
                        } else {
                            FragmentUtil.startFragment(getActivity(), CommentComplaintFragment.newInstance(), null);
                        }
                    }

                    @Override
                    public void onCallHotLineClicked() {
                        if (!PermissionUtil.checkAndRequestPermission(SupportFragment.this, Manifest.permission.CALL_PHONE, REQ_CALL_COMPLAIN)) {
                            Utils.callHotLine(getActivity(), mHotlineComplain);
                        }
                    }
                });
                supportDialog.show(getChildFragmentManager(), SentSupportDialog.class.getSimpleName());
                break;
            case R.id.btnCallHotline:
                if (!PermissionUtil.checkAndRequestPermission(this, Manifest.permission.CALL_PHONE, REQ_CALL)) {
                    Utils.callHotLine(getActivity(), mHotline);
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CALL && grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
            Utils.callHotLine(getActivity(), mHotline);
        }
        if (requestCode == REQ_CALL_COMPLAIN && grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
            Utils.callHotLine(getActivity(), mHotlineComplain);
        }
    }

    @Override
    public void loadHotline(String hotline, String hotline_complain) {
        if (hotline != null) {
            mHotline = hotline.replace(".", "");
        }

        if (hotline_complain != null) {
            mHotlineComplain = hotline_complain.replace(".", "");
        }
    }
}
