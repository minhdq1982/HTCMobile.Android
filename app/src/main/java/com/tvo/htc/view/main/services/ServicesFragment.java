package com.tvo.htc.view.main.services;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tvo.htc.R;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnCustomTabView;
import com.tvo.htc.view.main.services.appointment.AppointmentFragment;
import com.tvo.htc.view.main.services.maintenance_level.MaintenanceLevelFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ServicesFragment extends BaseFragment<ServicesContract.Presenter> implements ServicesContract.View, CpnCustomTabView.TabListener {

    @BindView(R.id.cpnCustomTabView)
    CpnCustomTabView cpnCustomTabView;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<String> mStrTabList;
    private ArrayList<Fragment> mFragmentList;


    public static ServicesFragment newInstance() {

        Bundle args = new Bundle();

        ServicesFragment fragment = new ServicesFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected ServicesContract.Presenter createPresenterInstance() {
        return new ServicesPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_services;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        initDataTab();
        cpnCustomTabView.setContent(mStrTabList);
        cpnCustomTabView.setTabListener(this);

        ServiceAdapterViewPager adapterViewPager = new ServiceAdapterViewPager(
                getActivity().getSupportFragmentManager(), mStrTabList, mFragmentList);
        viewPager.setAdapter(adapterViewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                cpnCustomTabView.setTabSelected(mStrTabList.get(i));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onTabSelected(String tabTitle) {
        for (int i = 0; i < mStrTabList.size(); i++) {
            if (mStrTabList.get(i).equals(tabTitle)) {
                viewPager.setCurrentItem(i);
            }
        }
    }

    private void initDataTab() {
        mStrTabList = Arrays.asList(getResources().getStringArray(R.array.services_arr_tab_title));
        mFragmentList = new ArrayList<>();
        mFragmentList.add(AppointmentFragment.newInstance());
        mFragmentList.add(MaintenanceLevelFragment.newInstance());
    }
}
