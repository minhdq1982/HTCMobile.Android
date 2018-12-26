package com.tvo.htc.view.main.promotion;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.News;
import com.android.lib.util.LibUtils;
import com.tvo.htc.R;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseLoadMoreAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class DetailPromotionAdapter extends BaseLoadMoreAdapter<News, DetailPromotionAdapter.ViewHolder> {

    private List<News> mList;

    public DetailPromotionAdapter(Context context, RecyclerView recyclerView, List<News> items, int pageLimit) {
        super(context, recyclerView, items, pageLimit);
        mList = items;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_news_feed;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 2;
        }

        return super.getItemViewType(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 2) {
            View view = getLayoutInflater().inflate(R.layout.item_news_feed_header, parent, false);
            return new ViewHolder(view);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder h, News item, int position) {
        h.txtTitle.setText(item.getTitle());
        h.txtTimeNews.setText(LibUtils.getFormatTitleDate(item.getCreationTime()));
        if ((mList.size() - 1) == position) {
            h.lineView.setVisibility(View.GONE);
        }
        ImageLoader.loadImage(getContext(), h.imgNews, R.drawable.img_no_image, Utils.getImagePath(item.getImage()));
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.imgNews)
        ImageView imgNews;
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtTimeNews)
        TextView txtTimeNews;
        @BindView(R.id.lineView)
        View lineView;
        @BindView(R.id.imgLogo)
        ImageView imgLogo;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
