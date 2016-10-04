package com.example.rhymedys.intelligencebj.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.rhymedys.intelligencebj.R;
import com.example.rhymedys.intelligencebj.activity.NewsDetailsActivity;
import com.example.rhymedys.intelligencebj.adapter.NewsAdapter;
import com.example.rhymedys.intelligencebj.adapter.TopNewsAdapter;
import com.example.rhymedys.intelligencebj.bean.NewsMenuBean;
import com.example.rhymedys.intelligencebj.bean.NewsTabBean;
import com.example.rhymedys.intelligencebj.util.CacheUtils;
import com.example.rhymedys.intelligencebj.util.ContantValues;
import com.example.rhymedys.intelligencebj.util.GetMainActivityUtils;
import com.example.rhymedys.intelligencebj.util.LogUtils;
import com.example.rhymedys.intelligencebj.util.SpUtils;
import com.example.rhymedys.intelligencebj.util.ToastUtils;
import com.example.rhymedys.intelligencebj.view.RefreshListView;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.CirclePageIndicator;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Rhymedys on 2016/9/27.
 */

public class BaseTabDetailsPager extends BaseMenuDetailsPager {

    private static final String TAG = "BaseTabDetailsPager";
    private static final int NET_ERROR = 201;
    private static final int SET_TOP_NEWS_DATA = 301;
    private static final int UPDATE_NEWS_ADAPTER = 302;
    private static final int NEXT_PIC = 303;

    private final NewsMenuBean.ChildrenData mTabItemData;

    @ViewInject(R.id.vp_topnews)
    private ViewPager vp_topnews;

    @ViewInject(R.id.lv_toutiao)
    private RefreshListView lv_toutiao;

    @ViewInject(R.id.tv_title)
    private TextView tv_Top_title;

    @ViewInject(R.id.indicator_title)
    private CirclePageIndicator indicator_title;

    private String myUrl;
    private String moreURL;
    private List<NewsTabBean.TopNews> topNewsList;
    private TopNewsAdapter topNewsAdapter;
    private String cache;
    private List<NewsTabBean.News> newsList;
    private NewsAdapter newsAdapter;


    private Handler handler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NET_ERROR:
                    ToastUtils.showToast(myActivity, "网络错误");
                    break;
                case SET_TOP_NEWS_DATA:
                    //ViewPager
                    if (topNewsList != null && topNewsAdapter == null) {
                        LogUtils.i(TAG, "topNewsList!=null  and  topNewsAdapter==null" + vp_topnews);
                        //初始化标题
                        tv_Top_title.setText(topNewsList.get(0).title);

                        topNewsAdapter = new TopNewsAdapter(myActivity, topNewsList);
                        vp_topnews.setAdapter(topNewsAdapter);
                        indicator_title.setViewPager(vp_topnews);
                        indicator_title.setSnap(true);
                        indicator_title.setCurrentItem(0);

                        indicator_title.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                tv_Top_title.setText(topNewsList.get(position).title);
                                SlidingMenu slidingMenu = GetMainActivityUtils.getSlidingMenu(myActivity);
                                if (slidingMenu != null) {
                                    if (vp_topnews.getCurrentItem() == 0 && isNewMenuDetailsFisrt) {
                                        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                                    } else {
                                        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                                    }
                                }
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                    }

                    if (newsList != null && newsAdapter == null) {
                        newsAdapter = new NewsAdapter(myActivity, newsList);
                        lv_toutiao.setAdapter(newsAdapter);
                    }


