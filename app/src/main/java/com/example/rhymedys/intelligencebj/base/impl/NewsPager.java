package com.example.rhymedys.intelligencebj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.rhymedys.intelligencebj.base.BasePager;

/**
 * Created by Rhymedys on 2016/9/24.
 */

public class NewsPager extends BasePager {

    private static final String TITLE = "新闻中心";

    public NewsPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        TextView textView = new TextView(myActivity);
        textView.setText(TITLE);
        textView.setTextColor(Color.RED);
        textView.setTextSize(22);
        textView.setGravity(Gravity.CENTER);
        tv_title.setText(TITLE);

        ib_menu.setVisibility(View.VISIBLE);
        fl_content.addView(textView);
    }


}
