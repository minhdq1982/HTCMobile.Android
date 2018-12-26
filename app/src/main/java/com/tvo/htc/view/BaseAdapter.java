package com.tvo.htc.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.model.LocalDataManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinhNH on 3/23/2018.
 */

public abstract class BaseAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {

    private final Context mContext;
    private final LayoutInflater mInflater;
    private final LoginResponse mLoginResponse;
    private List<T> mDisplayItems;
    public OnItemClickListener mListener;

    protected abstract int getLayoutId();

    protected abstract VH createViewHolder(View view);

    protected abstract void bindView(VH holder, T item, int position);

    public interface OnItemClickListener {
        void onItemClick(BaseAdapter adapter, View view, int position);
    }

    public BaseAdapter(Context context, List<T> items) {
        mContext = context;
        mDisplayItems = items;
        if (mDisplayItems == null) {
            mDisplayItems = new ArrayList<>();
        }
        mInflater = LayoutInflater.from(context);

        mLoginResponse = LocalDataManager.getInstance(context).getLoginResponse();
    }

    public final void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public final Context getContext() {
        return mContext;
    }

    public final LayoutInflater getLayoutInflater() {
        return mInflater;
    }

    public final List<T> getDisplayItems() {
        return mDisplayItems;
    }

    public LoginResponse getLoginResponse() {
        return mLoginResponse;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(getLayoutId(), parent, false);
        return createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T item = getItemAtPosition(position);
        bindView((VH) holder, item, position);

        holder.itemView.setOnClickListener(view -> {
            onItemClick((VH) holder, holder.getAdapterPosition());
            if (mListener != null) {
                mListener.onItemClick(BaseAdapter.this, view, holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDisplayItems == null ? 0 : mDisplayItems.size();
    }

    public final T getItemAtPosition(int position) {
        if (mDisplayItems != null && position < mDisplayItems.size()) {
            return mDisplayItems.get(position);
        }
        return null;
    }

    protected void onItemClick(VH holder, int position) {

    }

    public final void updateData(List<T> items) {
        mDisplayItems = items;
        if (mDisplayItems == null) {
            mDisplayItems = new ArrayList<>();
        }

        notifyDataSetChanged();
    }
}
