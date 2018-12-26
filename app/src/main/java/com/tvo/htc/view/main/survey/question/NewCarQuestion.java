package com.tvo.htc.view.main.survey.question;

import android.content.Context;
import android.text.InputType;
import android.util.Pair;

import com.tvo.htc.R;
import com.tvo.htc.model.Question;
import com.tvo.htc.model.SessionDataManager;

import java.util.ArrayList;
import java.util.List;

import static com.tvo.htc.view.component.CpnSurvey.Type.CHECKBOX;
import static com.tvo.htc.view.component.CpnSurvey.Type.EMAIL;
import static com.tvo.htc.view.component.CpnSurvey.Type.FULL_NAME;
import static com.tvo.htc.view.component.CpnSurvey.Type.LEVEL;
import static com.tvo.htc.view.component.CpnSurvey.Type.PHONE;
import static com.tvo.htc.view.component.CpnSurvey.Type.RADIO;
import static com.tvo.htc.view.component.CpnSurvey.Type.SPINNER;
import static com.tvo.htc.view.component.CpnSurvey.Type.TEXT;
import static com.tvo.htc.view.component.CpnSurvey.Type.TEXT_AREA;
import static com.tvo.htc.view.main.survey.question.BaseQuestion.HeaderType.NONE;
import static com.tvo.htc.view.main.survey.question.BaseQuestion.HeaderType.REQUIRE;
import static com.tvo.htc.view.main.survey.question.BaseQuestion.HeaderType.TIP_LEVEL;

/**
 * Create by Ngocji on 11/21/2018
 **/


public class NewCarQuestion extends BaseQuestion {
    public NewCarQuestion(Context mContext) {
        super(mContext);
    }

    public Pair<HeaderType, List<Question>> loadQuestion0() {
        List<Question> list = new ArrayList<>();
        Question question = new Question();
        question.setType(RADIO);
        question.setQuestion(getString(R.string.question_sell_0_0_question));
        question.setAnswers(getListString(R.array.question_sell_0_0_answer));
        list.add(question);

        question = new Question();
        question.setType(SPINNER);
        question.setRequire(true);
        question.setQuestion(getString(R.string.question_sell_0_1_question));
        question.setAnswers(convertListObjectToString(SessionDataManager.getInstance().getCars()));
        list.add(question);

        question = new Question();
        question.setType(RADIO);
        question.setQuestion(getString(R.string.question_sell_0_2_question));
        question.setAnswers(getListString(R.array.question_sell_0_2_answer));
        list.add(question);

        question = new Question();
        question.setType(RADIO);
        question.setQuestion(getString(R.string.question_sell_0_3_question));
        question.setAnswers(getListString(R.array.question_sell_0_3_answer));
        list.add(question);

        question = new Question();
        question.setType(RADIO);
        question.setQuestion(getString(R.string.question_sell_0_4_question));
        question.setAnswers(getListString(R.array.question_sell_0_4_answer));
        list.add(question);

        question = new Question();
        question.setType(RADIO);
        question.setQuestion(getString(R.string.question_sell_0_5_question));
        question.setAnswers(getListString(R.array.question_sell_0_5_answer));
        list.add(question);

        question = new Question();
        question.setType(TEXT);
        question.setQuestion(getString(R.string.question_sell_0_6_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setRequire(true);
        question.setQuestion(getString(R.string.question_sell_0_7_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setRequire(true);
        question.setQuestion(getString(R.string.question_sell_0_8_question));
        list.add(question);

        return Pair.create(REQUIRE, list);
    }

    public Pair<HeaderType, List<Question>> loadQuestion1() {
        List<Question> list = new ArrayList<>();
        Question question = new Question();
        question.setType(LEVEL);
        question.setRequire(true);
        question.setQuestion(getString(R.string.question_sell_1_0_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_1_1_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_1_2_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_1_3_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_1_4_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_1_5_question));
        list.add(question);
        return Pair.create(TIP_LEVEL, list);
    }

    public Pair<HeaderType, List<Question>> loadQuestion2() {
        List<Question> list = new ArrayList<>();
        Question question = new Question();
        question.setType(LEVEL);
        question.setRequire(true);
        question.setQuestion(getString(R.string.question_sell_2_0_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_2_1_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_2_2_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_2_3_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_2_4_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_2_5_question));
        list.add(question);
        return Pair.create(TIP_LEVEL, list);

    }

    public Pair<HeaderType, List<Question>> loadQuestion3() {
        List<Question> list = new ArrayList<>();
        Question question = new Question();
        question.setType(LEVEL);
        question.setRequire(true);
        question.setQuestion(getString(R.string.question_sell_3_0_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setRequire(true);
        question.setQuestion(getString(R.string.question_sell_3_1_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setRequire(true);
        question.setQuestion(getString(R.string.question_sell_3_2_question));
        question.setSubAnswer(getListString(R.array.question_sell_3_2_sub_answer));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_3_3_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_3_4_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_3_5_question));
        list.add(question);
        return Pair.create(TIP_LEVEL, list);
    }

    public Pair<HeaderType, List<Question>> loadQuestion4() {
        List<Question> list = new ArrayList<>();
        Question question = new Question();
        question.setType(LEVEL);
        question.setRequire(true);
        question.setQuestion(getString(R.string.question_sell_4_0_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_4_1_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_4_2_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_4_3_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_4_4_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_4_5_question));
        list.add(question);
        return Pair.create(TIP_LEVEL, list);
    }

    public Pair<HeaderType, List<Question>> loadQuestion5() {
        List<Question> list = new ArrayList<>();
        Question question = new Question();
        question.setType(LEVEL);
        question.setRequire(true);
        question.setQuestion(getString(R.string.question_sell_5_0_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_5_1_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_5_2_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_5_3_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_5_4_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_5_5_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_5_6_question));
        list.add(question);
        return Pair.create(TIP_LEVEL, list);
    }

    public Pair<HeaderType, List<Question>> loadQuestion6() {
        List<Question> list = new ArrayList<>();
        Question question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_6_0_question));
        question.setDescription(getString(R.string.question_sell_6_0_desc));
        question.setSubAnswer(getListString(R.array.question_sell_6_0_sub_answer));
        list.add(question);

        question = new Question();
        question.setType(SPINNER);
        question.setQuestion(getString(R.string.question_sell_6_1_question));
        question.setAnswers(getListString(R.array.question_sell_6_1_answer));
        list.add(question);

        question = new Question();
        question.setType(SPINNER);
        question.setQuestion(getString(R.string.question_sell_6_2_question));
        question.setDescription(getString(R.string.question_sell_6_2_desc));
        question.setAnswers(getListString(R.array.question_sell_6_2_answer));
        list.add(question);

        question = new Question();
        question.setType(SPINNER);
        question.setQuestion(getString(R.string.question_sell_6_3_question));
        question.setAnswers(getListString(R.array.question_sell_6_3_answer));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_6_4_question));
        question.setDescription(getString(R.string.question_sell_6_4_desc));
        list.add(question);

        question = new Question();
        question.setType(TEXT_AREA);
        question.setQuestion(getString(R.string.question_sell_6_5_question));
        list.add(question);
        return Pair.create(NONE, list);

    }

    public Pair<HeaderType, List<Question>> loadQuestion7() {
        List<Question> list = new ArrayList<>();
        Question question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_7_0_question));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setRequire(true);
        question.setQuestion(getString(R.string.question_sell_7_1_question));
        list.add(question);


        question = new Question();
        question.setType(LEVEL);
        question.setQuestion(getString(R.string.question_sell_7_2_question));
        list.add(question);


        question = new Question();
        question.setType(RADIO);
        question.setQuestion(getString(R.string.question_sell_7_3_question));
        question.setAnswers(getListString(R.array.question_sell_7_3_answer));
        list.add(question);

        question = new Question();
        question.setType(LEVEL);
        question.setRequire(true);
        question.setDescription(getString(R.string.question_sell_7_4_desc));
        question.setQuestion(getString(R.string.question_sell_7_4_question));
        list.add(question);

        question = new Question();
        question.setType(TEXT_AREA);
        question.setQuestion(getString(R.string.question_sell_7_5_question));
        list.add(question);
        return Pair.create(REQUIRE, list);
    }

