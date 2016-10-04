package com.example.rhymedys.intelligencebj.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.rhymedys.intelligencebj.R;
import com.example.rhymedys.intelligencebj.util.GetMainActivityUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import static android.view.View.inflate;

/**
 * Created by Rhymedys on 2016/9/24.
 * 标签页基本类
 */

public class BasePager {

    public Activity myActivity;
    private View view;
    public TextView tv_title;
    public ImageButton ib_menu;
    public View mRootView;
    public FrameLayout fl_content;
    public ImageButton ib_view_type;

    public BasePager(Activity activity) {
        this.myActivity = activity;
        mRootView = initView();

    }


    /**
     * 自动加载
     * @return
     */
    public View initView() {
        view = inflate(myActivity, R.layout.basepager, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        ib_menu = (ImageButton) view.findViewById(R.id.ib_menu);
        fl_content = (FrameLayout) view.findViewById(R.id.fl_content);
        ib_view_type = (ImageButton) view.findViewById(R.id.ib_translation_view);

        ib_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlidingMenu slidingMenu = GetMainActivityUtils.getSlidingMenu(myActivity);
                if (slidingMenu!=null){
                   slidingMenu.toggle();
                }
            }
        });

        return view;
    }

    /**
     * 手动加载
     */
    public void initData() {

    }



}
