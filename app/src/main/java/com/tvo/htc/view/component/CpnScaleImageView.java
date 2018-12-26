package com.tvo.htc.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.tvo.htc.R;

/**
 * Created by ThinhNH on 10/08/2016
 * display and control action on navigation bar
 */
public class CpnScaleImageView extends AppCompatImageView {
    private float mScale = 1f;

    public CpnScaleImageView(Context context) {
        super(context);
        initView(null);
    }

    public CpnScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public CpnScaleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable
                    .CpnScaleImageView, 0, 0);
            mScale = a.getFloat(R.styleable.CpnScaleImageView_scale, 1f);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, (int) (widthMeasureSpec * mScale));
        int width = getMeasuredWidth();
        setMeasuredDimension(width, (int) (width * mScale));
    }
}
