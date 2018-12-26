package com.tvo.htc.view.main.login.confirm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.switch_page.SwitchPageImpl;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnNavigationBar;
import com.tvo.htc.view.component.CpnPinEditText;
import com.tvo.htc.view.component.CpnTab;
import com.tvo.htc.view.main.MainActivity;
import com.tvo.htc.view.main.profile.edit.ProfileEditFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class LoginConfirmFragment extends BaseFragment<LoginConfirmContract.Presenter> implements LoginConfirmContract.View {

    private static String mPhoneNumber;

    @BindView(R.id.editConfirmCode)
    CpnPinEditText editConfirmCode;
    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @BindView(R.id.btnResentCode)
    LinearLayout btnResentCode;

    public static LoginConfirmFragment newInstance(String phone) {

        Bundle args = new Bundle();

        LoginConfirmFragment fragment = new LoginConfirmFragment();
        fragment.setArguments(args);

        mPhoneNumber = phone;
        Logger.d(phone);
        return fragment;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().getCountDown();
        editConfirmCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 4) {
                    String inputCode = s.toString();
                    getPresenter().handleConfirmCode(mPhoneNumber, inputCode);
                }
            }
        });
    }

    @Override
    protected LoginConfirmContract.Presenter createPresenterInstance() {
        return new LoginConfirmPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_confirm;
    }

    @Override
    protected boolean isShowGridBackground() {
        return true;
    }

    @Override
    public void displayErrorConfirmCode(String message) {
        showMessage(message);
    }

    @Override
    public void displaySuccessConfirmCode(boolean isProfileFull) {
        if (!SwitchPageImpl.getInstance().handleSwitchLoginSuccess(getActivity())) {
            FragmentUtil.clearAllBackStack(getActivity());
            if (isProfileFull) {
                ((MainActivity) getActivity()).onTabMenuSelected(CpnTab.TabType.HOME);
            } else {
                FragmentUtil.startFragmentNoTabbar(getActivity(), ProfileEditFragment.newInstanceConfirmLogin(), null);
            }
        }
    }

    @Override
    public void loginSuccess(String phone) {
        String message = getString(R.string.login_resent_code);
        showMessage(message);
        editConfirmCode.setMaxLength(4);
        mPhoneNumber = phone;
    }

    @Override
    public void onTimeOut(String message) {
        tvMessage.setText(message);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTimeCount(String message) {
        tvMessage.setText(message);
    }

    @Override
    public void onNavigationBtClick(CpnNavigationBar.NavigationType type) {
        super.onNavigationBtClick(type);
        switch (type) {
            case BT_BACK:
                getPresenter().stopCountDown();
                break;
        }
    }

    @OnClick({/*R.id.cpnbConfirm, */R.id.btnResentCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*case R.id.cpnbConfirm:
                String inputCode = editConfirmCode.getText().questionToString();
                getPresenter().handleConfirmCode(mPhoneNumber, inputCode);
                break;*/
            case R.id.btnResentCode:
                getPresenter().getCountDown();
                getPresenter().resentCode(mPhoneNumber);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().stopCountDown();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().stopCountDown();
    }
}
