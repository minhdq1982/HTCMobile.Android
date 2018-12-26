package com.tvo.htc.view.main.services.appointment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.lib.model.response.AppointmentResponse;
import com.android.lib.util.LibUtils;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseLoadMoreAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Create by Ngocji on 10/18/2018
 **/


public class AppointmentAdapter extends BaseLoadMoreAdapter<AppointmentResponse.Data, AppointmentAdapter.ViewHolder> {
    private List<AppointmentResponse.Data> items;

    public AppointmentAdapter(Context context, RecyclerView recyclerView, List<AppointmentResponse.Data> items, int pageLimit) {
        super(context, recyclerView, items, pageLimit);
        this.items = items;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_appointment;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, AppointmentResponse.Data item, int position) {
        holder.tvDate.setText(LibUtils.convertDateToDayOfWeek(item.getDateAppointment()));
        holder.tvTime.setText(LibUtils.getDateTimeFromString(item.getDateAppointment()));
        holder.tvServices.setText(item.getServiceName());
        holder.tvCarName.setText(item.getCarName());
        holder.tvAgencyName.setText(item.getAgencyName());
    }

    @Override
    public int getItemViewType(int position) {
        if(position < items.size()) {
            if (LibUtils.checkDayOut(items.get(position).getDateAppointment())) {
                return 2;
            }
        }
        return super.getItemViewType(position);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 2) {
            View view = getLayoutInflater().inflate(R.layout.item_appointment_gray, parent, false);
            return new ViewHolder(view);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.tvServices)
        TextView tvServices;
        @BindView(R.id.tvCarName)
        TextView tvCarName;
        @BindView(R.id.tvAgencyName)
        TextView tvAgencyName;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
