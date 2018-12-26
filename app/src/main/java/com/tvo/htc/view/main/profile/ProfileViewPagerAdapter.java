package com.tvo.htc.view.main.profile;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ProfileViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<String> mTabTitles;
    private final List<Fragment> mFragmentList;

    public ProfileViewPagerAdapter(FragmentManager fm, List<String> mTabTitles, List<Fragment> mFragmentList) {
        super(fm);
        this.mTabTitles = mTabTitles;
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        if (mTabTitles != null) {
            return mTabTitles.size();
        }
        return 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles.get(position);
    }
}
