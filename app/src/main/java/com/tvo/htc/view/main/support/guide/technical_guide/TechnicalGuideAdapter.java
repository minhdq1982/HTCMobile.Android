package com.tvo.htc.view.main.support.guide.technical_guide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.response.TechnicalGuideResponse;
import com.android.lib.util.LibUtils;
import com.tvo.htc.R;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseLoadMoreAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Create by Ngocji on 10/30/2018
 **/


public class TechnicalGuideAdapter extends BaseLoadMoreAdapter<TechnicalGuideResponse.Item, BaseViewHolder> {
    public static final int view_header = 2;
    private List<TechnicalGuideResponse.Item> items;

    public TechnicalGuideAdapter(Context context, RecyclerView recyclerView, List<TechnicalGuideResponse.Item> items, int pageLimit) {
        super(context, recyclerView, items, pageLimit);
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return view_header;
        return super.getItemViewType(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == view_header) {
            return new ViewHolder(getLayoutInflater().inflate(R.layout.item_guide_header, parent, false));
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_guide;
    }

    @Override
    protected BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(BaseViewHolder holder, TechnicalGuideResponse.Item item, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).txtTitle.setText(item.getTitle());
            ((ViewHolder) holder).txtContentNews.setText(item.getShortContent());
            if(item.getLastModificationTime() != null) {
                ((ViewHolder) holder).txtTimeNews.setText(LibUtils.getFormatTitleDate(item.getLastModificationTime()));
            }
            ImageLoader.loadImage(getContext(), ((ViewHolder) holder).imgNews, R.drawable.img_no_image, Utils.getImagePath(item.getImage()));

            if ((items.size() - 1) == position) {
                ((ViewHolder) holder).lineView.setVisibility(View.GONE);
            }

        }
    }

    static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.imgNews)
        ImageView imgNews;
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtTimeNews)
        TextView txtTimeNews;
        @BindView(R.id.txtContentNews)
        TextView txtContentNews;
        @BindView(R.id.lineView)
        View lineView;
        @BindView(R.id.imgLogo)
        ImageView imgLogo;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
