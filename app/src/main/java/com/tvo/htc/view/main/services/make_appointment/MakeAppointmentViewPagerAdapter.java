package com.tvo.htc.view.main.services.make_appointment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Create by Ngocji on 10/17/2018
 **/


public class MakeAppointmentViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mListStep;

    public MakeAppointmentViewPagerAdapter(FragmentManager fm, List<Fragment> mListStep) {
        super(fm);
        this.mListStep = mListStep;
    }

    @Override
    public Fragment getItem(int i) {
        return mListStep.get(i);
    }

    @Override
    public int getCount() {
        return mListStep.size();
    }
}
