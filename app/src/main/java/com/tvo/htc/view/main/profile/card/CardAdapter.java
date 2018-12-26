package com.tvo.htc.view.main.profile.card;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.model.Card;
import com.tvo.htc.R;
import com.tvo.htc.model.WrapperCard;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;
import com.tvo.htc.view.component.CpnMembershipCard;

import java.util.List;

import butterknife.BindView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Create by Ngocji on 10/23/2018
 **/


public class CardAdapter extends BaseAdapter<WrapperCard, CardAdapter.ViewHolder> {
    private FragmentManager mFragmentManager;
    private OnItemClickListener mSubListener;

    public CardAdapter(Context context, List<WrapperCard> items, FragmentManager mFragmentManager) {
        super(context, items);
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_profile_card;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, WrapperCard item, int position) {
        holder.tvAgencyName.setText(item.getName());
        if (item.isShowExpand()) {
            holder.imStateExpand.setImageResource(R.drawable.ic_drop_up);
            holder.recyclerView.setVisibility(VISIBLE);
        } else {
            holder.imStateExpand.setImageResource(R.drawable.ic_drop_down);
            holder.recyclerView.setVisibility(GONE);
        }
        holder.initCards(getContext(), item.getCards(), mSubListener);
        holder.llShowExpand.setOnClickListener(view -> {
            item.setShowExpand(!item.isShowExpand());
            notifyItemChanged(position);
        });
    }

    public void setSubListener(OnItemClickListener mSubListener) {
        this.mSubListener = mSubListener;
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvAgencyName)
        TextView tvAgencyName;
        @BindView(R.id.imStateExpand)
        ImageView imStateExpand;
        @BindView(R.id.llShowExpand)
        LinearLayout llShowExpand;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        public ViewHolder(View view) {
            super(view);
        }

        public void initCards(Context context, List<Card> cards, OnItemClickListener subListener) {
            SubCardAdapter subCardAdapter = new SubCardAdapter(context, cards);
            subCardAdapter.setOnItemClickListener(subListener);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(subCardAdapter);
        }
    }
}

class SubCardAdapter extends BaseAdapter<Card, SubCardAdapter.ViewHolder> {

    public SubCardAdapter(Context context, List<Card> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_profile_sub_card;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, Card item, int position) {
        holder.cpnCard.setData(item);
        holder.cpnCard.setOnClickListener(view ->
                mListener.onItemClick(SubCardAdapter.this, view, holder.getAdapterPosition()));
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.cpnCard)
        CpnMembershipCard cpnCard;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
