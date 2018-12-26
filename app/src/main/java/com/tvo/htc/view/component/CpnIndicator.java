package com.tvo.htc.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tvo.htc.R;
import com.tvo.htc.util.Utils;

import static com.tvo.htc.view.component.CpnIndicator.StateIndicator.CURRENT;
import static com.tvo.htc.view.component.CpnIndicator.StateIndicator.NORMAL;
import static com.tvo.htc.view.component.CpnIndicator.StateIndicator.SELECTED;

/**
 * Created by ThinhNH on 09/08/2016.
 */
public class CpnIndicator extends LinearLayout {

    private View mRootView;
    private LinearLayout mLlContent;
    private IndicatorListener mListener;
    private boolean isClickItem;
    private boolean isUseState;
    private int positionSelected;
    private Drawable mIconNormal;
    private Drawable mIconSelected;
    private Drawable mIconCurrent;

    private ViewPager mViewpager;
    private int mMaxItem;

    public interface IndicatorListener {
        void onIndicatorSelected(int position);
    }

    public enum StateIndicator {
        NORMAL,
        CURRENT,
        SELECTED
    }

    public CpnIndicator(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CpnIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CpnIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable
                .CpnIndicator, defStyleAttr, 0);

        isClickItem = a.getBoolean(R.styleable.CpnIndicator_isClickItem, true);

        isUseState = a.getBoolean(R.styleable.CpnIndicator_isUseState, false);
        mIconNormal = a.getDrawable(R.styleable.CpnIndicator_icNormal);
        mIconCurrent = a.getDrawable(R.styleable.CpnIndicator_icCurrent);
        mIconSelected = a.getDrawable(R.styleable.CpnIndicator_icSelected);

        LayoutInflater inflater = LayoutInflater.from(context);
        mRootView = inflater.inflate(R.layout.cpn_indicator, this, true);

        mLlContent = mRootView.findViewById(R.id.llContent);
        validateDrawable();
        updateView();
    }

    private void validateDrawable() {
        if (mIconNormal == null) {
            mIconNormal = Utils.getDrawable(getContext(), R.drawable.ic_indicator_none);
        }
        if (mIconCurrent == null) {
            mIconCurrent = Utils.getDrawable(getContext(), R.drawable.ic_indicator_current);
        }
        if (mIconSelected == null) {
            mIconSelected = Utils.getDrawable(getContext(), R.drawable.ic_indicator_selected);
        }
    }

    private void updateView() {
        mLlContent.removeAllViews();
        for (int i = 0; i < mMaxItem; i++) {
            final int index = i;
            CpnIndicatorItem cpnIndicatorItem = new CpnIndicatorItem(getContext());
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (index == 0) {
                layoutParams.setMargins(0, 0, 0, 0);
                cpnIndicatorItem.setImageDrawable(getDrawableBySate(CURRENT));
            } else {
                int marginLeft = (int) getResources().getDimension(R.dimen.margin_padding_0_5x);
                layoutParams.setMargins(marginLeft, 0, 0, 0);
                cpnIndicatorItem.setImageDrawable(getDrawableBySate(NORMAL));
            }
            cpnIndicatorItem.setLayoutParams(layoutParams);
            cpnIndicatorItem.setEnabled(isClickItem);
            if (isClickItem) {
                cpnIndicatorItem.setOnClickListener(v -> {
                    if (mListener!=null) mListener.onIndicatorSelected(index);
//                            mListener.onTabSelected(mStrings.get(index));
                });
            }
            mLlContent.addView(cpnIndicatorItem);
        }
    }

    private void clearSelected() {
        if (mLlContent == null) {
            return;
        }

        for (int i = 0; i < mLlContent.getChildCount(); i++) {
            CpnIndicatorItem view = (CpnIndicatorItem) mLlContent.getChildAt(i);
            view.changeState(getDrawableBySate(NORMAL));
        }
    }

    public void setCurrentIndicator(int position) {
        if (mLlContent == null) {
            return;
        }
        if (!isUseState) clearSelected();
        CpnIndicatorItem view = getIndicatorByPosition(position);
        if (view != null) {
            view.changeState(getDrawableBySate(CURRENT));
        }
    }

    public void setSelectedIndicator(int position) {
        if (mLlContent == null) return;
        CpnIndicatorItem view = getIndicatorByPosition(position);
        if (view != null) {
            view.changeState(getDrawableBySate(SELECTED));
        }
    }

    public void setNormalIndicator(int i) {
        if (mLlContent == null) return;
        CpnIndicatorItem view = getIndicatorByPosition(i);
        if (view != null) {
            view.changeState(getDrawableBySate(NORMAL));
        }
    }

    private CpnIndicatorItem getIndicatorByPosition(int position) {
        for (int i = 0; i < mLlContent.getChildCount(); i++) {
            CpnIndicatorItem view = (CpnIndicatorItem) mLlContent.getChildAt(i);
            if (i == position) {
                return view;
            }
        }
        return null;
    }

    private Drawable getDrawableBySate(StateIndicator state) {
        switch (state) {
            case SELECTED:
                return mIconSelected;
            case CURRENT:
                return mIconCurrent;
            case NORMAL:
                return mIconNormal;
            default:
                return null;

        }
    }

    public void setTabListener(IndicatorListener listener) {
        mListener = listener;
    }

    public void setViewPager(ViewPager viewPager) {
        setViewPager(viewPager, 0);
    }

    public void setViewPager(ViewPager viewPager, int max) {
        if (viewPager == null) {
            return;
        }
        mMaxItem = max;
        mViewpager = viewPager;
        if (mMaxItem <= 0) {
            mMaxItem = mViewpager.getAdapter().getCount();
        }
        updateView();
        mViewpager.removeOnPageChangeListener(mInternalPageChangeListener);
        mViewpager.addOnPageChangeListener(mInternalPageChangeListener);
    }


    private final ViewPager.OnPageChangeListener mInternalPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {

            if (mViewpager.getAdapter() == null || mViewpager.getAdapter().getCount() <= 0) {
                return;
            }
            int realPosition = position % mMaxItem;
            setCurrentIndicator(realPosition);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}
