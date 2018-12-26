package com.tvo.htc.view.main.login.request;

import android.os.Bundle;

import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.main.login.LoginFragment;

import butterknife.OnClick;

public class LoginRequestFragment extends BaseFragment<LoginRequestContract.Presenter> implements LoginRequestContract.View {

    public static LoginRequestFragment newInstance() {
        Bundle args = new Bundle();

        LoginRequestFragment fragment = new LoginRequestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected LoginRequestContract.Presenter createPresenterInstance() {
        return new LoginRequestPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_request;
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        FragmentUtil.startFragment(getContext(), LoginFragment.newInstance(), null);
    }
}
