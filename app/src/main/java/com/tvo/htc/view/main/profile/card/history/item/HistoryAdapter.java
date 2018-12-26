package com.tvo.htc.view.main.profile.card.history.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.History;
import com.tvo.htc.R;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BaseLoadMoreAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Create by Ngocji on 10/25/2018
 **/


public class HistoryAdapter extends BaseLoadMoreAdapter<History, HistoryAdapter.ViewHolder> {

    public HistoryAdapter(Context context, RecyclerView recyclerView, List<History> items, int pageLimit) {
        super(context, recyclerView, items, pageLimit);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_history;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, History item, int position) {
        switch (item.getTypeHistory()) {
            case ENDOW:
                holder.imgType.setVisibility(View.GONE);
                break;
            case GRADE_POINT:
                holder.imgType.setVisibility(View.VISIBLE);
                holder.imgType.setImageResource(R.drawable.ic_add_point);
                break;
            case USE_POINT:
                holder.imgType.setVisibility(View.VISIBLE);
                if (item.getAmount() > 0) {
                    holder.imgType.setImageResource(R.drawable.ic_add_point);
                } else if (item.getAmount() < 0) {
                    holder.imgType.setImageResource(R.drawable.ic_subtract_point);
                }
                break;
            default:
                holder.imgType.setVisibility(View.GONE);
        }

        holder.tvTitle.setText(item.getPreviewTitle());
        holder.tvDate.setText(item.getPreviewDate());
        Pair<String, String> dataFirst = item.getPreviewDataFirst();
        holder.tvTitleFirst.setText(Utils.getStringResId(getContext(), dataFirst.first));
        holder.tvDataFirst.setText(dataFirst.second);

        Pair<String, String> dataSecond = item.getPreviewDataSecond();
        holder.tvTitleSecond.setText(Utils.getStringResId(getContext(), dataSecond.first));
        holder.tvDataSecond.setText(dataSecond.second);

        Pair<String, String> dataThird = item.getPreviewDataThird();
        holder.tvTitleThird.setText(Utils.getStringResId(getContext(), dataThird.first));
        holder.tvDataThird.setText(dataThird.second);
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvTitleFirst)
        TextView tvTitleFirst;
        @BindView(R.id.tvDataFirst)
        TextView tvDataFirst;
        @BindView(R.id.tvTitleSecond)
        TextView tvTitleSecond;
        @BindView(R.id.tvDataSecond)
        TextView tvDataSecond;
        @BindView(R.id.tvTitleThird)
        TextView tvTitleThird;
        @BindView(R.id.tvDataThird)
        TextView tvDataThird;
        @BindView(R.id.imgType)
        ImageView imgType;

        public ViewHolder(View view) {
            super(view);
        }

    }
}
