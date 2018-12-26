package com.tvo.htc.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ThinhNH on 10/08/2016
 * display and control action on navigation bar
 */
public class CpnCustomRecyclerView extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.llRefresh)
    LinearLayout llRefresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;

    private boolean clipToPadding;
    private float mPadding;
    private float mPaddingLeft;
    private float mPaddingTop;
    private float mPaddingRight;
    private float mPaddingBottom;
    private CustomRecyclerViewListener mListener;
    private boolean isShow;
    private boolean enableRefresh = true;

    public interface CustomRecyclerViewListener {
        void onRefresh();
    }

    RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            Logger.d("dy: " + dy);
            if (enableRefresh) {
                if (dy >= 0) {
//                llRefresh.setVisibility(GONE);
                    showRefresh(false);
                } else {
                    llRefresh.setVisibility(VISIBLE);
                    showRefresh(true);
                }
            }
        }
    };

    public CpnCustomRecyclerView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CpnCustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CpnCustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable
                .CpnCustomRecyclerView, defStyle, 0);

        clipToPadding = a.getBoolean(R.styleable.CpnCustomRecyclerView_android_clipToPadding, true);
        mPadding = a.getDimension(R.styleable.CpnCustomRecyclerView_android_padding, 0);
        mPaddingLeft = a.getDimension(R.styleable.CpnCustomRecyclerView_android_paddingLeft, 0);
        mPaddingTop = a.getDimension(R.styleable.CpnCustomRecyclerView_android_paddingTop, 0);
        mPaddingRight = a.getDimension(R.styleable.CpnCustomRecyclerView_android_paddingRight, 0);
        mPaddingBottom = a.getDimension(R.styleable.CpnCustomRecyclerView_android_paddingBottom, 0);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.cpn_custom_view_pager, this, true);
        ButterKnife.bind(this);

        updateView();
    }

    private void updateView() {
        setClipToPadding(clipToPadding);

        mPaddingLeft = mPaddingLeft > 0 ? mPaddingLeft : mPadding;
        mPaddingTop = mPaddingTop > 0 ? mPaddingTop : mPadding;
        mPaddingRight = mPaddingRight > 0 ? mPaddingRight : mPadding;
        mPaddingBottom = mPaddingBottom > 0 ? mPaddingBottom : mPadding;
        setPadding((int) mPaddingLeft, (int) mPaddingTop, (int) mPaddingRight, (int) mPaddingBottom);

        recyclerView.removeOnScrollListener(mScrollListener);
        recyclerView.addOnScrollListener(mScrollListener);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void setCustomRecyclerViewListener(CustomRecyclerViewListener listener) {
        this.mListener = listener;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRefreshing(boolean refreshing) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(refreshing);
        }
    }

    public void setAdapter(@Nullable RecyclerView.Adapter adapter) {
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);
            registerDataChangedAdapter(adapter);
        }
    }

    private void registerDataChangedAdapter(RecyclerView.Adapter adapter) {
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                updateState(adapter.getItemCount());
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                updateState(adapter.getItemCount());
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);
                updateState(adapter.getItemCount());
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                updateState(adapter.getItemCount());
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                updateState(adapter.getItemCount());
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                updateState(adapter.getItemCount());
            }
        });
        updateState(adapter.getItemCount());
    }

    private void updateState(int count) {
        if (count == 0) {
            txtEmpty.setVisibility(VISIBLE);
            recyclerView.setVisibility(GONE);
        } else {
            txtEmpty.setVisibility(GONE);
            recyclerView.setVisibility(VISIBLE);
        }
    }

    @Nullable
    public RecyclerView.Adapter getAdapter() {
        if (recyclerView != null) {
            return recyclerView.getAdapter();
        }

        return null;
    }

    public void setLayoutManager(@Nullable RecyclerView.LayoutManager layout) {
        if (recyclerView != null) {
            recyclerView.setLayoutManager(layout);
        }
    }

    public void addOnScrollListener(@NonNull RecyclerView.OnScrollListener listener) {
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(listener);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void setClipBounds(Rect clipBounds) {
        if (recyclerView != null) {
            recyclerView.setClipBounds(clipBounds);
        }
    }

    public void setClipToPadding(boolean clipToPadding) {
        if (recyclerView != null) {
            recyclerView.setClipToPadding(clipToPadding);
        }
    }

    public void addItemDecoration(@NonNull RecyclerView.ItemDecoration decor) {
        if (recyclerView != null) {
            recyclerView.addItemDecoration(decor);
        }
    }

    public void setEnableRefresh(boolean enableRefresh) {
        this.enableRefresh = enableRefresh;
        swipeRefreshLayout.setEnabled(enableRefresh);
    }

    @OnClick({R.id.llRefresh})
    void onClick(View view) {
        if (!Utils.isEnabledClick(Utils.CLICK_DELAY_MSEC_500)) {
            return;
        }

        switch (view.getId()) {
            case R.id.llRefresh:
//                llRefresh.setVisibility(GONE);
                showRefresh(false);

                if (mListener != null) {
                    swipeRefreshLayout.setRefreshing(true);
                    mListener.onRefresh();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        showRefresh(false);
        if (mListener != null) {
            mListener.onRefresh();
        }
    }

    private void showRefresh(boolean isShow) {
        if (this.isShow == isShow) {
            return;
        }
        this.isShow = isShow;
        Animation translateAnim;
        if (isShow) {
            translateAnim = AnimationUtils.loadAnimation(getContext(),
                    R.anim.anim_refresh_in);
        } else {
            translateAnim = AnimationUtils.loadAnimation(getContext(),
                    R.anim.anim_refresh_out);
        }
//        translateAnim.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                if (!isShow) {
//                    llRefresh.setVisibility(GONE);
//                }
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
        llRefresh.startAnimation(translateAnim);
    }
}