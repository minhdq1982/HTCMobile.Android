package com.tvo.htc.view.main.profile.car;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Create by Ngocji on 10/19/2018
 **/


public class ProfileCarAdapter extends BaseAdapter<LoginResponse.Data.Car, ProfileCarAdapter.ViewHolder> {
    public ProfileCarAdapter(Context context, List<LoginResponse.Data.Car> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_profile_car;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, LoginResponse.Data.Car item, int position) {
        ImageLoader.loadImage(getContext(), holder.imgCar, R.drawable.img_no_image, Utils.getImagePath(item.getCarImage()));
        holder.tvCarName.setText(item.getCarName());
        holder.tvCarLicensePlate.setText(item.getLicensePlate());
        holder.tvAgency.setText(item.getAgencyName());
        holder.btnDelete.setOnClickListener(view -> mListener.onItemClick(this, view, holder.getAdapterPosition()));
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvCarName)
        TextView tvCarName;
        @BindView(R.id.tvCarLicensePlate)
        TextView tvCarLicensePlate;
        @BindView(R.id.tvAgency)
        TextView tvAgency;
        @BindView(R.id.imCarPreview)
        ImageView imgCar;
        @BindView(R.id.btnDelete)
        ImageView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
