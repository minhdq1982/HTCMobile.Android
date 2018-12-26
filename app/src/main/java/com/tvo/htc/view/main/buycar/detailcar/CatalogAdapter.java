package com.tvo.htc.view.main.buycar.detailcar;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.lib.model.Car;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class CatalogAdapter extends BaseAdapter<Car.Version.Spec, CatalogAdapter.SpecHolder> {
    public CatalogAdapter(Context context, List<Car.Version.Spec> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_catalog_type;
    }

    @Override
    protected SpecHolder createViewHolder(View view) {
        return new SpecHolder(view);
    }

    @Override
    protected void bindView(SpecHolder holder, Car.Version.Spec item, int position) {
        holder.tvVehicle.setText(item.getName());
        holder.initDetailSpec(getContext(), item.getDetails());
    }


    class SpecHolder extends BaseViewHolder {
        @BindView(R.id.tvVehicle)
        TextView tvVehicle;
        @BindView(R.id.reDetail)
        RecyclerView reDetail;
        SpecDetailAdapter specDetailAdapter;

        public SpecHolder(View view) {
            super(view);
        }

        public void initDetailSpec(Context context, List<Car.Version.Spec.Detail> list) {
            specDetailAdapter = new SpecDetailAdapter(context, list);
            reDetail.setLayoutManager(new LinearLayoutManager(context));
            reDetail.setAdapter(specDetailAdapter);
        }

    }

    static class SpecDetailHolder extends BaseViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvParameter)
        TextView tvParameter;


        public SpecDetailHolder(View view) {
            super(view);
        }

    }


    class SpecDetailAdapter extends BaseAdapter<Car.Version.Spec.Detail, SpecDetailHolder> {
        public SpecDetailAdapter(Context context, List<Car.Version.Spec.Detail> items) {
            super(context, items);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.item_spec_detail;
        }

        @Override
        protected SpecDetailHolder createViewHolder(View view) {
            return new SpecDetailHolder(view);
        }

        @Override
        protected void bindView(SpecDetailHolder holder, Car.Version.Spec.Detail item, int position) {
            holder.tvTitle.setText(item.getName());
            holder.tvParameter.setText(item.getValue());
        }
    }

}
