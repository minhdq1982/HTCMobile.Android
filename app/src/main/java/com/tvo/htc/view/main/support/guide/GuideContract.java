package com.tvo.htc.view.main.support.guide;

import android.support.v4.app.Fragment;

import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface GuideContract {
    interface View extends BaseContract.View {

        void displayGuide(List<String> tabTitles, List<Fragment> fragments);

        void updateTabSelected(String tabTitle);

        void updateViewPagerSelected(int indexOfTab);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadListGuide();

        void changePagerSelected(int i);

        void changeTabTitleSelected(String tabTitle);
    }
}
