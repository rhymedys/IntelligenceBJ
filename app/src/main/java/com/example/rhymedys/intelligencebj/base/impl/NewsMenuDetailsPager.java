package com.example.rhymedys.intelligencebj.base.impl;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import com.example.rhymedys.intelligencebj.R;
import com.example.rhymedys.intelligencebj.adapter.NewsMenuDetailsPagerAdapter;
import com.example.rhymedys.intelligencebj.base.BaseMenuDetailsPager;
import com.example.rhymedys.intelligencebj.base.BaseTabDetailsPager;
import com.example.rhymedys.intelligencebj.bean.NewsMenuBean;
import com.example.rhymedys.intelligencebj.util.GetMainActivityUtils;
import com.example.rhymedys.intelligencebj.util.LogUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 侧边栏新闻详情菜单
 * Created by Rhymedys on 2016/9/25.
 */

public class NewsMenuDetailsPager extends BaseMenuDetailsPager {


    private static final String TAG = "NewsMenuDetailsPager";
    private NewsMenuBean.NewsMenuData mNewsMenuData;
    private List<BaseTabDetailsPager> mTabMenuPagerList;
    private List<NewsMenuBean.ChildrenData> mTabMenuItemList;
    private NewsMenuDetailsPagerAdapter mMenuDetailsPagerAdapter;
    @ViewInject(R.id.vp_news_details)
    private ViewPager vp_news_details;

    @ViewInject(R.id.indicator_tabmenus)
    private TabPageIndicator indicator_tabmenus;

    @ViewInject(R.id.ib_more)
    private ImageButton ib_more;

    public NewsMenuDetailsPager(Activity activity, NewsMenuBean.NewsMenuData data) {
        super(activity);
        this.mNewsMenuData = data;
    }

    @Override
    public View initView() {
        View view = View.inflate(myActivity, R.layout.pager_news_menu_details, null);
        x.view().inject(this, view);

        return view;
    }

    @Override
    public void initData() {

        mTabMenuItemList = mNewsMenuData.children;
        mTabMenuPagerList = new ArrayList<BaseTabDetailsPager>();
        for (int i = 0; i < mTabMenuItemList.size(); i++) {
            BaseTabDetailsPager tempTabDetailsPager =
                    new BaseTabDetailsPager(myActivity, mTabMenuItemList.get(i));
            mTabMenuPagerList.add(tempTabDetailsPager);
        }


        if (mMenuDetailsPagerAdapter == null) {
            LogUtils.i(TAG, "new NewsMenuDetailsPagerAdapter()");
            mMenuDetailsPagerAdapter = new NewsMenuDetailsPagerAdapter(myActivity, mTabMenuPagerList, mTabMenuItemList);
            vp_news_details.setAdapter(mMenuDetailsPagerAdapter);
            indicator_tabmenus.setViewPager(vp_news_details);
            indicator_tabmenus.setOnPageChangeListener(
                    new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            SlidingMenu slidingMenu = GetMainActivityUtils.getSlidingMenu(myActivity);
                            if (slidingMenu != null) {
                                if (position == 0 ) {
                                    isNewMenuDetailsFisrt=true;
                                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                                } else {
                                    isNewMenuDetailsFisrt=false;
                                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                                }
                            }
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    }

            );

            ib_more.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int currentItem = vp_news_details.getCurrentItem();
                            if (currentItem < mTabMenuItemList.size() - 1) {
                                currentItem++;
                                indicator_tabmenus.setCurrentItem(currentItem);
                            }
                        }
                    }
            );
        }
    }
}

