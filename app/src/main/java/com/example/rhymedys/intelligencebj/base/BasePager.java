package com.example.rhymedys.intelligencebj.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.rhymedys.intelligencebj.R;

import static android.view.View.inflate;

/**
 * Created by Rhymedys on 2016/9/24.
 * 标签页基本类
 */

public class BasePager {

    public Activity myActivity;
    public View view;
    public TextView tv_title;
    public ImageButton ib_menu;
    public View mRootView;
    public FrameLayout fl_content;

    public BasePager(Activity activity) {
        this.myActivity = activity;
        mRootView = initView();

    }

    public View initView() {
        view = inflate(myActivity, R.layout.basepager, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        ib_menu = (ImageButton) view.findViewById(R.id.ib_menu);
        fl_content = (FrameLayout) view.findViewById(R.id.fl_content);

        ib_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    public void initData() {

    }

}
