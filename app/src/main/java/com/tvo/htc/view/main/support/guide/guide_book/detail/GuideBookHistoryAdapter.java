package com.tvo.htc.view.main.support.guide.guide_book.detail;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.model.response.GuildBookItemDetailResponse;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

import static android.graphics.Typeface.BOLD;
import static android.graphics.Typeface.NORMAL;

/**
 * Create by Ngocji on 11/1/2018
 **/


public class GuideBookHistoryAdapter extends BaseAdapter<GuildBookItemDetailResponse.Item, GuideBookHistoryAdapter.ViewHolder> {
    public GuideBookHistoryAdapter(Context context, List<GuildBookItemDetailResponse.Item> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_book_history;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, GuildBookItemDetailResponse.Item item, int position) {
        holder.tvName.setText(item.getTitle());
        if (item.getParentId() == 0) {
            makeTitle(holder.tvName, true);
        } else {
            makeTitle(holder.tvName, false);
        }
    }

    private void makeTitle(TextView tv, boolean isTitle) {
        int type = isTitle ? BOLD : NORMAL;
        tv.setTypeface(null, type);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.llRootView)
        LinearLayout llRootView;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
