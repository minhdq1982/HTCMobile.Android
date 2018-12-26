package com.tvo.htc.view.main.support.findlocation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.util.LibUtils;
import com.android.lib.util.Logger;
import com.google.android.gms.maps.model.LatLng;
import com.tvo.htc.R;
import com.tvo.htc.view.BaseAdapter;
import com.tvo.htc.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class FindLocationListAdapter extends BaseAdapter<AgenciesResponse.Agency, FindLocationListAdapter.ViewHolder> {

    private Location mLocation = null;
    private LatLng myLatLng, otLatLng;

    public boolean isClick = false;
    public int backupPosition = 0;

    private Context context;
    private int mType;

    private List<AgenciesResponse.Agency> mList;

    public FindLocationListAdapter(Context context, List<AgenciesResponse.Agency> items, int type) {
        super(context, items);
        this.context = context;
        this.mType = type;
        this.mList = items;
    }

    public void setLocation(Location mLocation) {
        this.mLocation = mLocation;
        Logger.d(mLocation.getLatitude() + " " + mLocation.getLongitude());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_find_location;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void bindView(ViewHolder holder, AgenciesResponse.Agency item, int position) {
        holder.txtTitleLocation.setText(item.getName());
        holder.txtAddressLocation.setText(item.getAddress());
        if (mLocation != null) {
            myLatLng = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
            otLatLng = new LatLng(item.getLatitude(), item.getLongitude());
            holder.txtDistanceLocation.setText(LibUtils.ReplaceDot(LibUtils.CalculationByDistance(myLatLng, otLatLng)).replace(".", ",") + "km");
        }

        if (position == backupPosition && isClick) {
            holder.itemView.setBackgroundResource(R.color.colorBackgroundList);
        } else {
            holder.itemView.setBackgroundResource(R.color.colorWhite);
        }
        if (position == 0) {
            holder.lineTop.setVisibility(View.GONE);
        } else if (position == mList.size() - 1) {
            holder.lineBottom.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onItemClick(ViewHolder holder, int position) {
        super.onItemClick(holder, position);
        isClick = true;
        notifyItemChanged(backupPosition);
        backupPosition = position;
        notifyItemChanged(position);
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.layout)
        RelativeLayout layout;
        @BindView(R.id.txtTitleLocation)
        TextView txtTitleLocation;
        @BindView(R.id.txtAddressLocation)
        TextView txtAddressLocation;
        @BindView(R.id.txtDistanceLocation)
        TextView txtDistanceLocation;
        @BindView(R.id.lineTop)
        View lineTop;
        @BindView(R.id.lineBottom)
        View lineBottom;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
