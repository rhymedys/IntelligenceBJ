package com.example.rhymedys.intelligencebj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 自定义拦截分发事件ViewPager
 * Created by Rhymedys on 2016/9/24.
 */

public class NoDispatchViewPager extends ViewPager {

    private int startX;
    private int startY;


    public NoDispatchViewPager(Context context) {
        super(context);
    }

    public NoDispatchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int tempPosition = getCurrentItem();
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) getX();
                startY = (int) getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) getX();
                int endY = (int) getY();
                int dX = endX - startX;
                int dY = endY - startY;
                if (Math.abs(dY) < Math.abs(dX)) {
                    if (dX > 0) {
                        if (tempPosition == 0) {
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    } else {
                        if (tempPosition == getAdapter().getCount() - 1) {
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    }
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
