package com.tvo.htc.view.main.profile;

import android.support.v4.app.Fragment;

import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface ProfileContract {
    interface View extends BaseContract.View {
        void displayTabProfile(List<String> listTitle, List<Fragment> listFragment);

        void updateTabSelected(int position, boolean isSelected);

        void updateInfoProfile(String avatar, String name);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void initTabProfile();

        void loadProfile(int id, boolean initTab);


        void handleSaveSuccess();

    }
}
