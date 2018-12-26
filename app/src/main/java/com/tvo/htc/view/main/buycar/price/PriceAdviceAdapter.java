package com.tvo.htc.view.main.buycar.price;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.lib.model.response.PriceAdviceResponse.PriceAdviceList;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class PriceAdviceAdapter extends BaseAdapter<PriceAdviceList, PriceAdviceAdapter.ViewHolder> {

    public PriceAdviceAdapter(Context context, List<PriceAdviceList> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_price_advice;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, PriceAdviceList item, int position) {
        holder.txtTitle.setText(item.getTitle());
        holder.txtPrice.setText(item.getPrice());
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtPrice)
        TextView txtPrice;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
