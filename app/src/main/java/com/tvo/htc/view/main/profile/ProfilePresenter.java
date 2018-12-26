package com.tvo.htc.view.main.profile;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.event.EventNumberNotification;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BasePresenter;
import com.tvo.htc.view.main.profile.car.ProfileCarFragment;
import com.tvo.htc.view.main.profile.card.ProfileCardFragment;
import com.tvo.htc.view.main.profile.info.ProfileInfoFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ThinhNH on 3/20/2018.
 */

public class ProfilePresenter extends BasePresenter<ProfileContract.View> implements ProfileContract.Presenter {
    public ProfilePresenter(Context context) {
        super(context);
        mLoginResponse = LocalDataManager.getInstance(getContext()).getLoginResponse();
    }

    private List<String> mListTabTitle;
    private List<Fragment> mListFragment;

    private LoginResponse mLoginResponse;


    @Override
    public void loadProfile(int userId, boolean initTab) {
        RESTManager.getInstance().getProfile(userId, new IRequestListener<LoginResponse>() {
            @Override
            public void onCompleted(LoginResponse data) {
                super.onCompleted(data);
                if (data != null && data.getData() != null) {
                    mLoginResponse = data;
                    LocalDataManager.getInstance(getContext()).saveLoginResponse(data);
                    if (initTab) {
                        initTabProfile();
                        initInfoProfile(data.getData());
                    } else {
                        initInfoProfile(data.getData());
                        ((ProfileInfoFragment) (mListFragment.get(0))).getPresenter().loadInfo(data);
                    }
                    EventBusUtils.postEvent(new EventNumberNotification(data.getData().getUnreadNotification()));
                }
            }
        });
    }

    private void initInfoProfile(LoginResponse.Data data) {
        getView().updateInfoProfile(Utils.getImagePath(data.getAvatar()), data.getName());
    }

    @Override
    public void initTabProfile() {
        mListTabTitle = Arrays.asList(getContext().getResources().getStringArray(R.array.profile_arr_tab_title));
        mListFragment = new ArrayList<>();
        mListFragment.add(ProfileInfoFragment.newInstance());
        mListFragment.add(ProfileCarFragment.newInstance());
        mListFragment.add(ProfileCardFragment.newInstance());

        getView().displayTabProfile(mListTabTitle, mListFragment);
    }

    @Override
    public void handleSaveSuccess() {
        mLoginResponse = LocalDataManager.getInstance(getContext()).getLoginResponse();
        if (mLoginResponse != null && mLoginResponse.getData() != null) {
            initInfoProfile(mLoginResponse.getData());
            ProfileInfoFragment infoFragment = (ProfileInfoFragment) mListFragment.get(0);
            if (infoFragment != null) {
                infoFragment.updateProfile();
            }
        }
    }
}