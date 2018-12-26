package com.tvo.htc.view.main.services.maintenance_level;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.lib.model.response.MaintenanceDetailResponse.MaintenanceList;
import com.tvo.htc.R;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Create by Ngocji on 10/19/2018
 **/


public class AccessaryAdapter extends BaseAdapter<MaintenanceList, AccessaryAdapter.ViewHolder> {

    public AccessaryAdapter(Context context, List<MaintenanceList> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_maintenance_accessary;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, MaintenanceList item, int position) {
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(Utils.getMoney(String.valueOf(item.getPrice())));
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPrice)
        TextView tvPrice;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
