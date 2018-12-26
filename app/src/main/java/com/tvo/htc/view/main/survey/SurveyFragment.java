package com.tvo.htc.view.main.survey;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Pair;
import android.view.View;
import android.widget.FrameLayout;

import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.model.AnswerSurvey;
import com.tvo.htc.model.Question;
import com.tvo.htc.model.event.EventCompleteSurvey;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.Utils;
import com.tvo.htc.util.switch_page.SwitchPageImpl;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.component.CpnButton;
import com.tvo.htc.view.component.CpnIndicator;
import com.tvo.htc.view.component.CpnNavigationBar;
import com.tvo.htc.view.component.CpnSurveyViewPager;
import com.tvo.htc.view.dialog.ConfirmDialog;
import com.tvo.htc.view.main.notification.NotificationFragment;
import com.tvo.htc.view.main.survey.adapter.SurveyPagerAdapter;
import com.tvo.htc.view.main.survey.item.SurveyItemFragment;
import com.tvo.htc.view.main.survey.question.BaseQuestion;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class SurveyFragment extends BaseFragment<SurveyContract.Presenter> implements SurveyContract.View {
    private static final String KEY_TYPE = "KEY_TYPE";
    private static final String KEY_TOKEN = "KEY_TOKEN";
    @BindView(R.id.viewPager)
    CpnSurveyViewPager viewPager;
    @BindView(R.id.flFirstShow)
    FrameLayout flFirstShow;
    @BindView(R.id.indicator)
    CpnIndicator indicator;
    @BindView(R.id.cpnbPrevious)
    CpnButton cpnbPrevious;
    @BindView(R.id.cpnbNext)
    CpnButton cpnbNext;

    private SurveyListener mListener;

    public interface SurveyListener {
        void onCompleted();
    }

    public static SurveyFragment newInstance(int ordinalType, String token) {

        Bundle args = new Bundle();
        args.putInt(KEY_TYPE, ordinalType);
        args.putString(KEY_TOKEN, token);
        SurveyFragment fragment = new SurveyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setSurveyListener(SurveyListener listener) {
        this.mListener = listener;
    }

    @Override
    protected SurveyContract.Presenter createPresenterInstance() {
        return new SurveyPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_survey_started;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        viewPager.setPageMargin(Utils.getDimensions(getContext(), R.dimen.survey_item_margin));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int i) {
                SurveyItemFragment currentPage = (SurveyItemFragment) viewPager.getAdapter().instantiateItem(viewPager, i);
                SurveyItemFragment dataPage = null;
                if (getPresenter().getCurrentPage() >= 0) {
                    dataPage = (SurveyItemFragment) viewPager.getAdapter().instantiateItem(viewPager, getPresenter().getCurrentPage());
                }
                getPresenter().handlePagerChange(i, currentPage, dataPage);
                viewPager.setEnableSwipeLeft(i != 0);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
//        frTouch.setOnTouchListener(new OnSwipeGestureDetector(getContext()) {
//            @Override
//            public void onSwipeRight() {
//                getPresenter().handleSwipeRight(viewPager.getEnableSwipe());
//            }
//        });
        showWaitDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(() -> getPresenter().loadData(getArguments().getInt(KEY_TYPE, -1), getArguments().getString(KEY_TOKEN, "")), 400L);
    }

    @Override
    public void displayViewPager(int tabCount, HashMap<Integer, Pair<BaseQuestion.HeaderType, List<Question>>> mapQuestion, HashMap<Integer, List<AnswerSurvey>> mapAnswer, int currentItem) {
        SurveyPagerAdapter adapter = new SurveyPagerAdapter(getChildFragmentManager(),
                tabCount,
                mapQuestion,
                mapAnswer,
                enable -> changeEnableSwipe(enable));
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager, tabCount);
        indicator.setTabListener(position ->
                getPresenter().handleIndicatorClicked(position));
        viewPager.setCurrentItem(currentItem);
//        SurveyItemFragment currentPage = (SurveyItemFragment) viewPager.getAdapter().instantiateItem(viewPager, currentItem);
//        getPresenter().checkEnableSwipe(currentPage);
    }

    @Override
    public void hideDialogWait() {
        dismissWaitDialog();
    }

    @Override
    public void showDialogWait() {
        showWaitDialog();
    }

    @Override
    public void startAnswer() {
        flFirstShow.setVisibility(View.GONE);
    }

    @Override
    public void showErrorAvailable() {
        showMessage(getString(R.string.survey_error_available), () -> FragmentUtil.removeFragment(getContext()));
    }

    @Override
    public void displayConfirmDialog() {
        ConfirmDialog dialog = new ConfirmDialog.Builder().setTextMessage(getString(R.string.survey_confirm_exit))
                .create();
        dialog.addListener(new ConfirmDialog.Callback() {
            @Override
            public void onConfirmClicked() {
                finishSurvey();
            }

            @Override
            public void onCancelClicked() {

            }
        });
        dialog.show(getChildFragmentManager(), null);
    }

    @Override
    public void setCurrentPagerQuestion(int currentPagerQuestion) {
        viewPager.setCurrentItem(currentPagerQuestion);
    }

    @Override
    public void changeEnableSwipe(boolean enable) {
        Logger.e("ChangeEnable: " + enable);
        viewPager.setEnableCanSwipe(enable);
    }

    @Override
    public void setSelectedIndicator(int i) {
        indicator.setSelectedIndicator(i);
    }

    @Override
    public void setNormalIndicator(int i) {
        indicator.setNormalIndicator(i);
    }

    @Override
    public void finishSurvey() {
        SwitchPageImpl.getInstance().handleBackSurvey(getContext());
    }

    @Override
    public void displaySuccessSurvey() {
        SurveySuccessDialog dialog = new SurveySuccessDialog();
        dialog.setCallback(() -> {
            if (mListener != null) {
                mListener.onCompleted();
            }
            EventBusUtils.postEvent(new EventCompleteSurvey());
            FragmentUtil.removeFragment(getContext());
        });
        dialog.show(getChildFragmentManager(), null);
    }

    @Override
    public void showErrorAnswerDialog() {
        showMessage(getString(R.string.survey_error_answer_require));
    }

    @Override
    public void showSuccessCpnbNext() {
        cpnbNext.setText(getString(R.string.survey_btn_success));
    }

    @Override
    public void showNormalCpnbNext() {
        cpnbNext.setText(getString(R.string.survey_btn_next));
    }

    @Override
    public void showErrorIndicatorClick() {
        showMessage(getString(R.string.survey_complete_first));
    }

    @Override
    public void onDestroyView() {
        getPresenter().savePref();
        super.onDestroyView();
    }

    @Override
    public void onNavigationBtClick(CpnNavigationBar.NavigationType type) {
        switch (type) {
            case BT_BACK:
                getPresenter().handleBack();
                break;
            default:
                super.onNavigationBtClick(type);
        }
    }

    @OnClick({R.id.cpnbNext, R.id.cpnbPrevious, R.id.cpnbStart, R.id.flFirstShow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cpnbStart:
                getPresenter().handleStart();
                break;
            case R.id.cpnbNext:
                getPresenter().handleNext(getCurrentFragmentViewPager());
                break;
            case R.id.cpnbPrevious:
                getPresenter().handlePrevious(getCurrentFragmentViewPager());
                break;
            case R.id.flFirstShow:
                break;
        }
    }

    private SurveyItemFragment getCurrentFragmentViewPager() {
        if (viewPager.getAdapter() != null) {
            Fragment fragment = (Fragment) viewPager.getAdapter().instantiateItem(viewPager, viewPager.getCurrentItem());
            if (fragment instanceof SurveyItemFragment) {
                return ((SurveyItemFragment) fragment);
            } else {
                return null;
            }
        }
        return null;
    }
}
