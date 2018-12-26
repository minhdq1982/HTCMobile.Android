package com.tvo.htc.view.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * Created by ThinhNH on 09/08/2016.
 */
public class CpnIndicatorItem extends android.support.v7.widget.AppCompatImageView {

    public CpnIndicatorItem(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CpnIndicatorItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CpnIndicatorItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        updateView();
    }

    private void updateView() {

    }

    public void changeState(Drawable drawable) {
        setImageDrawable(drawable);
    }
}
