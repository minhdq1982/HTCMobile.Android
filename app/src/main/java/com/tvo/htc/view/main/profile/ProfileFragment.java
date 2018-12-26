package com.tvo.htc.view.main.profile;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.model.event.EventSaveProfileSuccess;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CircleImageView;
import com.tvo.htc.view.component.CpnLockableViewPager;
import com.tvo.htc.view.component.CpnNavigationBar;
import com.tvo.htc.view.main.profile.setting.SettingFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public class ProfileFragment extends BaseFragment<ProfileContract.Presenter>
        implements ProfileContract.View {

    public static final String KEY_DATA_TYPE = "KEY_DATA_TYPE";

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    CpnLockableViewPager viewPager;
    @BindView(R.id.cpnbCircleImage)
    CircleImageView cpnbCircleImage;
    @BindView(R.id.tvName)
    TextView tvName;

    public static ProfileFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(KEY_DATA_TYPE, type);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ProfileFragment newInstance() {
        return newInstance(1);
    }

    @Override
    protected ProfileContract.Presenter createPresenterInstance() {
        return new ProfilePresenter(getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        mNavigationBar.setType(getArguments().getInt(KEY_DATA_TYPE, 1));
        viewPager.setPagingEnabled(false);
        EventBusUtils.register(this);
        Logger.d(mLoginResponse.getData().getId() + "");
        getPresenter().loadProfile(mLoginResponse.getData().getId(), true);
    }

    @Override
    protected boolean isShowGridBackground() {
        return true;
    }

    @Override
    public void onNavigationBtClick(CpnNavigationBar.NavigationType type) {
        switch (type) {
            case BT_SETTING:
                FragmentUtil.startFragment(getActivity(), SettingFragment.newInstance(), null);
                break;
            case BT_BACK:
                FragmentUtil.removeFragment(getActivity());
                break;
            case BT_IGNORE:
                FragmentUtil.removeFragment(getContext());
                break;
            default:
                super.onNavigationBtClick(type);
        }
    }

    @Override
    public void displayTabProfile(List<String> listTitle, List<Fragment> listFragment) {
        ProfileViewPagerAdapter adapterViewPager = new ProfileViewPagerAdapter(
                getActivity().getSupportFragmentManager(), listTitle, listFragment);
        viewPager.setOffscreenPageLimit(listFragment.size());
        viewPager.setAdapter(adapterViewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateTabSelected(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTabSelected(tab.getPosition(), false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        createTabLayout(listTitle);
        changeTab(0, true);
    }

    @Override
    public void updateTabSelected(int position, boolean isSelected) {
        changeTab(position, isSelected);
    }

    @Override
    public void updateInfoProfile(String avatar, String name) {
        ImageLoader.loadImage(getContext(), cpnbCircleImage, R.drawable.img_avatar_default, avatar);
        tvName.setText(name);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onSaveSuccess(EventSaveProfileSuccess event) {
        getPresenter().handleSaveSuccess();
    }


    private void createTabLayout(List<String> list) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i = 0; i < list.size(); i++) {
            View view = inflater.inflate(R.layout.item_profile_tab, null);
            view.setLayoutParams(new LayoutParams(-1, -1));
            ((TextView) view.findViewById(R.id.tvTabTitle)).setText(list.get(i));
            if (i == list.size() - 1) {
                view.findViewById(R.id.divider).setVisibility(View.GONE);
            }
            tabLayout.getTabAt(i).setCustomView(view);
        }
    }

    private void changeTab(int position, boolean selected) {
        View view = tabLayout.getTabAt(position).getCustomView();
        View root = view.findViewById(R.id.llRootView);
        TextView tvTabTitle = view.findViewById(R.id.tvTabTitle);
        if (selected) {
            tvTabTitle.setTypeface(null, Typeface.BOLD);
            root.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
        } else {
            tvTabTitle.setTypeface(null, Typeface.NORMAL);
            root.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorTabNone));
        }
    }

    @Override
    public void onDestroy() {
        EventBusUtils.unregister(this);
        super.onDestroy();
    }
}
