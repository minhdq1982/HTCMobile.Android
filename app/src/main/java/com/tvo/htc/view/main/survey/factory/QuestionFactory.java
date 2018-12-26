package com.tvo.htc.view.main.survey.factory;

import android.content.Context;
import android.util.Pair;

import com.tvo.htc.model.Question;
import com.tvo.htc.view.main.survey.question.BaseQuestion;

import java.util.HashMap;
import java.util.List;

/**
 * Create by Ngocji on 11/21/2018
 **/

public interface QuestionFactory {
    void createInstance(Context context, int countTab, int ordinalType);

    HashMap<Integer, Pair<BaseQuestion.HeaderType, List<Question>>> getMapQuestion();
}