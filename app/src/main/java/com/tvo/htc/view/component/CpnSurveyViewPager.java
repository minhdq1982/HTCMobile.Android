package com.tvo.htc.view.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.android.lib.util.Logger;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;

/**
 * Create by Ngocji on 11/23/2018
 **/


public class CpnSurveyViewPager extends ViewPager {
    private float initialXValue;
    private SwipeDirection disableDirection = SwipeDirection.RIGHT;
    private boolean canSwipeDirection;
    private boolean fixSwipeLeftFirst;

    public CpnSurveyViewPager(@NonNull Context context) {
        super(context);
    }

    public CpnSurveyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (canSwipeDirection || detectEmptyMotion(ev)) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (canSwipeDirection || detectEmptyMotion(event)) {
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }


    private boolean detectEmptyMotion(MotionEvent event) {
        switch (event.getAction() & event.getActionMasked()) {
            case ACTION_DOWN:
                Logger.e(event.getAction() + "x: " + event.getX());
            case ACTION_POINTER_DOWN:
                initialXValue = event.getX();
                break;
            case ACTION_MOVE:
                float diffX = event.getX() - initialXValue;
                if (diffX > 0) {
                    initialXValue = event.getX();
                }
                switch (disableDirection) {
                    case ALL:
                        return false;
                    case LEFT:
                        return !(diffX > 0);
                    case RIGHT:
                        return !(diffX < -5);
                }
                break;
        }
        return fixSwipeLeftFirst;
    }

    public void setEnableSwipeLeft(boolean enable) {
        fixSwipeLeftFirst = enable;
    }

    public void setEnableCanSwipe(boolean enable) {
        canSwipeDirection = enable;
    }

    public boolean getEnableSwipe() {
        return canSwipeDirection;
    }
}

enum SwipeDirection {
    ALL, LEFT, RIGHT
}
