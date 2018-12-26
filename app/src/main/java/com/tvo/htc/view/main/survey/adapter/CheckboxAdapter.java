package com.tvo.htc.view.main.survey.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create by Ngocji on 10/25/2018
 **/


public class CheckboxAdapter extends BaseAdapter<String, CheckboxAdapter.ViewHolder> {
    private int mPositionSelected = -1;
    private boolean mMultiSelection;
    private List<Integer> mListSelection;

    public CheckboxAdapter(Context context, List<String> items, boolean multiSelection) {
        super(context, items);
        this.mMultiSelection = multiSelection;
        mListSelection = new ArrayList<>();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_survey_checkbox;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, String item, int position) {
        if (mMultiSelection) {
            if (mListSelection.indexOf(position) != -1) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
        } else {
            if (mPositionSelected != -1 && mPositionSelected == position) {
                holder.chk.setChecked(true);
            } else {
                holder.chk.setChecked(false);
            }
        }

        holder.tvValue.setText(item);
        holder.flCheckbox.setOnClickListener((view) -> {
            mListener.onItemClick(this, view, position);
        });
    }

    public String getAnswer() {
        if (mMultiSelection) {
            StringBuilder string = new StringBuilder();
            for (int i = 0; i < mListSelection.size(); i++) {
                string.append(getDisplayItems().get(mListSelection.get(i)));
                if (i != mListSelection.size() - 1) {
                    string.append(" / ");
                }
            }
            return string.toString();
        } else {
            if (mPositionSelected != -1) {
                return getDisplayItems().get(mPositionSelected);
            } else {
                return "";
            }
        }
    }

    public boolean hasSelected() {
        return mMultiSelection || mPositionSelected != -1;
    }

    public void setSelection(List<Integer> list) {
        mListSelection.clear();
        mListSelection.addAll(list);
        notifyDataSetChanged();
    }

    public void changeSelect(int position) {
        if (mMultiSelection) {
            int indexOfPosition = mListSelection.indexOf(position);
            if (indexOfPosition != -1) {
                mListSelection.remove(indexOfPosition);
            } else {
                mListSelection.add(position);
            }
        } else {
            if (mPositionSelected != position) {
                mPositionSelected = position;
            } else {
                mPositionSelected = -1;
            }
        }
        notifyDataSetChanged();
    }

    public List<Integer> getListSelection() {
        return mListSelection;
    }


    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.chk)
        CheckBox chk;
        @BindView(R.id.tvValue)
        TextView tvValue;
        @BindView(R.id.flCheckbox)
        FrameLayout flCheckbox;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
