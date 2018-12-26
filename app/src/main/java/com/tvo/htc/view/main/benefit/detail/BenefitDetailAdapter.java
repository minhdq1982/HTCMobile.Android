package com.tvo.htc.view.main.benefit.detail;

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


public class BenefitDetailAdapter extends BaseAdapter<Card, BenefitDetailAdapter.ViewHolder> {

    public BenefitDetailAdapter(Context context, List<Card> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_benefit_detail;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, Card item, int position) {
        holder.tvAgencyName.setText(item.getAgency().getName());
        holder.tvAgencyAddress.setText(item.getAgency().getAddress());
        holder.tvAgencyContact.setText(item.getAgency().getHotline());
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvAgencyName)
        TextView tvAgencyName;
        @BindView(R.id.tvAgencyAddress)
        TextView tvAgencyAddress;
        @BindView(R.id.tvAgencyContact)
        TextView tvAgencyContact;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
