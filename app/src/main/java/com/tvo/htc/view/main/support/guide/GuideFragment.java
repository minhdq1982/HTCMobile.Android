package com.tvo.htc.view.main.support.guide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tvo.htc.R;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnCustomTabView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class GuideFragment extends BaseFragment<GuideContract.Presenter> implements GuideContract.View {

    @BindView(R.id.cpnCustomTabView)
    CpnCustomTabView cpnCustomTabView;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public static GuideFragment newInstance() {

        Bundle args = new Bundle();

        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected GuideContract.Presenter createPresenterInstance() {
        return new GuidePresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_support_guide;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().loadListGuide();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                getPresenter().changePagerSelected(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        cpnCustomTabView.setTabListener(tabTitle -> {
            getPresenter().changeTabTitleSelected(tabTitle);
        });
    }

    @Override
    public void displayGuide(List<String> tabTitles, List<Fragment> fragments) {
        if (tabTitles == null || fragments == null) {
            return;
        }
        GuideViewPagerAdapter adapter = new GuideViewPagerAdapter(getActivity().getSupportFragmentManager(), tabTitles, fragments);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(adapter);
        cpnCustomTabView.setContent(tabTitles);
    }

    @Override
    public void updateTabSelected(String tabTitle) {
        cpnCustomTabView.setTabSelected(tabTitle);
    }

    @Override
    public void updateViewPagerSelected(int indexOfTab) {
        viewPager.setCurrentItem(indexOfTab);
    }
}
