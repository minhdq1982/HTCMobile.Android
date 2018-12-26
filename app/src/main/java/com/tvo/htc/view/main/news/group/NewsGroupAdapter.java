package com.tvo.htc.view.main.news.group;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.lib.model.response.FacebookGroupResponse.Group.Items;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class NewsGroupAdapter extends BaseAdapter<Items, NewsGroupAdapter.ViewHolder> {

    NewsGroupAdapter(Context context, List<Items> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_news_group;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, Items item, int position) {
        holder.txtTitle.setText(item.getName());
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.txtTitle)
        TextView txtTitle;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
