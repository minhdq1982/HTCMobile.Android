package com.tvo.htc.view.main.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class BannerAdapter extends BaseAdapter<BannerResponse, BannerAdapter.ViewHolder> {

    BannerAdapter(Context context, List<BannerResponse> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_banner;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, BannerResponse item, int position) {
        holder.ivBanner.setImageResource(item.getImage());
        holder.txtTitle.setText(item.getTitle());
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.ivBanner)
        ImageView ivBanner;
        @BindView(R.id.txtTitle)
        TextView txtTitle;

        public ViewHolder(View view) {
            super(view);
        }
    }
}

class BannerResponse {
    private String title;
    private int image;

    public BannerResponse(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
