package com.example.rhymedys.intelligencebj.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Rhymedys on 2016/9/17.
 */
public class GuideApater  extends PagerAdapter {

    private final Context context;
    private final List<ImageView> listIV;

    public GuideApater(Context context, List<ImageView> IV) {
        this.context=context;
        this.listIV =IV;
    }

    @Override
    public int getCount() {
        return listIV.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    /**
     * 初始化item布局
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = listIV.get(position);
        container.addView(imageView);
        return imageView;
    }

    /**
     * 销毁布局
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
