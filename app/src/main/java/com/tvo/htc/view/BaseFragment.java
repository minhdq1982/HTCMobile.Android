package com.tvo.htc.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.lib.dialog.SimpleDialog;
import com.android.lib.dialog.WaitDialogFragment;
import com.android.lib.model.response.LoginResponse;
import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.ScreenUtils;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.component.CpnNavigationBar;
import com.tvo.htc.view.component.CpnSpinner;
import com.tvo.htc.view.main.login.LoginFragment;
import com.tvo.htc.view.main.notification.NotificationFragment;
import com.tvo.htc.view.main.profile.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThinhNH on 11/23/2016.
 */
public abstract class BaseFragment<P extends BaseContract.Presenter> extends Fragment implements
        BaseContract.View, CpnNavigationBar.NavigationListener {

    protected P presenter;

    private WaitDialogFragment mWaitDialog;

    @Nullable
    @BindView(R.id.navigationBar)
    protected CpnNavigationBar mNavigationBar;

    protected LoginResponse mLoginResponse;

    protected abstract P createPresenterInstance();

    protected abstract int getLayoutId();

    public BaseFragment() {
    }

    public P getPresenter() {
        return presenter;
    }

    @Override
    public Animation onCreateAnimation(int transit, final boolean enter, int nextAnim) {
        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);
        if (animation == null && nextAnim != 0) {
            animation = AnimationUtils.loadAnimation(getActivity(), nextAnim);
        }
        if (animation != null) {
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    BaseFragment.this.onAnimationEnd(enter);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
        return animation;
    }

    @Override
    final public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = createPresenterInstance();
        if (presenter != null) {
            //noinspection unchecked
            presenter.attachView(this);
        }

        mLoginResponse = LocalDataManager.getInstance(getActivity()).getLoginResponse();

//        Logger.d("savedInstanceState: " + savedInstanceState);
//        if (savedInstanceState != null) {
//            getFragmentManager().beginTransaction().remove(this).commit();
//            return;
//        }


//        getActivity().getWindow().setBackgroundDrawableResource(R.color.bt_gray);
        onCreateBaseFragment(savedInstanceState);
    }

    @Nullable
    @Override
    final public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        if (container.getId() == R.id.mainContainer
                || container.getId() == R.id.mainContainerNoTabbar) {
            FrameLayout rootView = new FrameLayout(getActivity());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            rootView.setLayoutParams(layoutParams);

            ImageView ivBackground = new ImageView(getActivity());
            ivBackground.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ivBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
            rootView.addView(ivBackground);
            rootView.addView(view);

            if (!isBackgroundTransparent()) {
                if (isShowGridBackground()) {
                    ivBackground.setImageResource(R.drawable.bg_layer_list);
//                    rootView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.bg_layer_list));
                } else {
                    rootView.setBackgroundResource(R.color.colorPrimary);
                }
            }
            if (!isFullScreen()) {
                int statusHeight = ScreenUtils.getStatusBarHeight(getActivity());

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                params.topMargin = statusHeight;
                view.setLayoutParams(params);
//                rootView.setPadding(0, statusHeight, 0, 0);
            }

            view = rootView;
        }
        //todo handle hide keyboard when click outsize edittext
        hideKeyboardWhenClickOutSizeEditText(view);
        return view;
    }

    @Override
    final public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.d("this: " + this);

//        if (savedInstanceState != null) {
//            return;
//        }

        if (mNavigationBar != null) {
            mNavigationBar.setNavigationListener(this);
        }

        view.setOnTouchListener((v, event) -> true);
        onViewCreatedBaseFragment(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void onCreateBaseFragment(Bundle savedInstanceState) {

    }

    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {

    }

    protected boolean isShowGridBackground() {
        return false;
    }

    protected boolean isBackgroundTransparent() {
        return false;
    }

    protected boolean isFullScreen() {
        return false;
    }

    /**********************************************************************************************
     * OVERRIDE IF NEED
     *********************************************************************************************/
    public void onResumeFragment() {
        Logger.d("");
    }

    @Override
    public void onNavigationBtClick(CpnNavigationBar.NavigationType type) {
        Logger.d("type: " + type);
        switch (type) {
            case BT_BACK:
                FragmentUtil.removeFragment(getActivity());
                break;
            case BT_PROFILE:
                if (mLoginResponse != null && mLoginResponse.getData() != null) {
                    FragmentUtil.startFragment(getActivity(), ProfileFragment.newInstance(), null);
                } else {
                    FragmentUtil.startFragmentNoTabbar(getActivity(), LoginFragment.newInstance(), null);
                }
                break;
            case BT_NOTIFICATION:
                FragmentUtil.startFragment(getActivity(), NotificationFragment.newInstance(), null);
                break;
            default:
                break;
        }
    }

    public void onAnimationEnd(boolean enter) {
        Logger.d("");
    }

//    public void onBackPressed() {
//        FragmentUtil.removeFragment(getActivity());
//    }

    public void setTitle(String title) {
        if (mNavigationBar != null) {
            mNavigationBar.setTitle(title);
        }
    }

    /**
     * set orientation
     *
     * @param requestedOrientation
     */
    protected void setRequestedOrientation(int requestedOrientation) {
        if (getActivity() == null) {
            return;
        }
        getActivity().setRequestedOrientation(requestedOrientation);
    }

    public boolean hasLogin() {
        return Utils.hasLogin(getActivity(), mLoginResponse);
    }

    /**********************************************************************************************
     * CONTROL WAIT DIALOG
     *********************************************************************************************/
    protected void showWaitDialog() {
        Logger.v("");
        if (getActivity() == null) {
            return;
        }
        mWaitDialog = WaitDialogFragment.newInstance(false);
        if (mWaitDialog.getDialog() == null || !mWaitDialog.isAdded() || (mWaitDialog.getDialog()
                != null && !mWaitDialog.getDialog().isShowing())) {
            Logger.v("showWaitDialog");
            mWaitDialog.show(getActivity().getFragmentManager(), null);
        }
    }

    protected void dismissWaitDialog() {
        Logger.v("");
        if (getActivity() == null) {
            return;
        }

        if (mWaitDialog != null) {
            mWaitDialog.dismiss();
        }
    }

    /**********************************************************************************************
     * Show message
     *********************************************************************************************/
    protected void showMessage(String message) {
        Utils.showMessage(getActivity(), message);
    }

    protected void showMessage(String message, SimpleDialog.Callbacks callbacks) {
        Utils.showMessage(getActivity(), message, callbacks);
    }

    /**********************************************************************************************
     * Hide keyboard when click out size edittext
     *********************************************************************************************/
    @SuppressLint("ClickableViewAccessibility")
    protected void hideKeyboardWhenClickOutSizeEditText(View view) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                if (innerView instanceof ViewGroup) {
                    hideKeyboardWhenClickOutSizeEditText(innerView);
                } else if (!(innerView instanceof EditText) && !(innerView instanceof CpnSpinner)) {
                    innerView.setOnTouchListener((v, event) -> {
                        FragmentUtil.hideKeyboard(getContext());
                        return false;
                    });
                }
            }
        }
    }
}
