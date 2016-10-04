package com.example.rhymedys.intelligencebj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.rhymedys.intelligencebj.base.BaseMenuDetailsPager;
import com.example.rhymedys.intelligencebj.bean.NewsMenuBean;

import java.util.List;

/**
 * 侧边栏互动详情菜单
 * Created by Rhymedys on 2016/9/25.
 */

public class InteractMenuDetailsPager extends BaseMenuDetailsPager {


    public InteractMenuDetailsPager(Activity activity, NewsMenuBean.NewsMenuData data) {
        super(activity);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(myActivity);
        textView.setText("InteractMenuDetailsPager");
        textView.setTextColor(Color.RED);
        textView.setTextSize(22);
        textView.setGravity(Gravity.CENTER);

        return textView;
    }
}
