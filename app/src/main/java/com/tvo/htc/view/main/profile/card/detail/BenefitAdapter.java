package com.tvo.htc.view.main.profile.card.detail;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.lib.model.Card;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Create by Ngocji on 10/24/2018
 **/


public class BenefitAdapter extends BaseAdapter<Card.Benefit, BenefitAdapter.ViewHolder> {
    public BenefitAdapter(Context context, List<Card.Benefit> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_inner_benefit_detail;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, Card.Benefit item, int position) {
        holder.tvBenefitName.setText(item.getContent());
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvBenefitName)
        TextView tvBenefitName;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
