package com.tvo.htc.view.main.survey.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tvo.htc.R;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Create by Ngocji on 10/25/2018
 **/


public class LevelAdapter extends BaseAdapter<String, LevelAdapter.ViewHolder> {
    private int mPositionSelected = -1;
    private FrameLayout.LayoutParams mLayoutParams;

    public LevelAdapter(Context context, List<String> items) {
        super(context, items);
        initParamsItem();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_survey_level;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, String item, int position) {
        FrameLayout.LayoutParams params = mLayoutParams;
        holder.itemView.setLayoutParams(params);
        if (mPositionSelected != -1 && mPositionSelected == position) {
            holder.tvLevel.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            holder.llLevel.setBackgroundResource(R.drawable.bg_level_selected);
        } else {
            holder.tvLevel.setTextColor(ContextCompat.getColor(getContext(), R.color.colorTextTile));
            if (position != getItemCount() - 1) {
                holder.llLevel.setBackgroundResource(R.drawable.bg_level_none);
            } else {
                holder.llLevel.setBackgroundResource(0);
            }
        }
        holder.tvLevel.setText(item);
    }

    public String getAnswer() {
        if (mPositionSelected != -1) {
            return getDisplayItems().get(mPositionSelected);
        } else {
            return "";
        }
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

    public int getSelectedPosition() {
        return mPositionSelected;
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvLevel)
        TextView tvLevel;
        @BindView(R.id.llLevel)
        LinearLayout llLevel;

        public ViewHolder(View view) {
            super(view);
        }
    }

    private void initParamsItem() {
        int maxWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        int paddingContainer = Utils.getDimensions(getContext(), R.dimen.margin_padding_1_2x) * 2;
        int paddingPager = Utils.getDimensions(getContext(), R.dimen.survey_item_margin) * 4;
        int widthItem = (maxWidth - paddingContainer - paddingPager) / getItemCount();
        mLayoutParams = new FrameLayout.LayoutParams(widthItem, widthItem);
    }
}
