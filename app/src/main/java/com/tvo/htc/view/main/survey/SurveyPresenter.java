package com.tvo.htc.view.main.survey;

import android.content.Context;
import android.util.Pair;

import com.android.lib.RESTManager;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.CarsResponse;
import com.android.lib.model.response.SimpleResponse;
import com.android.lib.model.response.SurveyResponse.SurveyType;
import com.android.lib.util.Logger;
import com.tvo.htc.model.AnswerSurvey;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.SaveSurvey;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.view.BasePresenter;
import com.tvo.htc.view.main.survey.factory.QuestionFactory;
import com.tvo.htc.view.main.survey.factory.QuestionFactoryImpl;
import com.tvo.htc.view.main.survey.item.SurveyItemFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.tvo.htc.util.AppConstants.SURVEY_COUNT_TAB;
import static com.tvo.htc.util.AppConstants.SURVEY_COUNT_TAB_SELL;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class SurveyPresenter extends BasePresenter<SurveyContract.View> implements SurveyContract.Presenter {
    public SurveyPresenter(Context context) {
        super(context);
        mListFinishQuestion = new HashMap<>();
        httpRequestOption = new HTTPRequestOption(true, false);
    }

    private HashMap<Integer, List<AnswerSurvey>> mListFinishQuestion;
    private int mCurrentPager = 0;
    private int mCountFinish = -1;
    private boolean nextClicked = false;
    private int mOrdinalType;
    private int countLoadApi = 0;
    private HTTPRequestOption httpRequestOption;

    //todo init token survey
    private String mTokenSurvey = "";

    @Override
    public void loadData(int ordinalType, String token) {
        mOrdinalType = ordinalType;
        mTokenSurvey = token;

        //todo handle check validate token survey
//        if (mTokenSurvey.isEmpty()){
//            getView().hideDialogWait();
//            getView().showErrorAvailable();
//            return;
//        }
        loadAgency();
        loadCar();
    }

    @Override
    public void handleNext(SurveyItemFragment dataPager) {
        if (saveDataAnswer(dataPager, true)) {
            nextClicked = true;
            mCurrentPager++;
            if (mCurrentPager >= SURVEY_COUNT_TAB_SELL) {
                postAnswer();
            } else {
                getView().setCurrentPagerQuestion(mCurrentPager);
            }
        }
    }

    @Override
    public void handleStart() {
        getView().startAnswer();
    }

    @Override
    public void handlePrevious(SurveyItemFragment currentPage) {
        saveDataAnswer(currentPage, false);
        int temp = mCurrentPager;
        mCurrentPager--;
        if (mCurrentPager < 0) {
            mCurrentPager = temp;
            handleBack();
        } else {
            getView().setCurrentPagerQuestion(mCurrentPager);
        }
    }

    @Override
    public void handlePagerChange(int i, SurveyItemFragment currentPage, SurveyItemFragment dataPage) {
        if (!nextClicked) {
            saveDataAnswer(dataPage, false);
        }
        mCurrentPager = i;
        changeStatePosition(i - 1);
        changeStatePosition(i + 1);
        checkEnableSwipe(currentPage);
        if (i == SURVEY_COUNT_TAB - 1) {
            getView().showSuccessCpnbNext();
        } else {
            getView().showNormalCpnbNext();
        }
        nextClicked = false;
    }

    @Override
    public int getCurrentPage() {
        return mCurrentPager;
    }

    @Override
    public void handleBack() {
        if (mCurrentPager == -1) {
            getView().finishSurvey();
        } else {
            getView().displayConfirmDialog();
        }
    }

    @Override
    public void handleIndicatorClicked(int position) {
        if (!mListFinishQuestion.containsKey(position) && !mListFinishQuestion.containsKey(position - 1)) {
            getView().showErrorIndicatorClick();
            return;
        }
        if (position == mCurrentPager) return;
        changeStatePosition(mCurrentPager);
        getView().setCurrentPagerQuestion(position);
    }

    @Override
    public void savePref() {
        Logger.e("Start save pref");
        LocalDataManager.getInstance(getContext()).saveSurvey(new SaveSurvey(mListFinishQuestion, mOrdinalType, mCountFinish));
    }

    @Override
    public void loadSaveData() {
        SaveSurvey saveSurvey = LocalDataManager.getInstance(getContext()).getSurvey(mOrdinalType);
        if (saveSurvey != null && saveSurvey.answerSurveyList != null) {
            mListFinishQuestion = saveSurvey.answerSurveyList;
            mCountFinish = saveSurvey.mFinishCount;
        }
    }

    @Override
    public void checkEnableSwipe(SurveyItemFragment surveyItemFragment) {
        Logger.e("AAAAAAA: " + mCountFinish + "?" + mCurrentPager);
        if (mCurrentPager <= mCountFinish) {
            getView().changeEnableSwipe(true);
        } else if (surveyItemFragment != null) {
            getView().changeEnableSwipe(surveyItemFragment.checkListAnswer());
        } else {
            getView().changeEnableSwipe(false);
        }
    }

    @Override
    public void handleSwipeRight(boolean enableSwipe) {
        if (!enableSwipe) {
            getView().showErrorAnswerDialog();
        }
    }

    private void changeStatePosition(int i) {
        if (i < 0) return;
        if (mListFinishQuestion.containsKey(i)) {
            getView().setSelectedIndicator(i);
        } else {
            getView().setNormalIndicator(i);
        }
    }

    private boolean saveDataAnswer(SurveyItemFragment pager, boolean showException) {
        if (pager != null) {
            Pair<Integer, List<AnswerSurvey>> result = pager.getAnswer(showException);
            if (result != null) {
                if (result.second.isEmpty()) {
                    return false;
                }
                Logger.e("OnSave Data Answer: " + result.first + "/" + showException);
                int i = result.first;
                if (mCountFinish < i) mCountFinish = i;
                mListFinishQuestion.put(result.first, result.second);
                return true;
            }
        }
        return false;
    }


    private void changeSelectedFinish() {
        for (int i = 0; i < mCountFinish; i++) {
            getView().setSelectedIndicator(i);
        }
    }

    private void postAnswer() {
        RESTManager.getInstance().postSurvey(mTokenSurvey, createPostAnswer(), new IRequestListener<SimpleResponse>() {
            @Override
            public void onCompleted(SimpleResponse data) {
                super.onCompleted(data);
                if (data.isSuccess()) {
                    LocalDataManager.getInstance(getContext()).deleteSurvey(mOrdinalType);
                    getView().displaySuccessSurvey();
                }
            }

            @Override
            public void onFailed(Throwable throwable) {
                super.onFailed(throwable);
                mCurrentPager--;
            }
        });
    }

    private List<String> createPostAnswer() {
        List<String> answer = new ArrayList<>();
        for (HashMap.Entry entry : mListFinishQuestion.entrySet()) {
            List<AnswerSurvey> list = (List<AnswerSurvey>) entry.getValue();
            for (AnswerSurvey item : list) {
                answer.add(item.answer);
            }
        }
        Logger.e("List post: " + answer.toString());
        return answer;
    }

    private void loadAgency() {
        countLoadApi++;
        if (SessionDataManager.getInstance().getAgencies() == null) {
            RESTManager.getInstance().getAgencies(new IRequestListener<AgenciesResponse>(httpRequestOption) {
                @Override
                public void onCompleted(AgenciesResponse data) {
                    super.onCompleted(data);
                    if (data.isSuccess() && data.getData() != null) {
                        SessionDataManager.getInstance().setAgencies(data.getData());
                        handleSuccessLoadApi();
                    } else {
                        handleErrorLoadApi();
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                    handleErrorLoadApi();
                }
            });
        } else {
            handleSuccessLoadApi();
        }
    }

    private void loadCar() {
        countLoadApi++;
        if (SessionDataManager.getInstance().getCars() == null) {
            RESTManager.getInstance().getCars(new IRequestListener<CarsResponse>(httpRequestOption) {
                @Override
                public void onCompleted(CarsResponse data) {
                    super.onCompleted(data);
                    if (data.isSuccess() && data.getData() != null) {
                        SessionDataManager.getInstance().setCars(data.getData());
                        handleSuccessLoadApi();
                    } else {
                        handleErrorLoadApi();
                    }
                }
            });
        } else {
            handleSuccessLoadApi();
        }
    }

    private void handleSuccessLoadApi() {
        countLoadApi--;
        if (countLoadApi == 0) {
            getView().hideDialogWait();
            if (mOrdinalType != -1) {
                loadSaveData();
                SurveyType surveyType = SurveyType.values()[mOrdinalType];
                int count = 0;
                if (surveyType == SurveyType.NEW_CAR) {
                    count = SURVEY_COUNT_TAB;
                } else {
                    count = SURVEY_COUNT_TAB_SELL;
                }

                //Display viewpager
                QuestionFactory factory = new QuestionFactoryImpl();
                factory.createInstance(getContext(), count, mOrdinalType);
                getView().displayViewPager(AppConstants.SURVEY_COUNT_TAB, factory.getMapQuestion(), mListFinishQuestion, mCountFinish == -1 ? 0 : mCountFinish);
                changeSelectedFinish();
            } else {
                //todo error load type
            }
        }
    }

    private void handleErrorLoadApi() {
        countLoadApi = 0;
        getView().hideDialogWait();
    }
}