                    break;
                case UPDATE_NEWS_ADAPTER:
                    if (newsAdapter != null) {
                        LogUtils.i(TAG, "UPDATE_NEWS_ADAPTER");
                        newsAdapter.notifyDataSetChanged();
                        lv_toutiao.onRefreshComplete();
                    }
                    break;
                case NEXT_PIC:
                    int temp = vp_topnews.getCurrentItem();
                    temp++;
                    if (temp > topNewsList.size() - 1) {
                        temp = 0;
                    }
                    vp_topnews.setCurrentItem(temp);
                    handler.sendEmptyMessageDelayed(NEXT_PIC, 3000);
                    break;
                default:
                    break;
            }
        }
    };


    public BaseTabDetailsPager(Activity activity, NewsMenuBean.ChildrenData mTabMenuItemList) {
        super(activity);
        this.mTabItemData = mTabMenuItemList;
        myUrl = ContantValues.SERVER_URL + mTabMenuItemList.url;
        LogUtils.i(TAG, myUrl);
    }

    @Override
    public View initView() {
        View mainView = View.inflate(myActivity, R.layout.layout_tab_details, null);
        x.view().inject(this, mainView);

        View myHeaderView = View.inflate(myActivity, R.layout.list_item_news_header, null);
        x.view().inject(this, myHeaderView);
        lv_toutiao.addHeaderView(myHeaderView);

        View myFooterView = View.inflate(myActivity, R.layout.layout_footer_list, null);
        x.view().inject(this, myFooterView);
        lv_toutiao.addFooterView(myFooterView);


        //下拉刷新
        lv_toutiao.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtils.i(TAG, "onRefreshing");
                getDataFromServer();
                handler.sendEmptyMessage(UPDATE_NEWS_ADAPTER);
            }

            @Override
            public void onLoadMore() {
                LogUtils.i(TAG, "onLoadMore ");
                LogUtils.i(TAG, moreURL);
                if (!TextUtils.isEmpty(moreURL)) {
                    x.http().get(new RequestParams(moreURL), new org.xutils.common.Callback.CommonCallback<String>() {

                        @Override
                        public void onSuccess(String result) {
                            processData(result, true);
                            if (lv_toutiao != null) {
                                lv_toutiao.onRefreshComplete();
                            }
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            if (lv_toutiao != null) {
                                lv_toutiao.onRefreshComplete();
                            }
                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });
                } else {
                    ToastUtils.showToast(myActivity, "没有更多数据了");
                }
            }
        });

        //点击事件
        lv_toutiao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int tempPosition = position - 2;
                NewsTabBean.News news = newsList.get(tempPosition);
                String read_ids = SpUtils.getString(myActivity, ContantValues.READ_IDS, "");
                if (!read_ids.contains(news.id + "")) {
                    read_ids = read_ids + news.id + ",";
                    SpUtils.putString(myActivity, ContantValues.READ_IDS, read_ids);
                }
                TextView tv_news_title = (TextView) view.findViewById(R.id.tv_news_title);
                TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
                tv_news_title.setTextColor(Color.GRAY);
                tv_date.setTextColor(Color.GRAY);

                Intent intent = new Intent(myActivity, NewsDetailsActivity.class);
                intent.putExtra("detailsURL", news.url);
                myActivity.startActivity(intent);

            }
        });

        vp_topnews.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.sendEmptyMessageDelayed(NEXT_PIC,3000);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        handler.sendEmptyMessageDelayed(NEXT_PIC,3000);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });


        return mainView;
    }

    @Override
    public void initData() {

        cache = CacheUtils.getCache(myActivity, myUrl);
        if (!TextUtils.isEmpty(cache)) {
            processData(cache, false);
        }
        getDataFromServer();

    }

    private void getDataFromServer() {
        LogUtils.i(TAG, myUrl);
        x.http().get(new RequestParams(myUrl), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.i(TAG, "getDataFromServer onSuccess" + result);
                if (TextUtils.isEmpty(cache) || !result.equals(cache)) {
                    CacheUtils.setCache(myActivity, myUrl, result);
                    processData(result, false);
                    if (lv_toutiao != null) {
                        lv_toutiao.onRefreshComplete();
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.i(TAG, "onError");
                handler.sendEmptyMessage(NET_ERROR);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                LogUtils.i(TAG, "onFinished");
            }
        });
    }

    protected void processData(String strJson, boolean isLoadMore) {
        Gson tempGson = new Gson();
        NewsTabBean newsTabBean = tempGson.fromJson(strJson, NewsTabBean.class);
        LogUtils.i(TAG, "processData" + newsTabBean.toString());
        if (!TextUtils.isEmpty(newsTabBean.data.more)) {
            moreURL = ContantValues.CATAGORY_URL + newsTabBean.data.more;

        }
        LogUtils.i(TAG, moreURL);
        if (!isLoadMore) {
            newsList = newsTabBean.data.news;
            topNewsList = newsTabBean.data.topnews;

            handler.sendEmptyMessage(SET_TOP_NEWS_DATA);
            handler.sendEmptyMessageDelayed(NEXT_PIC, 3000);
        } else {
            List<NewsTabBean.News> moewNews = newsTabBean.data.news;
            newsList.addAll(moewNews);
            newsAdapter.notifyDataSetChanged();
        }
    }
}

