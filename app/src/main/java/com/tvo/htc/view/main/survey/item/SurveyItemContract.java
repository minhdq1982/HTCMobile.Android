package com.tvo.htc.view.main.survey.item;

import android.util.Pair;

import com.tvo.htc.model.AnswerSurvey;
import com.tvo.htc.model.Question;
import com.tvo.htc.view.BaseContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface SurveyItemContract {
    interface View extends BaseContract.View {

        void displayListQuestion(List<Question> listQuestion, ArrayList<AnswerSurvey> listAnswer);

        void showErrorPhoneValidate();

        void showErrorName();

        void showErrorEmail();

        void showErrorDate();

        void showErrorRequire();

        void hideHeader();

        void showRequireHeader();

        void showLevelHeader();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadQuestion(String questionString, String answerString, int position);

        boolean checkListAnswerRequire();

        Pair<Integer, List<AnswerSurvey>> getAnswerSurvey(boolean showException);
    }
}
