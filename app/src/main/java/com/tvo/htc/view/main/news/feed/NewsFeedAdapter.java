package com.tvo.htc.view.main.news.feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.response.NewsFeedResponse;
import com.android.lib.util.LibUtils;
import com.tvo.htc.R;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseLoadMoreAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class NewsFeedAdapter extends BaseLoadMoreAdapter<NewsFeedResponse.Data, NewsFeedAdapter.ViewHolder> {

    private List<NewsFeedResponse.Data> mList;
    private Context mContext;
    private boolean isAllowLoadMore = true;

    public NewsFeedAdapter(Context context, RecyclerView recyclerView, List<NewsFeedResponse.Data> items, int pageLimit) {
        super(context, recyclerView, items, pageLimit);
        this.mContext = context;
        this.mList = items;
    }

    public void setAllowLoadMore(boolean allowLoadMore) {
        isAllowLoadMore = allowLoadMore;
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
    protected void bindView(ViewHolder h, NewsFeedResponse.Data item, int position) {
        h.txtTitle.setText(item.getMessage());
        h.txtTimeNews.setText(LibUtils.getFormatTitleDate(item.getCreated_time()));
        if ((mList.size() - 1) == position) {
            h.lineView.setVisibility(View.GONE);
        }
        if (item.getType() == 4) {
            h.imgLogo.setVisibility(View.VISIBLE);
            h.imgLogo.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_news_facebook));
        } else if (item.getType() == 5) {
            h.imgLogo.setVisibility(View.VISIBLE);
            h.imgLogo.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_news_youtube));
        } else {
            h.imgLogo.setVisibility(View.GONE);
        }
        ImageLoader.loadImage(mContext, h.imgNews, R.drawable.img_no_image, item.getFull_picture());

    }

    @Override
    protected boolean isAllowLoadMore() {
        return isAllowLoadMore;
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
