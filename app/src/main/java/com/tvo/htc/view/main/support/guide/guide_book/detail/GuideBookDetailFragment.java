package com.tvo.htc.view.main.support.guide.guide_book.detail;

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
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.model.response.GuildBookItemDetailResponse;
import com.android.lib.util.Logger;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.tvo.htc.R;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseFragment;

import java.util.List;

import butterknife.BindAnim;
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

public class GuideBookDetailFragment extends BaseFragment<GuideBookDetailContract.Presenter> implements GuideBookDetailContract.View, BaseAdapter.OnItemClickListener, SmartTabLayout.OnTabClickListener {
    private static final String KEY_ID = "KEY_ID";
    private static final String KEY_TITLE = "KEY_TITLE";
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

    @BindView(R.id.flProgress)
    FrameLayout flProgress;
    @BindView(R.id.flEmpty)
    FrameLayout flEmpty;

    @BindAnim(R.anim.menu_slide_top)
    Animation animSlileTop;
    @BindAnim(R.anim.menu_slide_bottom)
    Animation animSlideBottom;

    private GuideBookHistoryAdapter historyAdapter;
    private boolean mFullScreen = false;


    public static GuideBookDetailFragment newInstance(int id, String title) {
        Bundle args = new Bundle();
        args.putInt(KEY_ID, id);
        args.putString(KEY_TITLE, title);

        GuideBookDetailFragment fragment = new GuideBookDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected GuideBookDetailContract.Presenter createPresenterInstance() {
        return new GuideBookDetailPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_support_guide_book_detail;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultFontSize((int) getResources().getDimension(R.dimen.text_size));
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Logger.e("OnProgress: " + newProgress);
                if (newProgress >= AppConstants.WEB_VIEW_LOADING_FINISH_PERCENT) {
                    hideProgress();
                }
            }
        });
//        nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (nestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY) -> {
//            if (flProgress.getVisibility() == View.VISIBLE) return;
//            if (scrollY == (nestedScrollView.getChildAt(0).getMeasuredHeight() - nestedScrollView.getMeasuredHeight())) {
//                getPresenter().handleNextItem();
//            }
//        });
        getPresenter().loadData(getArguments().getInt(KEY_ID, -1), getArguments().getString(KEY_TITLE, ""));
    }

    @Override
    public void updateTitle(String title) {
        mNavigationBar.setTitle(title);
    }

    @Override
    public void displayListHistory(List<GuildBookItemDetailResponse.Item> list) {
        //set data navigator
        initTabNavigator(list);

        //set data menu
        historyAdapter = new GuideBookHistoryAdapter(getContext(), list);
        historyAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(historyAdapter);
    }

    private void initTabNavigator(List<GuildBookItemDetailResponse.Item> list) {
        viewPager.setAdapter(new GuideBookPagerAdapter(getActivity().getSupportFragmentManager(), list));
        smartTabLayout.setCustomTabView(R.layout.item_tab, R.id.tvTabTitle);
        smartTabLayout.setViewPager(viewPager);
        smartTabLayout.getTabAt(list.size() - 1).findViewById(R.id.imTab).setVisibility(INVISIBLE);
        changeStyleTabText(0, true);
        smartTabLayout.setOnTabClickListener(this);
    }

    @Override
    public void showEmptyContent() {
        flEmpty.setVisibility(VISIBLE);
    }

    @Override
    public void hideEmptyContent() {
        flEmpty.setVisibility(GONE);
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
    public void showProgress() {
        flProgress.setVisibility(VISIBLE);
    }

    @Override
    public void hideProgress() {
        flProgress.setVisibility(GONE);
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