    public Pair<HeaderType, List<Question>> loadQuestion8() {
        List<Question> list = new ArrayList<>();
        Question question = new Question();
        question.setType(RADIO);
        question.setQuestion(getString(R.string.question_sell_8_0_question));
        question.setAnswers(getListString(R.array.question_sell_8_0_answer));
        list.add(question);

        question = new Question();
        question.setType(RADIO);
        question.setQuestion(getString(R.string.question_sell_8_1_question));
        question.setAnswers(getListString(R.array.question_sell_8_1_answer));
        list.add(question);

        question = new Question();
        question.setType(FULL_NAME);
        question.setRequire(true);
        if (hasPrefData()) {
            question.setDataPref(mLoginResponse.getData().getName());
        }
        question.setDescription(getString(R.string.question_sell_8_2_desc));
        question.setQuestion(getString(R.string.question_sell_8_2_question));
        list.add(question);

        question = new Question();
        question.setType(TEXT);
        question.setRequire(true);
        if (hasPrefData()) {
            question.setDataPref(mLoginResponse.getData().getAddress());
        }
        question.setDescription(getString(R.string.question_sell_8_3_desc));
        question.setQuestion(getString(R.string.question_sell_8_3_question));
        list.add(question);

        question = new Question();
        question.setType(EMAIL);
        question.setRequire(true);
        if (hasPrefData()) {
            question.setDataPref(mLoginResponse.getData().getEmailAddress());
        }
        question.setQuestion(getString(R.string.question_sell_8_4_question));
        list.add(question);

        question = new Question();
        question.setType(PHONE);
        question.setRequire(true);
        if (hasPrefData()) {
            question.setDataPref(mLoginResponse.getData().getPhoneNumber());
        }
        question.setQuestion(getString(R.string.question_sell_8_5_question));
        question.setInputType(InputType.TYPE_CLASS_NUMBER);
        list.add(question);
        return Pair.create(REQUIRE, list);
    }

    public Pair<HeaderType, List<Question>> loadQuestion9() {
        List<Question> list = new ArrayList<>();
        Question question = new Question();
        question.setType(SPINNER);
        question.setQuestion(getString(R.string.question_sell_9_0_question));
        question.setAnswers(getListString(R.array.question_sell_9_0_answer));
        list.add(question);

        question = new Question();
        question.setType(CHECKBOX);
        question.setQuestion(getString(R.string.question_sell_9_1_question));
        question.setAnswers(getListString(R.array.question_sell_9_1_answer));
        question.setDescription(getString(R.string.question_sell_9_1_desc));
        question.setMultiSelection(true);
        list.add(question);
        return Pair.create(REQUIRE, list);
    }


}
