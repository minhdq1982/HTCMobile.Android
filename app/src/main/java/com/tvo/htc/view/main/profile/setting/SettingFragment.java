package com.tvo.htc.view.main.profile.setting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckBox;

import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.main.home.HomeFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends BaseFragment<SettingContract.Presenter> implements
        SettingContract.View {

    @BindView(R.id.btnNoti)
    CheckBox btnNotification;
    @BindView(R.id.btnNews)
    CheckBox btnNewsfeed;

    public static SettingFragment newInstance() {

        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected SettingContract.Presenter createPresenterInstance() {
        return new SettingPresenter(getActivity());
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().loadSetting(mLoginResponse);
        btnNotification.setOnCheckedChangeListener((buttonView, isChecked) ->
                getPresenter().changeSetting(isChecked, true));
        btnNewsfeed.setOnCheckedChangeListener((buttonView, isChecked) ->
                getPresenter().changeSetting(isChecked, false));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @OnClick(R.id.btnLogout)
    public void onViewClicked() {
        getPresenter().logout();
        FragmentUtil.clearAllBackStack(getContext());
        LocalDataManager.getInstance(getContext()).logout();
        FragmentUtil.startFragment(getContext(), HomeFragment.newInstance(), null);
    }

    @Override
    public void displaySetting(boolean notification, boolean newsfeed) {
        btnNotification.setChecked(notification);
        btnNewsfeed.setChecked(newsfeed);
    }
}
