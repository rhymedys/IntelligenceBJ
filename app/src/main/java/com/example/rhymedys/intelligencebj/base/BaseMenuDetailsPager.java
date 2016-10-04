package com.example.rhymedys.intelligencebj.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by Rhymedys on 2016/9/25.
 * public  Activity myActivity;
 * public  View mRootView;
 */

public abstract class BaseMenuDetailsPager {
    public Activity myActivity;
    public View mRootView;

    public boolean isNewMenuDetailsFisrt=false;


    public BaseMenuDetailsPager(Activity activity) {
        myActivity = activity;
        mRootView = initView();
    }

    /**
     * 自动加载
     * @return
     */
    public abstract View initView();

    /**
     * 手动加载
     */
    public void initData() {

    }
}
