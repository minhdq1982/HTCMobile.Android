package com.tvo.htc.view.main.notification;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.model.response.NotificationResponse;
import com.android.lib.util.LibUtils;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseLoadMoreAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Create by Ngocji on 10/22/2018
 **/


public class NotificationAdapter extends BaseLoadMoreAdapter<NotificationResponse.Notification, NotificationAdapter.ViewHolder> {
    public NotificationAdapter(Context context, RecyclerView recyclerView, List<NotificationResponse.Notification> items, int pageLimit) {
        super(context, recyclerView, items, pageLimit);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_notification;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder holder, NotificationResponse.Notification item, int position) {
        //check visible title date
        if (position == 0) {
            holder.llTitleDate.setVisibility(GONE);
        } else {
            if (LibUtils.compareDate(getDisplayItems().get(position - 1).getNotifiedTime(), item.getNotifiedTime())) {
                holder.llTitleDate.setVisibility(VISIBLE);
                holder.tvTitleDate.setText(LibUtils.getFormatTitleDate(item.getNotifiedTime()));
            } else {
                holder.llTitleDate.setVisibility(GONE);
            }
        }

        switch (item.getNotificationType()) {
            case Other:
                holder.imIcon.setImageResource(R.drawable.ic_bell);
                break;
            case Survey:
                holder.imIcon.setImageResource(R.drawable.ic_survey);
                break;
            case Promotion:
                holder.imIcon.setImageResource(R.drawable.ic_gift);
                break;
            case MaintenanceRemind:
                holder.imIcon.setImageResource(R.drawable.ic_maintenance);
                break;
            case Birthday:
                holder.imIcon.setImageResource(R.drawable.ic_birthday);
                break;
            case None:
            default:
                holder.imIcon.setImageResource(R.drawable.ic_bell);
        }
        holder.tvTitle.setText(item.getTitle());
        holder.tvContent.setText(item.getShortContent());
        holder.tvDate.setText(LibUtils.getTimeAgo(item.getNotifiedTime()));
        if (item.isRead()) {
            holder.flContent.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorNotificationRead));
        } else {
            holder.flContent.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorNotificationUnRead));
        }
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvTitleDate)
        TextView tvTitleDate;
        @BindView(R.id.llTitleDate)
        LinearLayout llTitleDate;
        @BindView(R.id.imIcon)
        ImageView imIcon;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.flContent)
        View flContent;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
