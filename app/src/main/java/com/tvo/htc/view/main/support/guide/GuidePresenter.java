package com.tvo.htc.view.main.support.guide;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.tvo.htc.R;
import com.tvo.htc.view.BasePresenter;
import com.tvo.htc.view.main.support.guide.guide_book.GuideBookFragment;
import com.tvo.htc.view.main.support.guide.technical_guide.TechnicalGuideFragment;
import com.tvo.htc.view.main.support.guide.warranty_policy.WarrantyPolicyFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class GuidePresenter extends BasePresenter<GuideContract.View> implements GuideContract.Presenter {
    public GuidePresenter(Context context) {
        super(context);
    }

    private List<String> mTabTitle;
    private List<Fragment> mFragment;

    @Override
    public void loadListGuide() {
        mTabTitle = Arrays.asList(getContext().getResources().getStringArray(R.array.support_guide_arr_tab));
        mFragment = new ArrayList<>();
        mFragment.add(TechnicalGuideFragment.newInstance());
        mFragment.add(GuideBookFragment.newInstance());
        mFragment.add(WarrantyPolicyFragment.newInstance());
        getView().displayGuide(mTabTitle, mFragment);
    }

    @Override
    public void changePagerSelected(int i) {
        getView().updateTabSelected(mTabTitle.get(i));
    }

    @Override
    public void changeTabTitleSelected(String tabTitle) {
        int indexOfTab = mTabTitle.indexOf(tabTitle);
        if (indexOfTab != -1) {
            getView().updateViewPagerSelected(indexOfTab);
        }
    }
}
