package com.example.rhymedys.intelligencebj.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rhymedys.intelligencebj.R;
import com.example.rhymedys.intelligencebj.bean.PhotoBeans;

import org.xutils.ImageManager;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Rhymedys on 2016/10/4.
 */

public class PhotoAdapter extends BaseAdapter {

    private Activity myActivity;
    private List<PhotoBeans.News> news;
    private ImageManager imageManager;
    private ImageOptions imageOption;

    public PhotoAdapter(Activity myActivity, List<PhotoBeans.News> news) {
        this.myActivity = myActivity;
        this.news = news;

        initLoadPhotoManager();

    }

    private void initLoadPhotoManager() {
        imageManager = x.image();
        imageOption = new ImageOptions.Builder()
                .setLoadingDrawableId(R.drawable.pic_item_list_default)
                .setFailureDrawableId(R.drawable.pic_item_list_default)
                .setUseMemCache(true)
                .build();

    }

    static class ViewHolder {
        public ImageView iv_photo;
        public TextView tv_photo_title;
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public PhotoBeans.News getItem(int position) {
        return news.get(position);
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
            convertView = View.inflate(myActivity, R.layout.list_item_photos, null);
            viewHolder.iv_photo = (ImageView) convertView.findViewById(R.id.iv_pic_item);
            viewHolder.tv_photo_title = (TextView) convertView.findViewById(R.id.tv_pic_title);

            convertView.setTag(viewHolder);
        }
        viewHolder.tv_photo_title.setText(getItem(position).title);
        imageManager.bind(viewHolder.iv_photo, getItem(position).listimage, imageOption);

        return convertView;
    }
}
