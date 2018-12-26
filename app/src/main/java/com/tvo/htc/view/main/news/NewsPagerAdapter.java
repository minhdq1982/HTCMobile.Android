package com.tvo.htc.view.main.news;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsPagerAdapter extends FragmentStatePagerAdapter {
    private final List<String> mTabTitle;
    private List<Fragment> mList = new ArrayList<>();
    public NewsPagerAdapter(FragmentManager fm, List<String> mTabTitle) {
        super(fm);
        this.mTabTitle = mTabTitle;

    }

    public void addFragment(Fragment fragment){
        mList.add(fragment);
    }

    @Override
    public Fragment getItem(int i) {
        return mList.get(i);
    }

    @Override
    public int getCount() {
        if(mTabTitle!=null){
            return mTabTitle.size();
        }
        return 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitle.get(position);
    }
}
