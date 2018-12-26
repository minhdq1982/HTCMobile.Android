package com.tvo.htc.view.main.support.guide.warranty_policy.detail;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.model.response.WarrantyPolicyResponse.WarrantyPolicy;
import com.android.lib.util.Logger;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.Gravity.RIGHT;
import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class WarrantyDetailFragment extends BaseFragment<WarrantyDetailContract.Presenter> implements WarrantyDetailContract.View, BaseAdapter.OnItemClickListener, SmartTabLayout.OnTabClickListener {
    public static final String KEY_DATA = "KEY_DATA";
    public static final String KEY_POSITION_SELECTED = "KEY_POSITION_SELECTED";

    private final long DURATION_ANIMATE = 200;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.smartTabLayout)
    SmartTabLayout smartTabLayout;
    @BindView(R.id.llBottomNavigator)
    View llBottomNavigator;

    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.llMenuOpen)
    LinearLayout llMenuOpen;
    @BindView(R.id.imMenu)
    ImageView imMenu;

    private WarrantyHistoryAdapter historyAdapter;
    private boolean mFullScreen = false;

    public static WarrantyDetailFragment newInstance(List<WarrantyPolicy> items, int position) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY_DATA, new ArrayList<>(items));
        args.putInt(KEY_POSITION_SELECTED, position);
        WarrantyDetailFragment fragment = new WarrantyDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected WarrantyDetailContract.Presenter createPresenterInstance() {
        return new WarrantyDetailPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_support_warranty_policy_detail;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultFontSize((int) getResources().getDimension(R.dimen.text_size));
        webView.getSettings().setDefaultTextEncodingName("utf-8");
//        nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (nestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY) -> {
//            if (scrollY == (nestedScrollView.getChildAt(0).getMeasuredHeight() - nestedScrollView.getMeasuredHeight())) {
//                getPresenter().handleNextItem();
//            }
//        });
        List<WarrantyPolicy> list = getArguments().getParcelableArrayList(KEY_DATA);
        int position = getArguments().getInt(KEY_POSITION_SELECTED, 0);
        getPresenter().loadData(list, position);
    }

    @Override
    public void displayListHistory(List<WarrantyPolicy> list) {
        //set data navigator
        initTabNavigator(list);

        //set data menu
        historyAdapter = new WarrantyHistoryAdapter(getContext(), list);
        historyAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(historyAdapter);
    }

    private void initTabNavigator(List<WarrantyPolicy> list) {
        viewPager.setAdapter(new WarrantyPagerAdapter(getActivity().getSupportFragmentManager(), list));
        smartTabLayout.setCustomTabView(R.layout.item_tab, R.id.tvTabTitle);
        smartTabLayout.setViewPager(viewPager);
        smartTabLayout.getTabAt(list.size() - 1).findViewById(R.id.imTab).setVisibility(GONE);
        changeStyleTabText(0, true);
        smartTabLayout.setOnTabClickListener(this);
    }

    @Override
    public void showErrorMessage() {
        showMessage(getString(R.string.support_book_guide_error_load), () -> FragmentUtil.removeFragment(getActivity()));
    }

    @Override
    public void onItemClick(BaseAdapter adapter, View view, int position) {
        getPresenter().handleItemClicked(position);
    }

    @Override
    public void showData(String content) {
        webView.loadData(content, "text/html; charset=utf-8", "UTF-8");
    }

    @Override
    public void updateSelectedItem(int prePosition, int position) {
        historyAdapter.notifyItemChanged(prePosition);
        historyAdapter.notifyItemChanged(position);

        changeStyleTabText(prePosition, false);
        changeStyleTabText(position, true);
        viewPager.setCurrentItem(position);
    }

    @Override
    public void updateScaleWebView(int scalePercentInScreen) {
        webView.setInitialScale(scalePercentInScreen);
        nestedScrollView.invalidate();
    }

    @Override
    public void onTabClicked(int position) {
        getPresenter().handleItemClicked(position);
    }

    @OnClick({R.id.imOpenDrawer, R.id.imFullScreen, R.id.imScaleUp, R.id.imScaleDown, R.id.imMenu, R.id.imCloseMenu, R.id.ivCloseDrawer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivCloseDrawer:
                drawerLayout.closeDrawer(RIGHT);
                break;
            case R.id.imOpenDrawer:
                drawerLayout.openDrawer(RIGHT);
                break;
            case R.id.imFullScreen:
                pickFullScreen();
                break;
            case R.id.imScaleUp:
                getPresenter().handleScaleUp();
                break;
            case R.id.imScaleDown:
                getPresenter().handleScaleDown();
                break;
            case R.id.imMenu:
                showFloatingMenu(true);
                break;
            case R.id.imCloseMenu:
                showFloatingMenu(false);
                break;
        }
    }


    private void pickFullScreen() {
        mFullScreen = !mFullScreen;
        Logger.e(getActivity().getWindow().getAttributes().toString());
        if (mFullScreen) {
            getActivity().getWindow().addFlags(FLAG_FULLSCREEN);
            mNavigationBar.setVisibility(GONE);
            llBottomNavigator.setVisibility(GONE);
        } else {
            getActivity().getWindow().clearFlags(FLAG_FULLSCREEN);
            mNavigationBar.setVisibility(VISIBLE);
            llBottomNavigator.setVisibility(VISIBLE);
        }
    }

    private void changeStyleTabText(int position, boolean isSelected) {
        int color = R.color.colorTextNone;
        if (isSelected) {
            color = R.color.colorWhite;
        }
        TextView textView = smartTabLayout.getTabAt(position).findViewById(R.id.tvTabTitle);
        textView.setTextColor(ContextCompat.getColor(getContext(), color));
    }

    /**
     * Function show / hide button menu
     */
    private int mHeightExpand = 0;
    private int mHeightCollapse = 0;

    private void showFloatingMenu(boolean showing) {
        if (mHeightExpand == 0 && mHeightCollapse == 0) {
            mHeightExpand = llMenuOpen.getHeight();
            mHeightCollapse = imMenu.getHeight();
        }
        if (showing) {
            llMenuOpen.setVisibility(VISIBLE);
            llMenuOpen.getLayoutParams().height = imMenu.getMeasuredHeight();
            expand(llMenuOpen, mHeightCollapse, mHeightExpand);
            imMenu.animate().alpha(0f).setDuration(DURATION_ANIMATE).start();
            delayAnimate(imMenu, GONE);
        } else {
            imMenu.setVisibility(VISIBLE);
            imMenu.animate().alpha(1f).setDuration(DURATION_ANIMATE).start();
            collapse(llMenuOpen, mHeightExpand, mHeightCollapse);
            delayAnimate(llMenuOpen, INVISIBLE);
        }
    }

    private void delayAnimate(View v, int visibility) {
        new Handler().postDelayed(() -> v.setVisibility(visibility), DURATION_ANIMATE + 100);
    }

    private void expand(final View v, int fromHeight, int targetHeight) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(fromHeight, targetHeight);
        valueAnimator.addUpdateListener(animation -> {
            int height = (int) animation.getAnimatedValue();
            int margin = 25 - ((height / targetHeight) * 25);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, height);
            params.setMargins(0, 0, 0, margin);
            v.setLayoutParams(params);
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(DURATION_ANIMATE);
        valueAnimator.start();
    }

    private void collapse(final View v, int fromHeight, int targetHeight) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(fromHeight, targetHeight);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(animation -> {
            int height = (int) animation.getAnimatedValue();
            int margin = 25 - ((height / fromHeight) * 25);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, height);
            params.setMargins(0, 0, 0, margin);
            v.setLayoutParams(params);
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(DURATION_ANIMATE);
        valueAnimator.start();
    }
}
