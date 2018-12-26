package com.tvo.htc.view.main.survey.item;

import android.content.Context;
import android.util.Pair;

import com.android.lib.util.LibUtils;
import com.android.lib.util.Logger;
import com.google.gson.reflect.TypeToken;
import com.tvo.htc.model.AnswerSurvey;
import com.tvo.htc.model.Question;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BasePresenter;
import com.tvo.htc.view.component.CpnSurvey;
import com.tvo.htc.view.main.survey.question.BaseQuestion.HeaderType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class SurveyItemPresenter extends BasePresenter<SurveyItemContract.View> implements SurveyItemContract.Presenter {
    public SurveyItemPresenter(Context context) {
        super(context);
    }

    private Pair<HeaderType, List<Question>> mPairQuestion;
    private ArrayList<AnswerSurvey> mListAnswer = new ArrayList<>();
    private int mPosition;

    @Override
    public void loadQuestion(String questionString, String answerString, int position) {
        mPosition = position;
        mPairQuestion = Utils.stringToQuestion(questionString);
        if (mPairQuestion != null) {
            handleHeaderType(mPairQuestion.first);
            initializeListAnswer(answerString, mPairQuestion.second);
            getView().displayListQuestion(mPairQuestion.second, mListAnswer);
        } else {
            //todo show empty question
        }
    }

    @Override
    public boolean checkListAnswerRequire() {
        Logger.e("Check answer: "+mPosition);
        if (mPairQuestion != null) {
            List<Question> listQuestion = mPairQuestion.second;
            for (int i = 0; i < listQuestion.size(); i++) {
                Question question = listQuestion.get(i);
                if (question.isRequire()) {
                    AnswerSurvey answerSurvey = mListAnswer.get(i);
                    if (answerSurvey.answer.isEmpty()) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Pair<Integer, List<AnswerSurvey>> getAnswerSurvey(boolean showException) {
        if (mPairQuestion != null) {
            List<Question> listQuestion = mPairQuestion.second;
            for (int i = 0; i < listQuestion.size(); i++) {
                Question question = listQuestion.get(i);
                if (question.isRequire()) {
                    String answer = mListAnswer.get(i).answer;
                    if (handleExceptionAnswer(question.getType(), answer, showException)) {
                        return null;
                    }
                }
            }
            Logger.e("Answer:---> " + mListAnswer.size());
            return Pair.create(mPosition, mListAnswer);
        }
        return null;
    }

    private void handleHeaderType(HeaderType type) {
        switch (type) {
            case NONE:
                getView().hideHeader();
                break;
            case REQUIRE:
                getView().showRequireHeader();
                break;
            case TIP_LEVEL:
                getView().showLevelHeader();
                break;
        }
    }


    private boolean handleExceptionAnswer(CpnSurvey.Type type, String answer, boolean showException) {
        if (answer.isEmpty()) {
            if (showException) getView().showErrorRequire();
            return true;
        }
        switch (type) {
            case PHONE:
                if (!Utils.isValidPhoneNumber(answer)) {
                    if (showException) getView().showErrorPhoneValidate();
                    return true;
                }
                break;
            case FULL_NAME:
                if (!Utils.isValidName(answer)) {
                    if (showException) getView().showErrorName();
                    return true;
                }
                break;
            case EMAIL:
                if (!Utils.isValidEmail(answer)) {
                    if (showException) getView().showErrorEmail();
                    return true;
                }
                break;
            case DATE:
                if (!LibUtils.isValidTime(answer)) {
                    if (showException) getView().showErrorDate();
                    return true;
                }
                break;
        }
        return false;
    }

    private void initializeListAnswer(String answer, List<Question> questions) {
        Type type = new TypeToken<List<AnswerSurvey>>() {
        }.getType();
        List<AnswerSurvey> list = (List<AnswerSurvey>) Utils.stringToList(answer, type);
        if (list == null) {
            for (int i = 0; i < questions.size(); i++) {
                Question item = questions.get(i);
                AnswerSurvey answerSurvey = new AnswerSurvey(i, item.getQuestion(), item.getDefaultAnswer(), null);
                mListAnswer.add(answerSurvey);
            }
        } else {
            mListAnswer.addAll(list);
        }
    }
}
