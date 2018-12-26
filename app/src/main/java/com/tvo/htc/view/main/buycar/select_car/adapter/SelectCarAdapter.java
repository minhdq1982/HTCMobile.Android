package com.tvo.htc.view.main.buycar.select_car.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.lib.model.response.SelectCarResponse;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class SelectCarAdapter extends BaseAdapter<SelectCarResponse.Item, SelectCarAdapter.ViewHolder> implements SelectCarOptionAdapter.OptionCallback {

    private SelectCarOptionAdapter mAdapter;
    private Context mContext;
    private Map<Integer, String> mChooseList;

    public SelectCarAdapter(Context context, List<SelectCarResponse.Item> items) {
        super(context, items);
        mContext = context;
        mChooseList = new HashMap<>();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_select_car;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, SelectCarResponse.Item item, int position) {
        holder.txtTitle.setText(item.getQuestion());
        mAdapter = new SelectCarOptionAdapter(mContext, item.getAnswers(), item.getQuestionNo(), this);
        holder.rvListRatio.setAdapter(mAdapter);
    }

    @Override
    public void onCallback(int questionNo, String questionLetter) {
        String data = questionNo + ":" + questionLetter;
        mChooseList.put(questionNo, data);
    }

    public Map<Integer, String> getListAnswerQuestion() {
        return mChooseList;
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.rvListRatio)
        RecyclerView rvListRatio;

        public ViewHolder(View view) {
            super(view);
        }


    }
}
