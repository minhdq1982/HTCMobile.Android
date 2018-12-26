package com.tvo.htc.view.main.support.guide.warranty_policy.detail;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.model.response.WarrantyPolicyResponse.WarrantyPolicy;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

import static android.graphics.Typeface.BOLD;

/**
 * Create by Ngocji on 11/1/2018
 **/


public class WarrantyHistoryAdapter extends BaseAdapter<WarrantyPolicy, WarrantyHistoryAdapter.ViewHolder> {
    public WarrantyHistoryAdapter(Context context, List<WarrantyPolicy> items) {
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
    protected void bindView(ViewHolder holder, WarrantyPolicy item, int position) {
        holder.tvName.setText(item.getName() != null ? item.getName() : "Nội dung " + position);
    }

    private void makeTitle(TextView tv, boolean isSelected) {
        tv.setTypeface(tv.getTypeface(), BOLD);
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
