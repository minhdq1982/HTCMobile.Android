package com.tvo.htc.view.dialog;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;

import com.android.lib.model.Car;
import com.android.lib.model.response.DetailCarResponse;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class VersionAdapter extends BaseAdapter<Car.Version, VersionAdapter.ViewHolder> {

    private int mSelectionPosition = 0;

    public VersionAdapter(Context context, List<Car.Version> items) {
        super(context, items);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_car_version;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, Car.Version item, int position) {
        if (mSelectionPosition == position) {
            holder.rbVersion.setChecked(true);
        } else {
            holder.rbVersion.setChecked(false);
        }

        if (item.getVersionName() != null) {
            holder.rbVersion.setText(item.getVersionName());
        }
    }

    @Override
    protected void onItemClick(ViewHolder holder, int position) {
        super.onItemClick(holder, position);

        int oldSelectionPosition = mSelectionPosition;
        mSelectionPosition = position;
        holder.rbVersion.setChecked(true);

        this.notifyItemChanged(oldSelectionPosition);
    }

    public Car.Version getSelectionItem() {
        if (mSelectionPosition >= 0 && mSelectionPosition < getItemCount()) {
            return getItemAtPosition(mSelectionPosition);
        }
        return null;
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.rbVersion)
        RadioButton rbVersion;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
