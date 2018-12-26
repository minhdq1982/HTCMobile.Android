package com.tvo.htc.view.main.survey.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;

import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Create by Ngocji on 10/29/2018
 **/


public class RadioAdapter extends BaseAdapter<String, RadioAdapter.ViewHolder> {
    int mPositionSelected = -1;

    public RadioAdapter(Context context, List<String> items, int mPositionSelected) {
        super(context, items);
        this.mPositionSelected = mPositionSelected;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_survey_radio;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, String item, int position) {
        holder.radioButton.setText(item);
        if (position == mPositionSelected) {
            holder.radioButton.setChecked(true);
        } else {
            holder.radioButton.setChecked(false);
        }
    }

    public String getAnswer() {
        if (mPositionSelected != -1) {
            return getDisplayItems().get(mPositionSelected);
        } else {
            return "";
        }
    }

    public int getSelectedPosition() {
        return mPositionSelected;
    }

    public boolean hasSelected() {
        return mPositionSelected != -1;
    }


    public void changeSelect(int position) {
        if (mPositionSelected != position) {
            mPositionSelected = position;
            notifyDataSetChanged();
        }
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.radioButton)
        RadioButton radioButton;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
