package com.tvo.htc.view.main.buycar.car_comparison;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.tvo.htc.R;
import com.tvo.htc.model.CarComparisonSpec;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class CarSpecAdapter extends BaseAdapter<CarComparisonSpec.Item, CarSpecAdapter.ViewHolder> {

    public CarSpecAdapter(Context context, List<CarComparisonSpec.Item> items) {
        super(context, items);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_car_spec;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, CarComparisonSpec.Item item, int position) {
        holder.tvTitle.setText(item.getName());
        holder.tvValue1.setText(item.getValue().first);
        holder.tvValue2.setText(item.getValue().second);
    }


    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvValue1)
        TextView tvValue1;
        @BindView(R.id.tvValue2)
        TextView tvValue2;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
