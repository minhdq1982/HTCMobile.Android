package com.tvo.htc.view.main.benefit;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lib.model.WrapperBenefit;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;
import com.tvo.htc.view.component.CpnMembershipCard;

import java.util.List;

import butterknife.BindView;

/**
 * Create by Ngocji on 10/23/2018
 **/


public class BenefitAdapter extends BaseAdapter<WrapperBenefit, BenefitAdapter.ViewHolder> {

    public BenefitAdapter(Context context, List<WrapperBenefit> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_benefit;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, WrapperBenefit item, int position) {
        holder.tvCarName.setText(item.getCarName());
        holder.tvLicensePlate.setText(item.getCarLicensePlates());
        holder.tvAgencyName.setText(item.getAgencyName());
        holder.cpnMembershipCard.setData(item.getCard());
        //Change state
        if (item.isShowExpand()) {
            holder.imStateExpand.setImageResource(R.drawable.ic_drop_up);
            holder.recyclerInnerBenefit.setVisibility(View.VISIBLE);
        } else {
            holder.imStateExpand.setImageResource(R.drawable.ic_drop_down);
            holder.recyclerInnerBenefit.setVisibility(View.GONE);
        }

        holder.rlShowExpand.setOnClickListener((view) -> {
            item.setShowExpand(!item.isShowExpand());
            notifyItemChanged(position);
        });

        //Set listener item in innerBenefit adapter
        holder.initInnerBenefit(getContext(), item.getList())
                .setOnItemClickListener((adapter, view, position1) ->
                        mListener.onItemClick(adapter, view, position1));

    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvCarName)
        TextView tvCarName;
        @BindView(R.id.tvLicensePlate)
        TextView tvLicensePlate;
        @BindView(R.id.tvAgencyName)
        TextView tvAgencyName;
        @BindView(R.id.cpnMembershipCard)
        CpnMembershipCard cpnMembershipCard;

        @BindView(R.id.rlShowExpand)
        RelativeLayout rlShowExpand;
        @BindView(R.id.imStateExpand)
        ImageView imStateExpand;
        @BindView(R.id.recyclerInnerBenefit)
        RecyclerView recyclerInnerBenefit;
        InnerBenefitAdapter adapter;

        public ViewHolder(View view) {
            super(view);
        }

        public InnerBenefitAdapter initInnerBenefit(Context context, List<WrapperBenefit.WrapperDetailBenefit> list) {
            adapter = new InnerBenefitAdapter(context, list);
            recyclerInnerBenefit.setLayoutManager(new LinearLayoutManager(context));
            recyclerInnerBenefit.setAdapter(adapter);
            return adapter;
        }
    }

    static class InnerBenefitAdapter extends BaseAdapter<WrapperBenefit.WrapperDetailBenefit, InnerBenefitAdapter.ViewHolder> {

        public InnerBenefitAdapter(Context context, List<WrapperBenefit.WrapperDetailBenefit> items) {
            super(context, items);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.item_inner_benefit;
        }

        @Override
        protected ViewHolder createViewHolder(View view) {
            return new ViewHolder(view);
        }

        @Override
        protected void bindView(ViewHolder holder, WrapperBenefit.WrapperDetailBenefit item, int position) {
            holder.tvBenefitName.setText(item.getContent());
        }

        static class ViewHolder extends BaseViewHolder {
            @BindView(R.id.tvBenefitName)
            TextView tvBenefitName;
            @BindView(R.id.line)
            View line;

            public ViewHolder(View view) {
                super(view);
            }
        }
    }
}
