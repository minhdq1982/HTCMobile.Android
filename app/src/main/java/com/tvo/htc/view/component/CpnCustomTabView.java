package com.tvo.htc.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tvo.htc.R;

import java.util.List;

/**
 * Created by ThinhNH on 09/08/2016.
 */
public class CpnCustomTabView extends LinearLayout {

    private View mRootView;
    private List<String> mStrings;
    private LinearLayout mLlContent;
    private TabListener mListener;
    private boolean isClickItem;
    private int positionSelected;
    private CpnCustomButtonAction customButtonAction;

    public interface TabListener {
        void onTabSelected(String tabTitle);
    }

    public CpnCustomTabView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CpnCustomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CpnCustomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable
                .CpnCustomTabView, defStyleAttr, 0);

        isClickItem = a.getBoolean(R.styleable.CpnCustomTabView_isClickItem, true);

        LayoutInflater inflater = LayoutInflater.from(context);
        mRootView = inflater.inflate(R.layout.cpn_custom_tab_view, this, true);

        mLlContent = mRootView.findViewById(R.id.llContent);

        updateView();
    }

    private void updateView() {
        mLlContent.removeAllViews();
        if (mStrings == null || mStrings.isEmpty()) {
            return;
        }

        for (int i = 0; i < mStrings.size(); i++) {
            final int index = i;
            customButtonAction = new CpnCustomButtonAction
                    (getContext());
            LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            customButtonAction.setLayoutParams(layoutParams);
            customButtonAction.setText(mStrings.get(index));
            if (index == 0) {
                customButtonAction.setMargin(0, 0, 0, 0);
                customButtonAction.setSelected(true);
            }
            if (isClickItem) {
                customButtonAction.setOnClickListener(v -> {
                    clearSelected();
                    customButtonAction.setSelected(true);
                    if (mListener != null) {
                        setTabSelected(mStrings.get(index));
//                            mListener.onTabSelected(mStrings.get(index));
                    }
                });
            }
            mLlContent.addView(customButtonAction);
        }
    }

    private void clearSelected() {
        if (mLlContent == null) {
            return;
        }

        for (int i = 0; i < mLlContent.getChildCount(); i++) {
            View view = mLlContent.getChildAt(i);
            if (view instanceof CpnCustomButtonAction) {
                view.setSelected(false);
            }
        }
    }

    public void setTabSelected(String tabTitle) {
        if (mLlContent == null) {
            return;
        }

        clearSelected();
        for (int i = 0; i < mLlContent.getChildCount(); i++) {
            View view = mLlContent.getChildAt(i);
            if (view instanceof CpnCustomButtonAction) {
                if (tabTitle.equals(((CpnCustomButtonAction) view).getText())) {
                    view.setSelected(true);
                    mListener.onTabSelected(tabTitle);
                    break;
                }
            }
        }
    }

    public void setTabListener(TabListener listener) {
        mListener = listener;
    }

    public void setContent(List<String> strings) {
        mStrings = strings;
        updateView();
    }

}
