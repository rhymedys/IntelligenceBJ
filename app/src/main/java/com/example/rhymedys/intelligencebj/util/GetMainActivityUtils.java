package com.example.rhymedys.intelligencebj.util;

import android.app.Activity;

import com.example.rhymedys.intelligencebj.activity.MainActivity;
import com.example.rhymedys.intelligencebj.fragment.ContentFragment;
import com.example.rhymedys.intelligencebj.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by Rhymedys on 2016/9/25.
 */

public class GetMainActivityUtils {
    private static final String TAG ="GetMainActivityUtils" ;

    /**
     * 获取SlidingMenu对象
     *
     * @param activity
     * @return
     */
    public static SlidingMenu getSlidingMenu(Activity activity) {
        MainActivity tempActivity = (MainActivity) activity;
        SlidingMenu slidingMenu = tempActivity.getSlidingMenu();
        if (slidingMenu == null) {
            LogUtils.i(TAG, "SlidingMenu is null!!!!!!!!!!");
        }
        return slidingMenu;
    }

    /**
     * 获取左侧菜单栏Fragment
     *
     * @param activity
     * @return
     */
    public static LeftMenuFragment getLeftMenuFragment(Activity activity) {
        MainActivity tempActivity = (MainActivity) activity;
        LeftMenuFragment leftMenuFragment = tempActivity.getLeftMenuFragment();
        if (leftMenuFragment==null){
            LogUtils.i(TAG, "LeftMenuFragment is null!!!!!!!!!!");
        }
        return  leftMenuFragment;
    }

    /**
     * 获取主页Fragment
     *
     * @param activity
     * @return
     */
    public static ContentFragment getContentFragment(Activity activity) {
        MainActivity tempActivity = (MainActivity) activity;
        ContentFragment contentFragment = tempActivity.getContentFragment();
        if (contentFragment==null){
            LogUtils.i(TAG, "ContentFragment is null!!!!!!!!!!");
        }
        return  contentFragment;
    }
}
