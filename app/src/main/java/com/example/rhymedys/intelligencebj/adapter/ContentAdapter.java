package com.example.rhymedys.intelligencebj.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhymedys.intelligencebj.base.BasePager;

import java.util.List;

/**
 * Created by Rhymedys on 2016/9/24.
 */

public class ContentAdapter extends PagerAdapter {

    private final Activity acitvity;
    private final List<BasePager> myPagerList;
    private BasePager basePager;

    public ContentAdapter(Activity acitvity, List<BasePager> myPagerList) {
        this.acitvity = acitvity;
        this.myPagerList = myPagerList;
    }

    @Override
    public int getCount() {
        return myPagerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        basePager = myPagerList.get(position);
//        basePager.initData();
        View view = basePager.mRootView;
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }



}
