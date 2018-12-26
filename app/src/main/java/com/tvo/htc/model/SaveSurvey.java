package com.tvo.htc.model;

import java.util.HashMap;
import java.util.List;

/**
 * Create by Ngocji on 11/30/2018
 **/


public class SaveSurvey {
    public HashMap<Integer, List<AnswerSurvey>> answerSurveyList;
    public int ordinalType;
    public int mFinishCount;

    public SaveSurvey(HashMap<Integer, List<AnswerSurvey>> answerSurveyList, int ordinalType, int mFinishCount) {
        this.answerSurveyList = answerSurveyList;
        this.ordinalType = ordinalType;
        this.mFinishCount = mFinishCount;
    }

    public HashMap<Integer, List<AnswerSurvey>> getAnswerSurveyList() {
        return answerSurveyList;
    }

    public void setAnswerSurveyList(HashMap<Integer, List<AnswerSurvey>> answerSurveyList) {
        this.answerSurveyList = answerSurveyList;
    }

    public int getOrdinalType() {
        return ordinalType;
    }

    public void setOrdinalType(int ordinalType) {
        this.ordinalType = ordinalType;
    }

    public int getmFinishCount() {
        return mFinishCount;
    }

    public void setmFinishCount(int mFinishCount) {
        this.mFinishCount = mFinishCount;
    }
}
