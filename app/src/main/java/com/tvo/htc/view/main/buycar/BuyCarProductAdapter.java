package com.tvo.htc.view.main.buycar;

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
import butterknife.ButterKnife;

public class BuyCarProductAdapter extends BaseAdapter<Car, BuyCarProductAdapter.ItemHolder> {
    public static final int view_header = 2;
    public static final int view_item = 1;

    public BuyCarProductAdapter(Context context, List<Car> items) {
        super(context, items);
    }

    @Override
    public int getItemViewType(int position) {
        /*if (getDisplayItems().get(position) instanceof String) {
            return view_header;
        }*/
        return view_item;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_buy_car_product;
    }

    @Override
    protected ItemHolder createViewHolder(View view) {
        return new ItemHolder(view);
    }

    @Override
    protected void bindView(ItemHolder holder, Car item, int position) {
        String image = "";
        if (item.getImages() != null && !item.getImages().isEmpty()) {
            image = Utils.getImagePath(item.getImages().get(0));
        }
        ImageLoader.loadImage(
                getContext(),
                holder.imCarPreview,
                R.drawable.img_no_image,
                image);
        holder.tvName.setText(item.getName());
    }

    public void updateSearchQuery(List<Car> searchList) {
        updateData(searchList);
    }

    static class ItemHolder extends BaseViewHolder {
        @BindView(R.id.imCarPreview)
        ImageView imCarPreview;
        @BindView(R.id.tvName)
        TextView tvName;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
