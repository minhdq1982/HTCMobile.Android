package com.tvo.htc.view.main.buycar.select_car.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.lib.model.response.SelectCarResponse;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class SelectCarOptionAdapter extends BaseAdapter<SelectCarResponse.Item.Answer, SelectCarOptionAdapter.ViewHolder> {

    private int mSelectedPosition = -1;
    private List<SelectCarResponse.Item.Answer> mItem;

    private int mQuestionNo;
    private OptionCallback mCallback;

    public SelectCarOptionAdapter(Context context, List<SelectCarResponse.Item.Answer> items, int questionNo, OptionCallback callback) {
        super(context, items);
        mCallback = callback;
        mItem = items;
        mQuestionNo = questionNo;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_select_car_radio;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, SelectCarResponse.Item.Answer item, int position) {
        holder.txtRadio.setText(item.getAnswer());
        if (position == mSelectedPosition) {
            holder.btnRadio.setChecked(true);
        } else {
            holder.btnRadio.setChecked(false);
        }
    }

    @Override
    protected void onItemClick(ViewHolder holder, int position) {
        super.onItemClick(holder, position);

        if (mSelectedPosition != -1) {
            notifyItemChanged(mSelectedPosition);
        }

        mSelectedPosition = position;
        notifyItemChanged(mSelectedPosition);

        mCallback.onCallback(mQuestionNo, mItem.get(position).getAnswerLetter());
    }

    interface OptionCallback {
        void onCallback(int questionNo, String questionLetter);
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.btnRadio)
        RadioButton btnRadio;
        @BindView(R.id.txtRadio)
        TextView txtRadio;

        public ViewHolder(View view) {
            super(view);
        }
    }
}