package com.tvo.htc.view.main.buycar.select_car.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.Car;
import com.tvo.htc.R;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import okhttp3.internal.Util;

public class ChooseCarAdapter extends BaseAdapter<Car, ChooseCarAdapter.ViewHolder> {

    private Context mContext;

    public ChooseCarAdapter(Context context, List<Car> items) {
        super(context, items);
        mContext = context;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_buy_car_product;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, Car item, int position) {
        String imagePreview = Utils.getImagePath(item.getImages().isEmpty() ? "" : item.getImages().get(0));
        ImageLoader.loadImage(
                mContext,
                holder.imCarPreview,
                R.drawable.img_no_image,
                imagePreview);
        holder.tvName.setText(item.getName());
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.imCarPreview)
        ImageView imCarPreview;
        @BindView(R.id.tvName)
        TextView tvName;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
