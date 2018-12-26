package com.tvo.htc.view.main.services.make_appointment.services;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.response.ServicesListResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Create by Ngocji on 10/17/2018
 **/


public class ChooseServicesAdapter extends BaseAdapter<ServicesListResponse.Data, ChooseServicesAdapter.ViewHolder> {

    public ChooseServicesAdapter(Context context, List<ServicesListResponse.Data> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_service_list;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, ServicesListResponse.Data item, int position) {
        holder.tvServiceName.setText(item.getName());
        ImageLoader.loadImage(getContext(), holder.imgServiceIcon, Utils.getDrawableResId(getContext(), item.getIconRes()));
        if (item.isSelected()) {
            holder.itemView.setBackgroundResource(R.drawable.img_bg_selected);
//            holder.itemView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorServiceListSelected));
        } else {
            holder.itemView.setBackgroundResource(R.drawable.bg_border_item_service_list);
//            holder.itemView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorServiceListNormal));
        }
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.imgServiceIcon)
        ImageView imgServiceIcon;

        @BindView(R.id.tvServiceName)
        TextView tvServiceName;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
