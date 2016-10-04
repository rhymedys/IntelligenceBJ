package com.example.rhymedys.intelligencebj.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rhymedys.intelligencebj.R;
import com.example.rhymedys.intelligencebj.fragment.ContentFragment;
import com.example.rhymedys.intelligencebj.fragment.LeftMenuFragment;
import com.example.rhymedys.intelligencebj.util.LogUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import cn.sharesdk.framework.ShareSDK;


public class MainActivity extends AppCompatActivity {

    private static final String TAG_LEFTMENU_FRAGMENT = "leftmenu_fragment";
    private static final String TAG_MAIN_FRAGMENT = "main_fragment";
    private static final String TAG ="MainActivity ";
    private Context context;
    private SlidingMenu slidingMenu;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;

        initUI();


    }

    private void initUI() {
        newSlidingMenu();
        initFragment();
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_leftmenu, new LeftMenuFragment(), TAG_LEFTMENU_FRAGMENT);
        fragmentTransaction.replace(R.id.fl_main, new ContentFragment(), TAG_MAIN_FRAGMENT);
        fragmentTransaction.commit();
    }

    /**
     * 初始化一个SlidingMenu
     */
    private void newSlidingMenu() {
        slidingMenu = new SlidingMenu(context);
        slidingMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
//        slidingMenu.setShadowDrawable(R.drawable.shadow);

        // 设置滑动菜单视图的宽度
//        slidingMenu.setBehindWidth(100);
        slidingMenu.setBehindOffset(100);
//        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        slidingMenu.setMenu(R.layout.left_menu);
    }

    public SlidingMenu getSlidingMenu() {
        if (slidingMenu != null) {
            return slidingMenu;
        } else {
            return null;
        }
    }

    public LeftMenuFragment getLeftMenuFragment() {
        LeftMenuFragment tempFragment = null;
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        tempFragment = (LeftMenuFragment) fragmentManager.findFragmentByTag(TAG_LEFTMENU_FRAGMENT);
        return tempFragment;
    }

    public ContentFragment getContentFragment() {
        ContentFragment tempFragment = null;
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        tempFragment = (ContentFragment) fragmentManager.findFragmentByTag(TAG_MAIN_FRAGMENT);
        return tempFragment;
    }
}
