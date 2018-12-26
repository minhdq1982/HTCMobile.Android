package com.tvo.htc.view.main.survey.item;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.tvo.htc.R;
import com.tvo.htc.model.AnswerSurvey;
import com.tvo.htc.model.Question;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseFragment;
import com.tvo.htc.view.main.survey.adapter.QuestionAdapter;
import com.tvo.htc.view.main.survey.listener.OnListAnswerChangeListener;
import com.tvo.htc.view.main.survey.listener.OnRequireCallBack;
import com.tvo.htc.view.main.survey.question.BaseQuestion;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class SurveyItemFragment extends BaseFragment<SurveyItemContract.Presenter> implements SurveyItemContract.View, OnListAnswerChangeListener {
    private static final String KEY_DATA_QUESTION = "KEY_DATA_VIEW_QUESTION";
    private static final String KEY_DATA_ANSWER = "KEY_DATA_ANSWER";
    private static final String KEY_POSITION = "KEY_POSITION";

    @BindView(R.id.tvTipLevel)
    TextView tvTipLevel;
    @BindView(R.id.tvRequire)
    TextView tvRequire;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @NonNull
    private OnRequireCallBack requireCallBack;

    private boolean enableChange;

    public static SurveyItemFragment newInstance(Pair<BaseQuestion.HeaderType, List<Question>> question, List<AnswerSurvey> answers, int position, OnRequireCallBack onRequireCallBack) {
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putString(KEY_DATA_QUESTION, Utils.questionToString(question));
        args.putString(KEY_DATA_ANSWER, Utils.listToString(answers));
        SurveyItemFragment fragment = new SurveyItemFragment();
        fragment.setArguments(args);
        fragment.requireCallBack = onRequireCallBack;
        return fragment;
    }


    @Override
    protected SurveyItemContract.Presenter createPresenterInstance() {
        return new SurveyItemPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_survey_item;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onViewCreatedBaseFragment(View view, Bundle savedInstanceState) {
        super.onViewCreatedBaseFragment(view, savedInstanceState);
        getPresenter().loadQuestion(getArguments().getString(KEY_DATA_QUESTION), getArguments().getString(KEY_DATA_ANSWER), getArguments().getInt(KEY_POSITION, 0));
    }

    @Override
    public void hideHeader() {
        tvRequire.setVisibility(View.GONE);
        tvTipLevel.setVisibility(View.GONE);
    }

    @Override
    public void showRequireHeader() {
        tvRequire.setVisibility(View.VISIBLE);
        tvTipLevel.setVisibility(View.GONE);
    }

    @Override
    public void showLevelHeader() {
        tvRequire.setVisibility(View.VISIBLE);
        tvTipLevel.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayListQuestion(List<Question> listQuestion, ArrayList<AnswerSurvey> listAnswer) {
        QuestionAdapter adapter = new QuestionAdapter(getContext(), listQuestion, listAnswer, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showErrorPhoneValidate() {
        showMessage(getString(R.string.make_appointment_error_phone));
    }

    @Override
    public void showErrorName() {
        showMessage(getString(R.string.make_appointment_error_name));
    }

    @Override
    public void showErrorEmail() {
        showMessage(getString(R.string.make_appointment_error_email));
    }

    @Override
    public void showErrorDate() {
        showMessage(getString(R.string.survey_error_date_format));
    }

    @Override
    public void showErrorRequire() {
        showMessage(getString(R.string.survey_error_answer_require));
    }

    public Pair<Integer, List<AnswerSurvey>> getAnswer() {
        return getAnswer(true);
    }

    public Pair<Integer, List<AnswerSurvey>> getAnswer(boolean showException) {
        if (getPresenter() != null) {
            return getPresenter().getAnswerSurvey(showException);
        }
        return null;
    }

    @Override
    public void onListAnswerChanged(List<AnswerSurvey> list) {
        if (isVisible() && enableChange)
            requireCallBack.onEnableSwipe(getPresenter().checkListAnswerRequire());
    }

    public void changeEnable(boolean enableChange) {
        this.enableChange = enableChange;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        changeEnable(menuVisible);
    }

    public boolean checkListAnswer() {
        return getPresenter().checkListAnswerRequire();
    }
}
