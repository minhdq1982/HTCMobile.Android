package com.tvo.htc.view.main.home;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.lib.model.Card;
import com.android.lib.model.response.GroupNews;
import com.android.lib.model.response.SurveyResponse;
import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.model.event.EventCompleteSurvey;
import com.tvo.htc.model.event.EventUpdateListCard;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.PermissionUtil;
import com.tvo.htc.util.ScreenUtils;
import com.tvo.htc.util.Utils;
import com.tvo.htc.util.switch_page.SwitchPage;
import com.tvo.htc.util.switch_page.SwitchPageImpl;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnBannerSurvey;
import com.tvo.htc.view.component.CpnTab;
import com.tvo.htc.view.main.MainActivity;
import com.tvo.htc.view.main.benefit.BenefitFragment;
import com.tvo.htc.view.main.buycar.registration.RegistrationFragment;
import com.tvo.htc.view.main.login.LoginFragment;
import com.tvo.htc.view.main.login.request.LoginRequestFragment;
import com.tvo.htc.view.main.profile.card.add_card.AddCardFragment;
import com.tvo.htc.view.main.profile.card.detail.ProfileCardDetailFragment;
import com.tvo.htc.view.main.services.make_appointment.MakeAppointmentFragment;
import com.tvo.htc.view.main.support.findlocation.FindLocationFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.tvo.htc.model.LocalDataManager.PREF_LOGIN_RESPONSE;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public class HomeFragment extends BaseFragment<HomeContract.Presenter> implements
        HomeContract.View, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final int REQUEST_CODE_CALL = 10001;
    public static boolean isRunning;

    @BindView(R.id.flHomeNavigationBar)
    FrameLayout flHomeNavigationBar;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.llHomeContent)
    LinearLayout llHomeContent;
    @BindView(R.id.ivGridBackground)
    ImageView ivGridBackground;
    @BindView(R.id.flLogin)
    FrameLayout flLogin;
    @BindView(R.id.llMembershipCard)
    LinearLayout llMembershipCard;
    @BindView(R.id.vpCard)
    ViewPager vpCard;
    @BindView(R.id.rvBanner)
    RecyclerView rvBanner;
    @BindView(R.id.rvGroupNews)
    RecyclerView rvGroupNews;
    @BindView(R.id.cpnBannerSurvey)
    CpnBannerSurvey cpnBannerSurvey;
    @BindView(R.id.tvAddCardMessage)
    TextView tvAddCardMessage;
    @BindView(R.id.llCardAction)
    LinearLayout llCardAction;
    @BindView(R.id.tvHotline)
    TextView tvHotline;
    @BindView(R.id.animationView)
    LottieAnimationView animationView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private SurveyResponse mSurveyResponse;

    private CardPagerAdapter mCardAdapter;
    private int mScrollY;
    private int mTopBarHeight;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected HomeContract.Presenter createPresenterInstance() {
        return new HomePresenter(getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        isRunning = true;
        EventBusUtils.register(this);
        LocalDataManager.getInstance(getContext()).registerEvent(this);

        vpCard.setPageMargin((int) getResources().getDimension(R.dimen.card_margin));

        boolean isShowHomeLogin = LocalDataManager.getInstance(getActivity()).isShowHomeLogin();
        if (!isShowHomeLogin) {
            flLogin.setVisibility(View.GONE);
        }

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mNavigationBar.getLayoutParams();
        layoutParams.topMargin = ScreenUtils.getStatusBarHeight(getActivity());
        mNavigationBar.setLayoutParams(layoutParams);

        mTopBarHeight = (int) (ScreenUtils.getStatusBarHeight(getActivity())
                + getResources().getDimension(R.dimen.navigation_height_default));
        layoutParams = (FrameLayout.LayoutParams) llHomeContent.getLayoutParams();
        layoutParams.topMargin = mTopBarHeight;
        llHomeContent.setLayoutParams(layoutParams);

        initBanners();

        swipeRefreshLayout.setProgressViewOffset(false,100,200);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            getPresenter().loadData(true);
        });

        if (hasLogin()) {
            ivGridBackground.setVisibility(View.VISIBLE);
            initScroll();
        } else {
            flHomeNavigationBar.setAlpha(1f);
        }

        if (SwitchPageImpl.getInstance().handleMainSwitchPage(
                getActivity(),
                getActivity().getIntent(),
                new SwitchPage.SurveyCallBack() {
                    @Override
                    public void onSurveyChecked() {
                        getPresenter().loadData(false);
                    }

                    @Override
                    public void onSurveyComplete() {
                        getPresenter().loadData(false);
                    }
                })) return;

        getPresenter().loadData(false);

        float density = getResources().getDisplayMetrics().density;
        FrameLayout.LayoutParams params = ((FrameLayout.LayoutParams) animationView.getLayoutParams());
        params.rightMargin = (int) (params.rightMargin * (3.0 / density));
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @OnClick({R.id.ivCloseBannerLogin, R.id.cpnbLogin, R.id.ivAdd, R.id.ivBenefit, R.id.ivCardInfo, R.id.cpnBannerSurvey,
            R.id.tvHotline, R.id.ivFollowFacebook, R.id.ivFollowYoutube})
    void onClick(View view) {
        if (!Utils.isEnabledClick(Utils.CLICK_DELAY_MSEC_500)) {
            return;
        }

        Fragment fragment;
        switch (view.getId()) {
            case R.id.ivCloseBannerLogin:
                flLogin.setVisibility(View.GONE);
                LocalDataManager.getInstance(getActivity()).saveIsShowHomeLogin(false);
                break;
            case R.id.cpnbLogin:
                fragment = LoginFragment.newInstance();
                FragmentUtil.startFragmentNoTabbar(getContext(), fragment, null);
                break;
            case R.id.ivAdd:
                fragment = AddCardFragment.newInstance();
                FragmentUtil.startFragment(getContext(), fragment, null);
                break;
            case R.id.ivBenefit:
                fragment = BenefitFragment.newInstance();
                FragmentUtil.startFragment(getContext(), fragment, null);
                break;
            case R.id.ivCardInfo:
                fragment = ProfileCardDetailFragment.newInstance(-1, SessionDataManager.getInstance().getCards());
                FragmentUtil.startFragment(getContext(), fragment, null);
                break;
            case R.id.cpnBannerSurvey:
                if (mSurveyResponse != null && mSurveyResponse.getData() != null && mSurveyResponse.getData().getSurveyType() != null) {
                    SwitchPageImpl.getInstance().handleGoToSurveyMain(
                            getActivity(),
                            mSurveyResponse.getData().getToken(),
                            mSurveyResponse.getData().getSurveyType(),
                            cpnBannerSurvey);
                }
                break;
            case R.id.tvHotline:
                if (!PermissionUtil.checkAndRequestPermission(this, Manifest.permission.CALL_PHONE, REQUEST_CODE_CALL)) {
                    Utils.callHotLine(getActivity(), tvHotline.getText().toString().replace(".", ""));
                }
                break;
            case R.id.ivFollowFacebook:
                Utils.openFacebookPage(getActivity());
                break;
            case R.id.ivFollowYoutube:
                Utils.openYoutubeChanel(getActivity());
                break;
            default:
                break;
        }
    }

    @Override
    public void showWait() {
        swipeRefreshLayout.setRefreshing(false);
        showWaitDialog();
    }

    @Override
    public void displayListCard(List<Card> cards, boolean isShowEmptyList) {
        if (isShowEmptyList) {
            tvAddCardMessage.setVisibility(View.VISIBLE);
            llCardAction.setVisibility(View.GONE);
        } else {
            tvAddCardMessage.setVisibility(View.GONE);
            llCardAction.setVisibility(View.VISIBLE);
        }

        mCardAdapter = new CardPagerAdapter(getActivity(), cards, new CardPagerAdapter.CallBack() {
            @Override
            public void onItemClick(int id, List<Card> list) {
                FragmentUtil.startFragment(getContext(), ProfileCardDetailFragment.newInstance(id, list), null);
            }

            @Override
            public void onAddCard() {
                FragmentUtil.startFragment(getContext(), AddCardFragment.newInstance(), null);
            }
        });
        vpCard.setAdapter(mCardAdapter);
    }

    @Override
    public void displayHotline(String hotline) {
        tvHotline.setText(hotline);
    }

    @Override
    public void displayGroupNews(List<GroupNews> groupNews) {
        GroupNewsAdapter adapter = new GroupNewsAdapter(getActivity(), groupNews);
        rvGroupNews.setAdapter(adapter);
    }

    @Override
    public void getSurveySuccess(SurveyResponse surveyResponse) {
        Logger.e("Display survey--> ");
        mSurveyResponse = surveyResponse;
        if (surveyResponse.getData() != null) {
            cpnBannerSurvey.setVisibility(View.VISIBLE);

            if (surveyResponse.getData().getSurveyType() == SurveyResponse.SurveyType.NEW_CAR) {
                cpnBannerSurvey.setType(SurveyResponse.SurveyType.NEW_CAR);
            } else {
                cpnBannerSurvey.setType(SurveyResponse.SurveyType.REPAIR_PROTECTION);
            }
        } else {
            cpnBannerSurvey.setVisibility(View.GONE);
        }
    }

    @Override
    public void allRequestFinish() {
        dismissWaitDialog();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showGuest(boolean showHomeLogin) {
        if (!showHomeLogin) {
            flLogin.setVisibility(View.GONE);
        } else {
            flLogin.setVisibility(View.VISIBLE);
        }
        llMembershipCard.setVisibility(View.GONE);
    }

    @Override
    public void showLogin() {
        flLogin.setVisibility(View.GONE);
        llMembershipCard.setVisibility(View.VISIBLE);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void updateListCardEvent(EventUpdateListCard event) {
        mLoginResponse.getData().setCards(event.getListCard());
        getPresenter().handleUpdateListCard(event, mCardAdapter == null);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void updateCompleteSurvey(EventCompleteSurvey survey) {
        cpnBannerSurvey.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        EventBusUtils.unregister(this);
        LocalDataManager.getInstance(getContext()).unregisterEvent(this);
        super.onDestroy();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(PREF_LOGIN_RESPONSE)) {
            getPresenter().loadLogin();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CALL && grantResults[0] == PERMISSION_GRANTED) {
            Utils.callHotLine(getActivity(), tvHotline.getText().toString().replace(".", ""));
        }
    }


    private void initScroll() {
        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            int scrollY = nestedScrollView.getScrollY(); // For ScrollView
            int scrollX = nestedScrollView.getScrollX(); // For HorizontalScrollView
            // DO SOMETHING WITH THE SCROLL COORDINATES

            View view = (View) nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
            int diff = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()));
            if (diff == 0) {
                flHomeNavigationBar.setAlpha(1f);
            } else {
                if (Math.abs(scrollY - mScrollY) > (mTopBarHeight / 4)) {
                    flHomeNavigationBar.setAlpha((float) (0.25 * scrollY / mTopBarHeight));
                } else {
                    if (scrollY <= mTopBarHeight) {
                        flHomeNavigationBar.setAlpha((float) (1.0 * scrollY / mTopBarHeight));
                    } else {
                        flHomeNavigationBar.setAlpha(1f);
                    }
                }
            }
            mScrollY = scrollY;
        });
    }

    private void initBanners() {
        List<BannerResponse> list = new ArrayList<>();
        list.add(new BannerResponse(getString(R.string.map_title), R.drawable.img_banner));
        list.add(new BannerResponse(getString(R.string.buy_car), R.drawable.img_banner_1));
        list.add(new BannerResponse(getString(R.string.registration), R.drawable.img_banner_2));
        list.add(new BannerResponse(getString(R.string.services_option), R.drawable.img_banner_3));
        BannerAdapter adapter = new BannerAdapter(getActivity(), list);
        rvBanner.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvBanner.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Fragment fragment;
            switch (position) {
                // Make appointment
                case 3:
                    if (Utils.hasLogin(getActivity())) {
                        ((MainActivity) getActivity()).setTabSelected(CpnTab.TabType.SERVICE, true);
                        fragment = MakeAppointmentFragment.newInstance();
                        FragmentUtil.startFragmentNoTabbar(getActivity(), fragment, null);
                    } else {
                        fragment = LoginRequestFragment.newInstance();
                        FragmentUtil.startFragment(getActivity(), fragment, null);
                    }
                    break;
                // Test drive
                case 2:
                    ((MainActivity) getActivity()).setTabSelected(CpnTab.TabType.CAR, true);
                    fragment = RegistrationFragment.newInstance();
                    FragmentUtil.startFragment(getActivity(), fragment, null);
                    break;
                // buy car
                case 1:
                    ((MainActivity) getActivity()).setTabSelected(CpnTab.TabType.CAR);
                    break;
                //Find agency
                case 0:
                    ((MainActivity) getActivity()).setTabSelected(CpnTab.TabType.SUPPORT, true);
                    fragment = FindLocationFragment.newInstance();
                    FragmentUtil.startFragment(getActivity(), fragment, null);
                    break;
                default:
                    break;
            }
        });
    }
}
