package com.tvo.htc.view.main.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.model.News;
import com.android.lib.util.LibUtils;
import com.tvo.htc.R;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class HomeNewsAdapter extends BaseAdapter<News, HomeNewsAdapter.ViewHolder> {

    public HomeNewsAdapter(Context context, List<News> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_home_news;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, News item, int position) {
        if (position == getItemCount() - 1) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.llRootView.getLayoutParams();
            layoutParams.rightMargin = 0;
            holder.llRootView.setLayoutParams(layoutParams);
        }
        ImageLoader.loadImage(getContext(), holder.ivBanner, R.drawable.img_no_image,
                Utils.getImagePath(item.getImage()));
        holder.tvDate.setText(LibUtils.getDate(item.getCreationTime()));
        holder.tvName.setText(item.getTitle());
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.llRootView)
        LinearLayout llRootView;
        @BindView(R.id.ivBanner)
        ImageView ivBanner;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvName)
        TextView tvName;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
