package com.tvo.htc.view.main.survey.listener;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.android.lib.util.Logger;

/**
 * Create by Ngocji on 11/30/2018
 **/


public class OnSwipeGestureDetector implements View.OnTouchListener {
    private final GestureDetector gestureDetector;

    public OnSwipeGestureDetector(Context ctx) {
        gestureDetector = new GestureDetector(ctx, new GestureDetectorListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }


    class GestureDetectorListener extends android.view.GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 10;
        private static final int SWIPE_VELOCITY_THRESHOLD = 10;

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeLeft();
                        } else {
                            onSwipeRight();
                        }
                    }
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeTop();
                    } else {
                        onSwipeBottom();
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public void onSwipeRight() {
        Logger.e("Swipe right");
    }

    public void onSwipeLeft() {
        Logger.e("Swipe Left");
    }

    public void onSwipeTop() {
        Logger.e("Swipe Top");
    }

    public void onSwipeBottom() {
        Logger.e("Swipe bottom");
    }
}
