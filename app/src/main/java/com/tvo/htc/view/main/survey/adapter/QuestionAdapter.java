package com.tvo.htc.view.main.survey.adapter;

import android.content.Context;
import android.view.View;

import com.tvo.htc.R;
import com.tvo.htc.model.AnswerSurvey;
import com.tvo.htc.model.Question;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;
import com.tvo.htc.view.component.CpnSurvey;
import com.tvo.htc.view.main.survey.listener.AnswerCallBack;
import com.tvo.htc.view.main.survey.listener.OnDataChangeListener;
import com.tvo.htc.view.main.survey.listener.OnListAnswerChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create by Ngocji on 12/3/2018
 **/


public class QuestionAdapter extends BaseAdapter<Question, QuestionAdapter.ViewHolder> implements AnswerCallBack {
    ArrayList<AnswerSurvey> listAnswer;
    OnListAnswerChangeListener onListAnswerChangeListener;

    public QuestionAdapter(Context context, List<Question> items, ArrayList<AnswerSurvey> listAnswer, OnListAnswerChangeListener onListAnswerChangeListener) {
        super(context, items);
        this.listAnswer = listAnswer;
        this.onListAnswerChangeListener = onListAnswerChangeListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_question;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view, this);
    }

    @Override
    protected void bindView(ViewHolder holder, Question item, int position) {
        holder.cpnSurvey.createViewQuestion(item);
        if (position >= 0 && position < listAnswer.size())
            holder.cpnSurvey.updatePrefData(listAnswer.get(position));
    }

    @Override
    public void answerChange(AnswerSurvey answerSurvey, int position) {
        listAnswer.set(position, answerSurvey);
        onListAnswerChangeListener.onListAnswerChanged(listAnswer);
    }

    static class ViewHolder extends BaseViewHolder implements OnDataChangeListener {
        @BindView(R.id.cpnSurvey)
        CpnSurvey cpnSurvey;
        AnswerCallBack listener;

        public ViewHolder(View view, AnswerCallBack listener) {
            super(view);
            this.listener = listener;
            cpnSurvey.setOnDataChangeListener(this);
        }

        @Override
        public void onDataChanged() {
            listener.answerChange(cpnSurvey.getData(), getAdapterPosition());
        }
    }
}
