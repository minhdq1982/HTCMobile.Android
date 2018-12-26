package com.tvo.htc.view.main.news;

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

public class NewsAdapter extends BaseLoadMoreAdapter<News, NewsAdapter.ViewHolderNews> {
    private List<News> mList;
    private Context mContext;

    public NewsAdapter(Context context, RecyclerView recyclerView, List<News> items, int pageLimit) {
        super(context, recyclerView, items, pageLimit);
        this.mList = items;
        this.mContext = context;
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
            return new ViewHolderNews(view);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected ViewHolderNews createViewHolder(View view) {
        return new ViewHolderNews(view);
    }

    @Override
    protected void bindView(ViewHolderNews h, News item, int position) {
        h.txtTitle.setText(item.getTitle());
        h.txtTimeNews.setText(LibUtils.getFormatTitleDate(item.getCreationTime()));
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
        ImageLoader.loadImage(mContext, h.imgNews, R.drawable.img_no_image, Utils.getImagePath(item.getImage()));

    }


    class ViewHolderNews extends BaseViewHolder {
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


        public ViewHolderNews(View view) {
            super(view);
        }

    }

}
