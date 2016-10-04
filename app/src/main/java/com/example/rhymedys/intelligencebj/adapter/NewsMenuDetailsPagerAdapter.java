package com.example.rhymedys.intelligencebj.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhymedys.intelligencebj.base.BaseTabDetailsPager;
import com.example.rhymedys.intelligencebj.bean.NewsMenuBean;
import com.example.rhymedys.intelligencebj.bean.NewsTabBean;
import com.example.rhymedys.intelligencebj.util.LogUtils;

import java.util.List;

/**
 * Created by Rhymedys on 2016/9/27.
 */

public class NewsMenuDetailsPagerAdapter extends PagerAdapter {


    private static final String TAG = "NewsMenuDetailsPagerAdapter";
    private final Context context;
    private final List<BaseTabDetailsPager> mTabDetailsPagerList;
    private final List<NewsMenuBean.ChildrenData> mTabMenuItemList;


    public NewsMenuDetailsPagerAdapter(Context context, List<BaseTabDetailsPager> mTabDetailsPagerList,
                                       List<NewsMenuBean.ChildrenData> mTabMenuItemList) {
        this.context = context;
        this.mTabDetailsPagerList = mTabDetailsPagerList;
        this.mTabMenuItemList = mTabMenuItemList;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return mTabMenuItemList.get(position).title;
    }

    @Override
    public int getCount() {
        return mTabDetailsPagerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LogUtils.i(TAG, String.valueOf(mTabDetailsPagerList.size()));
        BaseTabDetailsPager mPager = mTabDetailsPagerList.get(position);
        View mRootView = mPager.mRootView;
        container.addView(mRootView);
        mPager.initData();
        return mRootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }



}
