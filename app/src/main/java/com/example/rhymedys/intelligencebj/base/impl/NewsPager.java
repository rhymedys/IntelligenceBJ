package com.example.rhymedys.intelligencebj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.rhymedys.intelligencebj.base.BaseMenuDetailsPager;
import com.example.rhymedys.intelligencebj.base.BasePager;
import com.example.rhymedys.intelligencebj.bean.NewsMenuBean;
import com.example.rhymedys.intelligencebj.fragment.LeftMenuFragment;
import com.example.rhymedys.intelligencebj.util.CacheUtils;
import com.example.rhymedys.intelligencebj.util.ContantValues;
import com.example.rhymedys.intelligencebj.util.GetMainActivityUtils;
import com.example.rhymedys.intelligencebj.util.LogUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 底部新闻中心菜单
 * Created by Rhymedys on 2016/9/24.
 */

public class NewsPager extends BasePager {

    public static final int SET_MENU_DATA = 101;
    public static final int NEWS_MENU_DETAILS_PAGER = 0;
    public static final int TOPIC_MENU_DETAILS_PAGER = 1;
    public static final int PHOTOS_MENU_DETAILS_PAGER = 2;
    public static final int INTERACT_MENU_DETAILS_PAGER = 3;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET_MENU_DATA:
                    if (newsMenuDataList == null) {
                        return;
                    }
                    LeftMenuFragment leftMenuFragment = GetMainActivityUtils.getLeftMenuFragment(myActivity);
                    if (leftMenuFragment != null) {
                        leftMenuFragment.setMenuData(newsMenuDataList);
                    }
            }
            super.handleMessage(msg);
        }
    };

    private static final String TAG = "NewsPager  LOG";
    private List<BaseMenuDetailsPager> myBaseMenuDetailsPagerList;
    private List<NewsMenuBean.NewsMenuData> newsMenuDataList;
    private String cache;


    public NewsPager(Activity activity) {
        super(activity);
    }

    /**
     * 页面初始化数据
     */
    @Override
    public void initData() {
        ib_menu.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                cache = CacheUtils.getCache(myActivity, ContantValues.CATAGORY_URL);
                if (!TextUtils.isEmpty(cache)) {
                    processData(cache);
                }
                getDataFromServer();
            }
        }).start();
    }

    /**
     * 从服务器中获取数据 并设置缓存
     */
    private void getDataFromServer() {
        Log.i(TAG, ContantValues.CATAGORY_URL);
        RequestParams requestParams = new RequestParams(ContantValues.CATAGORY_URL);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                LogUtils.i(TAG, "onSuccess" + result);
                if (TextUtils.isEmpty(cache) || !result.equals(cache)) {
                    CacheUtils.setCache(myActivity, ContantValues.CATAGORY_URL, result);
                    processData(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.i(TAG, "onError");
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.i(TAG, "onCancelled");
            }

            @Override
            public void onFinished() {
                LogUtils.i(TAG, "onFinished");
            }
        });
    }

    /**
     * 在一个线程上解析json数据 并且设置菜单数据
     *
     * @param json
     */
    private void processData(String json) {

        //将json转化为对象
        Gson gson = new Gson();
        NewsMenuBean newsMenuBean = gson.fromJson(json, NewsMenuBean.class);
        newsMenuDataList = newsMenuBean.data;

        //添加4个详情页到myBaseMenuPager集合中去
        addDetailsPager(newsMenuDataList);

        //发送到主线程更新侧边菜单栏menuItem
        LogUtils.i(TAG, newsMenuBean.toString());
        handler.sendEmptyMessage(SET_MENU_DATA);

    }

    /**
     * 添加4个详情页到myBaseMenuPager集合中去
     *
     * @param data
     */
    private void addDetailsPager(List<NewsMenuBean.NewsMenuData> data) {
        if (data == null) {
            return;
        }
        myBaseMenuDetailsPagerList = new ArrayList<BaseMenuDetailsPager>();
        myBaseMenuDetailsPagerList.add(new NewsMenuDetailsPager(myActivity, data.get(NEWS_MENU_DETAILS_PAGER)));
        myBaseMenuDetailsPagerList.add(new TopicMenuDetailsPager(myActivity, data.get(TOPIC_MENU_DETAILS_PAGER)));
        myBaseMenuDetailsPagerList.add(new PhotosMenuDetailsPager(myActivity, data.get(PHOTOS_MENU_DETAILS_PAGER),ib_view_type));
        myBaseMenuDetailsPagerList.add(new InteractMenuDetailsPager(myActivity, data.get(INTERACT_MENU_DETAILS_PAGER)));
    }

    /**
     * 设置菜单详情页,重新为FrameLayout添加内容
     *.
     * @param detailsPagerPosition
     */
    public void setCurrentDetailsPager(int detailsPagerPosition) {
        if (myBaseMenuDetailsPagerList != null) {
            BaseMenuDetailsPager pager = myBaseMenuDetailsPagerList.get(detailsPagerPosition);
            View view = pager.mRootView;

            fl_content.removeAllViews();
            fl_content.addView(view);


            pager.initData();

            //更新Title
            if (newsMenuDataList != null) {
                tv_title.setText("新闻中心" + "——" + newsMenuDataList.get(detailsPagerPosition).title);
            }

            if (pager instanceof PhotosMenuDetailsPager)
            {
                ib_view_type.setVisibility(View.VISIBLE);
            }else {
                ib_view_type.setVisibility(View.INVISIBLE);
            }
        }
    }
}
