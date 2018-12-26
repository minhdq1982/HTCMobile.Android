package com.tvo.htc.view.main.support.guide.guide_book.detail;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.lib.model.response.GuildBookItemDetailResponse;

import java.util.List;

/**
 * Create by Ngocji on 11/5/2018
 **/


public class GuideBookPagerAdapter extends FragmentPagerAdapter {
    private List<GuildBookItemDetailResponse.Item> mTabTitle;

    public GuideBookPagerAdapter(FragmentManager fm, List<GuildBookItemDetailResponse.Item> mTabTitle) {
        super(fm);
        this.mTabTitle = mTabTitle;
    }

    @Override
    public Fragment getItem(int i) {
        return new Fragment();
    }

    @Override
    public int getCount() {
        return mTabTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitle.get(position).getTitle();
    }
}
