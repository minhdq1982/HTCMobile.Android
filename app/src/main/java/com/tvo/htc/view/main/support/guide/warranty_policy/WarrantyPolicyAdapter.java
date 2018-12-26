package com.tvo.htc.view.main.support.guide.warranty_policy;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.lib.model.response.WarrantyPolicyResponse;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Create by Ngocji on 10/29/2018
 **/


public class WarrantyPolicyAdapter extends BaseAdapter<WarrantyPolicyResponse.WarrantyPolicy, WarrantyPolicyAdapter.ViewHolder> {
    private List<WarrantyPolicyResponse.WarrantyPolicy> mList;

    public WarrantyPolicyAdapter(Context context, List<WarrantyPolicyResponse.WarrantyPolicy> items) {
        super(context, items);
        this.mList = items;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_guide_warranty_policy;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, WarrantyPolicyResponse.WarrantyPolicy item, int position) {
        holder.tvTitle.setText(item.getName() != null ? item.getName() : "Nội dung " + position);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.lineView)
        View lineView;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
