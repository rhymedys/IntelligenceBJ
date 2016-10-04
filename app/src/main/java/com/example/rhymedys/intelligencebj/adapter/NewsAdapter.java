package com.example.rhymedys.intelligencebj.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rhymedys.intelligencebj.R;
import com.example.rhymedys.intelligencebj.bean.NewsTabBean;
import com.example.rhymedys.intelligencebj.util.ContantValues;
import com.example.rhymedys.intelligencebj.util.LogUtils;
import com.example.rhymedys.intelligencebj.util.SpUtils;

import org.xutils.ImageManager;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;


/**
 * Created by Rhymedys on 2016/9/30.
 */

public class NewsAdapter extends BaseAdapter {


    private static final String TAG = "NewsAdapter";
    private final Activity myActivity;
    private final List<NewsTabBean.News> newsList;
    private ImageManager imageManager;
    private ImageOptions imageOption;


    static class ViewHolder {
        private ImageView iv_image;
        private TextView tv_news_title;
        private TextView tv_date;
    }

    public NewsAdapter(Activity myActivity, List<NewsTabBean.News> newsList) {
        this.myActivity = myActivity;
        this.newsList = newsList;


        initLoadingPicConfig();
    }

    private void initLoadingPicConfig() {
        imageManager = x.image();
        imageOption = new ImageOptions.Builder().setLoadingDrawableId(R.drawable.pic_item_list_default)
                .setFailureDrawableId(R.drawable.pic_item_list_default)
                .setUseMemCache(true)
                .build();

    }

    @Override
    public int getCount() {
        LogUtils.i(TAG, String.valueOf(newsList.size()));
        return newsList.size();
    }

    @Override
    public NewsTabBean.News getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            viewHolder = new ViewHolder();
            convertView = View.inflate(myActivity, R.layout.list_item_news, null);
            viewHolder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            viewHolder.tv_news_title = (TextView) convertView.findViewById(R.id.tv_news_title);
            convertView.setTag(viewHolder);
        }
        NewsTabBean.News news = newsList.get(position);
        imageManager.bind(viewHolder.iv_image, news.listimage, imageOption);
        viewHolder.tv_date.setText(news.pubdate);
        viewHolder.tv_news_title.setText(news.title);

        String read_IDS = SpUtils.getString(myActivity, ContantValues.READ_IDS, "");
        if (read_IDS.contains(news.id + "")) {
            viewHolder.tv_date.setTextColor(Color.GRAY);
            viewHolder.tv_news_title.setTextColor(Color.GRAY);
        }
        return convertView;
    }
}
