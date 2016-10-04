package com.example.rhymedys.intelligencebj.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rhymedys.intelligencebj.R;
import com.example.rhymedys.intelligencebj.bean.NewsTabBean;

import org.xutils.ImageManager;
import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Rhymedys on 2016/9/29.
 */

public class TopNewsAdapter extends PagerAdapter {

    private final Activity myActivity;
    private final List<NewsTabBean.TopNews> topNewsList;
    private ImageManager imageManager;
    private ImageView imageView;
    private ImageOptions imageOption;

    public TopNewsAdapter(Activity activity, List<NewsTabBean.TopNews> topNewsList) {
        this.myActivity = activity;
        this.topNewsList = topNewsList;

        initLoadingPicConfig();

    }

    private void initLoadingPicConfig() {
        imageManager = x.image();
        imageOption = new ImageOptions.Builder()
                .setLoadingDrawableId(R.drawable.topnews_item_default)
                .setFailureDrawableId(R.drawable.topnews_item_default)
                .setUseMemCache(true)
                .build();
    }

    @Override
    public int getCount() {
        return topNewsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        imageView = new ImageView(myActivity);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        String topImageURL = topNewsList.get(position).topimage;
        imageManager.bind(imageView, topImageURL, imageOption);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
