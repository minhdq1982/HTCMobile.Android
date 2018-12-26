package com.tvo.htc.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.tvo.htc.R;

import java.util.List;

import butterknife.BindView;

public abstract class BaseCustomTabScrollAdapter<T> extends BaseAdapter<T, BaseCustomTabScrollAdapter.ViewHolder> {

    private int mSelectedPosition;

    protected abstract String getName(T item);

    public BaseCustomTabScrollAdapter(Context context, List<T> items) {
        super(context, items);
        this.mSelectedPosition = 0;
    }

    @Override
    protected final int getLayoutId() {
        return R.layout.item_custom_tab_scroll;
    }

    @Override
    protected final ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(BaseCustomTabScrollAdapter.ViewHolder holder, T item, int position) {
        if (mSelectedPosition == position) {
            holder.vSelected.setVisibility(View.VISIBLE);
            holder.tvOption.setTypeface(null, Typeface.BOLD);
        } else {
            holder.vSelected.setVisibility(View.INVISIBLE);
            holder.tvOption.setTypeface(null, Typeface.NORMAL);
        }
        holder.tvOption.setText(getName(item));
    }

    @Override
    protected void onItemClick(BaseCustomTabScrollAdapter.ViewHolder holder, int position) {
        super.onItemClick(holder, position);

        setSelectedPosition(position);
    }

    public void setSelectedPosition(int selectedPosition) {
        int oldSelectedPosition = mSelectedPosition;
        this.mSelectedPosition = selectedPosition;
        notifyItemChanged(oldSelectedPosition);
        notifyItemChanged(mSelectedPosition);
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvOption)
        TextView tvOption;
        @BindView(R.id.vSelected)
        View vSelected;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
