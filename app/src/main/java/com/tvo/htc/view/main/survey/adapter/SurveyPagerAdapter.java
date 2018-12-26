package com.tvo.htc.view.main.survey.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Pair;

import com.tvo.htc.model.AnswerSurvey;
import com.tvo.htc.model.Question;
import com.tvo.htc.view.main.survey.item.SurveyItemFragment;
import com.tvo.htc.view.main.survey.listener.OnRequireCallBack;
import com.tvo.htc.view.main.survey.question.BaseQuestion.HeaderType;

import java.util.HashMap;
import java.util.List;

/**
 * Create by Ngocji on 10/26/2018
 **/


public class SurveyPagerAdapter extends FragmentStatePagerAdapter {
    private int mTabCount;
    private HashMap<Integer, Pair<HeaderType, List<Question>>> mMapQuestion;
    private HashMap<Integer, List<AnswerSurvey>> mMapAnswer;
    private OnRequireCallBack onRequireCallBack;

    public SurveyPagerAdapter(FragmentManager fm, int mTabCount, HashMap<Integer, Pair<HeaderType, List<Question>>> mapQuestion, HashMap<Integer, List<AnswerSurvey>> mapAnswer, OnRequireCallBack onRequireCallBack) {
        super(fm);
        this.mTabCount = mTabCount;
        this.mMapQuestion = mapQuestion;
        this.onRequireCallBack = onRequireCallBack;
        this.mMapAnswer = mapAnswer;
    }

    @Override
    public Fragment getItem(int i) {
        return SurveyItemFragment.newInstance(mMapQuestion.get(i), mMapAnswer.get(i), i, onRequireCallBack);
    }

    @Override
    public int getCount() {
        return mTabCount;
    }
}
