package com.tvo.htc.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.lib.util.Logger;
import com.tvo.htc.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ThinhNH on 3/23/2018.
 */

public abstract class BaseLoadMoreAdapter<T, VH extends BaseViewHolder> extends BaseAdapter<T, VH> {

    private final int mPageLimit;
    private OnLoadMoreListener mOnLoadMoreListener;

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int lastVisibleItem;
    private int totalItemCount;
    private boolean loading;

    public interface OnLoadMoreListener {
        void onLoadMore(int skipCount);
    }

    public BaseLoadMoreAdapter(Context context, RecyclerView recyclerView, List<T> items, int pageLimit) {
        super(context, items);

        mPageLimit = pageLimit;
        initLoadMore(recyclerView);
    }

    private void initLoadMore(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();
            recyclerView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                            totalItemCount = linearLayoutManager.getItemCount();
                            lastVisibleItem = linearLayoutManager
                                    .findLastVisibleItemPosition();
                            if (!loading
                                    && (totalItemCount == lastVisibleItem + 1)  // check last item visible
                                    && isAllowLoadMore()) {                     // Check has new page
                                Logger.d("lastVisibleItem = " + lastVisibleItem);
                                // End has been reached
                                // Do something
                                if (mOnLoadMoreListener != null) {
                                    mOnLoadMoreListener.onLoadMore(totalItemCount - 1);
                                }
                                loading = true;
                            }
                        }
                    });
        }
    }

    public final void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = getLayoutInflater().inflate(R.layout.item_load_more, parent, false);
            return new LoadMoreViewHolder(view);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof LoadMoreViewHolder) {
            LoadMoreViewHolder loadMoreViewHolder = (LoadMoreViewHolder) holder;
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) loadMoreViewHolder.llRootView.getLayoutParams();
            if (!isAllowLoadMore()) {
                layoutParams.height = 0;
            } else {
                layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT;
            }
            loadMoreViewHolder.llRootView.setLayoutParams(layoutParams);
        } else {
            super.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return 1;
        }
        return super.getItemViewType(position);
    }

    @Override
    public final int getItemCount() {
        if (super.getItemCount() != 0) {
            return super.getItemCount() + 1;
        }

        return super.getItemCount();
    }

    public final void addMoreData(List<T> items) {
        if (items == null || items.isEmpty()) {
            return;
        }
        loading = false;
        getDisplayItems().addAll(items);
        notifyDataSetChanged();
    }

    /**
     * Check has new page
     * @return
     */
    protected boolean isAllowLoadMore() {
        if (getItemCount() - 1 == 0) {
            return false;
        }
        return ((getItemCount() - 1) % mPageLimit == 0);
    }

    static class LoadMoreViewHolder extends BaseViewHolder {
        @BindView(R.id.llRootView)
        LinearLayout llRootView;

        public LoadMoreViewHolder(View view) {
            super(view);
        }
    }
}
