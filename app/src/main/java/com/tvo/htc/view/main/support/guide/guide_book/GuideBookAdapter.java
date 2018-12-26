package com.tvo.htc.view.main.support.guide.guide_book;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.response.GuideBookResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Create by Ngocji on 11/1/2018
 **/


public class GuideBookAdapter extends BaseAdapter<GuideBookResponse.Data.Items, GuideBookAdapter.ViewHolder> {
    public GuideBookAdapter(Context context, List<GuideBookResponse.Data.Items> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_guide_book;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, GuideBookResponse.Data.Items item, int position) {
        holder.tvName.setText(item.getTitle());
        ImageLoader.loadImage(getContext(), holder.imBackground, R.drawable.ic_bg_book_load, Utils.getDrawableResId(getContext(), "ic_bg_book_" + item.getBgInt(position)));
        ImageLoader.loadImage(getContext(), holder.imPreview, -1, Utils.getImagePath(item.getImage()));
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.cpnbSaleImageView)
        ImageView imBackground;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.imPreview)
        ImageView imPreview;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
