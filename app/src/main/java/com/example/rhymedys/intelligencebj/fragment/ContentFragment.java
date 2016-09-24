package com.example.rhymedys.intelligencebj.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.rhymedys.intelligencebj.R;
import com.example.rhymedys.intelligencebj.activity.MainActivity;
import com.example.rhymedys.intelligencebj.adapter.ContentAdapter;
import com.example.rhymedys.intelligencebj.base.BasePager;
import com.example.rhymedys.intelligencebj.base.impl.GovernmentPager;
import com.example.rhymedys.intelligencebj.base.impl.HomePager;
import com.example.rhymedys.intelligencebj.base.impl.IntelligenceServicePager;
import com.example.rhymedys.intelligencebj.base.impl.NewsPager;
import com.example.rhymedys.intelligencebj.base.impl.SettingPager;
import com.example.rhymedys.intelligencebj.view.NoScollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import static com.example.rhymedys.intelligencebj.R.id.rg_menu;

/**
 * 主内容Fragment
 */
public class ContentFragment extends BaseFragment {

    //    private RadioButton rb_home;
//    private RadioButton rb_government;
//    private RadioButton rb_intelligence_service;
//    private RadioButton rb_news;
//    private RadioButton rb_setting;


    private NoScollViewPager vp_fragment_content;

    private List<BasePager> mPagerList;
    private RadioGroup rg_menu;
    private final static int HOME_PAPGER = 0;
    private final static int NEWS_PAPGER = 1;
    private final static int INTELLIGENCE_SERVICE_PAPGER = 2;
    private final static int GOVERNMENT_PAPGER = 3;
    private final static int SETTING_PAPGER = 4;

    @Override
    public View initView() {
        View view = View.inflate(myActivity, R.layout.fragment_content, null);
/*        rb_home = (RadioButton) view.findViewById(R.id.rb_home);
        rb_government = (RadioButton) view.findViewById(R.id.rb_government);
        rb_intelligence_service = (RadioButton) view.findViewById(R.id.rb_intelligence_service);
        rb_news = (RadioButton) view.findViewById(R.id.rb_news);
        rb_setting = (RadioButton) view.findViewById(R.id.rb_setting);*/
        rg_menu = (RadioGroup) view.findViewById(R.id.rg_menu);
        rg_menu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_government:
                        vp_fragment_content.setCurrentItem(GOVERNMENT_PAPGER);
                        break;
                    case R.id.rb_home:
                        vp_fragment_content.setCurrentItem(HOME_PAPGER);
                        break;
                    case R.id.rb_news:
                        vp_fragment_content.setCurrentItem(NEWS_PAPGER);
                        break;
                    case R.id.rb_setting:
                        vp_fragment_content.setCurrentItem(SETTING_PAPGER);
                        break;
                    case R.id.rb_intelligence_service:
                        vp_fragment_content.setCurrentItem(INTELLIGENCE_SERVICE_PAPGER);
                        break;
                    default:
                        break;
                }


            }
        });

        vp_fragment_content = (NoScollViewPager) view.findViewById(R.id.vp_fragment_content);

        vp_fragment_content.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mPagerList != null) {
                    mPagerList.get(position).initData();
                }
                if (position == 0 || position == 4) {
                    setSlidingMenuEnable(false);
                } else {
                    setSlidingMenuEnable(true);
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        return view;
    }

    /**
     * 开启禁用侧边栏
     *
     * @param b
     */
    private void setSlidingMenuEnable(boolean b) {
        MainActivity tempActivity = (MainActivity) myActivity;
        SlidingMenu slidingMenu = tempActivity.getSlidingMenu();
        if (b) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }

    }

    @Override
    public void initData() {
        addMyPagerList();
        if (mPagerList != null) {
            vp_fragment_content.setAdapter(new ContentAdapter(myActivity, mPagerList));
            //首次加载ViewPager
            mPagerList.get(HOME_PAPGER).initData();
            setSlidingMenuEnable(false);
        }
    }

    private void addMyPagerList() {
        mPagerList = new ArrayList<BasePager>();
        mPagerList.add(new HomePager(myActivity));
        mPagerList.add(new IntelligenceServicePager(myActivity));
        mPagerList.add(new GovernmentPager(myActivity));
        mPagerList.add(new NewsPager(myActivity));
        mPagerList.add(new SettingPager(myActivity));
    }
}
