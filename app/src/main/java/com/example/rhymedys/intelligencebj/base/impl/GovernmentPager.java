package com.example.rhymedys.intelligencebj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.rhymedys.intelligencebj.base.BasePager;

/**
 * 底部政务菜单
 * Created by Rhymedys on 2016/9/24.
 */

public class GovernmentPager extends BasePager {
    private static final String TITLE = "政务";

    public GovernmentPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        TextView textView = new TextView(myActivity);
        textView.setText(TITLE);
        tv_title.setText(TITLE);
        textView.setTextColor(Color.RED);
        textView.setTextSize(22);
        textView.setGravity(Gravity.CENTER);
        fl_content.addView(textView);

        ib_menu.setVisibility(View.VISIBLE);

    }
}
