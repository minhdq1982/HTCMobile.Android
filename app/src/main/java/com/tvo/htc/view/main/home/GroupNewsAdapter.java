package com.tvo.htc.view.main.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.News;
import com.android.lib.model.response.GroupNews;
import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;
import com.tvo.htc.view.component.CpnLinearSnapHelper;
import com.tvo.htc.view.main.news.detail.NewsDetailFragment;
import com.tvo.htc.view.main.promotion.PromotionFragment;

import java.util.List;

import butterknife.BindView;

public class GroupNewsAdapter extends BaseAdapter<GroupNews, GroupNewsAdapter.ViewHolder> {

    public GroupNewsAdapter(Context context, List<GroupNews> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_group_news;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, GroupNews item, int position) {
        if (position == 0) {
            holder.vSpace.setVisibility(View.GONE);
        } else {
            holder.vSpace.setVisibility(View.VISIBLE);
        }
        holder.tvName.setText(item.getName());
        HomeNewsAdapter adapter = new HomeNewsAdapter(getContext(), item.getItems());
        adapter.setOnItemClickListener((promotionAgencyAdapter, view, newsPosition) -> {
            News news = (News) promotionAgencyAdapter.getItemAtPosition(newsPosition);
            FragmentUtil.startFragment(getContext(), NewsDetailFragment.newInstance(news), null);
        });
        holder.recyclerView.setAdapter(adapter);
        holder.ivArrowRight.setOnClickListener(v -> {
            Fragment fragment = PromotionFragment.newInstance(item);
            FragmentUtil.startFragment(getContext(), fragment, null);
        });
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.vSpace)
        View vSpace;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.ivArrowRight)
        ImageView ivArrowRight;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        public ViewHolder(View view) {
            super(view);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),
                    LinearLayoutManager.HORIZONTAL, false));
//            recyclerView.addItemDecoration(new ItemOffsetDecoration(view.getContext(),
//                    R.dimen.margin_padding_0_7x));
            SnapHelper helper = new CpnLinearSnapHelper();
            helper.attachToRecyclerView(recyclerView);
        }
    }
}
