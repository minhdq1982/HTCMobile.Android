package com.tvo.htc.view.main.survey;

import android.util.Pair;

import com.tvo.htc.model.AnswerSurvey;
import com.tvo.htc.model.Question;
import com.tvo.htc.view.BaseContract;
import com.tvo.htc.view.main.survey.item.SurveyItemFragment;
import com.tvo.htc.view.main.survey.question.BaseQuestion;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface SurveyContract {
    interface View extends BaseContract.View {
        void displayConfirmDialog();

        void setCurrentPagerQuestion(int currentPagerQuestion);

        void finishSurvey();

        void displaySuccessSurvey();

        void showSuccessCpnbNext();

        void showNormalCpnbNext();

        void showErrorAnswerDialog();

        void startAnswer();


        void displayViewPager(int tabCount, HashMap<Integer, Pair<BaseQuestion.HeaderType, List<Question>>> mapQuestion, HashMap<Integer, List<AnswerSurvey>> mapAnswer, int currentItem);

        void setSelectedIndicator(int i);

        void setNormalIndicator(int i);

        void showErrorIndicatorClick();

        void changeEnableSwipe(boolean enable);

        void hideDialogWait();

        void showDialogWait();

        void showErrorAvailable();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void handleNext(SurveyItemFragment surveyItemFragment);

        void handleBack();

        void handlePrevious(SurveyItemFragment currentPage);

        void handlePagerChange(int i, SurveyItemFragment currentPage, SurveyItemFragment dataPage);

        void handleStart();

        void handleIndicatorClicked(int position);

        void loadData(int type, String token);

        int getCurrentPage();

        void savePref();

        void loadSaveData();

        void checkEnableSwipe(SurveyItemFragment currentPage);

        void handleSwipeRight(boolean enableSwipe);
    }
}
