package com.tvo.htc.view.main.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.switch_page.SwitchPageImpl;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnNavigationBar;
import com.tvo.htc.view.main.login.confirm.LoginConfirmFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.etPhone)
    EditText etPhone;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected LoginContract.Presenter createPresenterInstance() {
        return new LoginPresenter(getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onNavigationBtClick(CpnNavigationBar.NavigationType type) {
        switch (type) {
            case BT_BACK:
                SwitchPageImpl.getInstance().handleBackPressed(getContext());
                break;
            default:
                super.onNavigationBtClick(type);
        }
    }

    @OnClick({R.id.cpnbLogin})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.cpnbLogin:
                login();
                break;
            default:
                break;
        }
    }

    @Override
    protected boolean isShowGridBackground() {
        return true;
    }

    @Override
    public void loginSuccess(String phone) {
        FragmentUtil.startFragmentNoTabbar(getActivity(), LoginConfirmFragment.newInstance(phone), null);
    }

    @Override
    public void loginFailed(String message) {
        showMessage(message);
    }

    @Override
    public void showMessageError(String message) {
        showMessage(message);
    }

    private void login() {
        if (!isValidField()) {
            return;
        }

        getPresenter().login(etPhone.getText().toString().trim());
    }

    private boolean isValidField() {
        boolean isValidField = true;
        String message = "";
        if (etPhone.getText().toString().isEmpty()) {
            message = getString(R.string.login_error_input_phone);
            isValidField = false;
        }

        if (!isValidField) {
            showMessage(message);
        }

        return isValidField;
    }

}
