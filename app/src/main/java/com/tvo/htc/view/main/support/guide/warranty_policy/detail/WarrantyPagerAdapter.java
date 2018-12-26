package com.tvo.htc.view.main.support.guide.warranty_policy.detail;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.lib.model.response.WarrantyPolicyResponse;

import java.util.List;

/**
 * Create by Ngocji on 11/5/2018
 **/


public class WarrantyPagerAdapter extends FragmentPagerAdapter {
    private List<WarrantyPolicyResponse.WarrantyPolicy> mTabTitle;

    public WarrantyPagerAdapter(FragmentManager fm, List<WarrantyPolicyResponse.WarrantyPolicy> mTabTitle) {
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
        return mTabTitle.get(position).getName() != null ? mTabTitle.get(position).getName() : "Nội dung " + position;
    }
}
