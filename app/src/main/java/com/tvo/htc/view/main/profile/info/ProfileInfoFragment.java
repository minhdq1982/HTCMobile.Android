package com.tvo.htc.view.main.profile.info;

import android.os.Bundle;
import android.view.View;

import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnEditText;
import com.tvo.htc.view.main.profile.edit.ProfileEditFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ProfileInfoFragment extends BaseFragment<ProfileInfoContract.Presenter> implements ProfileInfoContract.View {

    @BindView(R.id.editPhone)
    CpnEditText editPhone;
    @BindView(R.id.editBirthDay)
    CpnEditText editBirthDay;
    @BindView(R.id.editIdentifyId)
    CpnEditText editIdentifyId;
    @BindView(R.id.editEmail)
    CpnEditText editEmail;
    @BindView(R.id.editAddress)
    CpnEditText editAddress;

    public static ProfileInfoFragment newInstance() {

        Bundle args = new Bundle();

        ProfileInfoFragment fragment = new ProfileInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected ProfileInfoContract.Presenter createPresenterInstance() {
        return new ProfileInfoPresenter(getContext());
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().loadInfo(mLoginResponse);
    }

    @Override
    protected boolean isBackgroundTransparent() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_info;
    }

    @Override
    public void displayInfo(String phone, String birthday, String identifyId, String email, String address) {
        editPhone.setText(phone);
        editBirthDay.setText(birthday);
        editIdentifyId.setText(identifyId);
        editEmail.setText(email);
        editAddress.setText(address);
    }

    @OnClick({R.id.llEditProfile})
    public void onClick(View v) {
        if (!Utils.isEnabledClick(Utils.CLICK_DELAY_MSEC_500)) {
            return;
        }

        switch (v.getId()) {
            case R.id.llEditProfile:
                FragmentUtil.startFragment(getContext(), ProfileEditFragment.newInstanceProfile(), null);
                break;
            default:
                break;
        }
    }

    public void updateProfile() {
        getPresenter().updateProfile();
    }
}
