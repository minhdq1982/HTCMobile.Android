package com.tvo.htc.view.main.survey.factory;

import android.content.Context;
import android.util.Pair;

import com.android.lib.model.response.SurveyResponse;
import com.android.lib.util.Logger;
import com.tvo.htc.model.Question;
import com.tvo.htc.view.main.survey.question.BaseQuestion;
import com.tvo.htc.view.main.survey.question.NewCarQuestion;
import com.tvo.htc.view.main.survey.question.RepairProtectionQuestion;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by Ngocji on 11/21/2018
 **/


public class QuestionFactoryImpl implements QuestionFactory {
    Context mContext;
    int mCountTab;
    int mOrdinalType;
    HashMap<Integer, Pair<BaseQuestion.HeaderType, List<Question>>> mMapQuestion;
    StringBuilder builder = new StringBuilder();

    @Override
    public void createInstance(Context context, int countTab, int ordinalType) {
        mContext = context;
        mCountTab = countTab;
        mOrdinalType = ordinalType;
        mMapQuestion = new HashMap<>();
        init();
    }

    @Override
    public HashMap<Integer, Pair<BaseQuestion.HeaderType, List<Question>>> getMapQuestion() {
        return mMapQuestion;
    }

    public StringBuilder getBuilder() {
        return builder;
    }

    private void init() {
        Object obj = createObjectQuestion(mOrdinalType);
        if (obj != null) {
            for (int i = 0; i < mCountTab; i++) {
                createInvokeMethod(obj, i);
            }
        }
        int count = 1;
        for (Map.Entry entry : mMapQuestion.entrySet()) {
            List<Question> list = ((Pair<BaseQuestion.HeaderType, List<Question>>) entry.getValue()).second;
            for (int i = 0; i < list.size(); i++) {
                builder.append(count + ": " + list.get(i).getQuestion() + "\n\n");
                count++;
            }
        }
    }

    private Object createObjectQuestion(int ordinalType) {
        switch (SurveyResponse.SurveyType.values()[ordinalType]) {
            case NEW_CAR:
                return new NewCarQuestion(mContext);
            case REPAIR_PROTECTION:
                return new RepairProtectionQuestion(mContext);
        }
        return null;
    }

    private void createInvokeMethod(Object obj, int i) {
        try {
            Method method = obj.getClass().getMethod("loadQuestion" + i);
            method.setAccessible(true);
            mMapQuestion.put(i, (Pair<BaseQuestion.HeaderType, List<Question>>) method.invoke(obj));
        } catch (Exception e) {
            Logger.e("error method" + e.toString());
        }
    }
}
