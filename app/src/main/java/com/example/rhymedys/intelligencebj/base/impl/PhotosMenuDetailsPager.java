package com.example.rhymedys.intelligencebj.base.impl;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.rhymedys.intelligencebj.R;
import com.example.rhymedys.intelligencebj.adapter.PhotoAdapter;
import com.example.rhymedys.intelligencebj.base.BaseMenuDetailsPager;
import com.example.rhymedys.intelligencebj.bean.NewsMenuBean;
import com.example.rhymedys.intelligencebj.bean.PhotoBeans;
import com.example.rhymedys.intelligencebj.util.ContantValues;
import com.example.rhymedys.intelligencebj.util.LogUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 侧边栏照片详情菜单
 * Created by Rhymedys on 2016/9/25.
 */

public class PhotosMenuDetailsPager extends BaseMenuDetailsPager {

    private ImageButton ib_view_type;
    @ViewInject(R.id.lv_photos)
    private ListView lv_photos;
    @ViewInject(R.id.gv_photos)
    private GridView gv_photos;
    private final static String TAG = "PhotosMenuDetailsPager";
    private List<PhotoBeans.News> news;
    private PhotoAdapter photoAdapter;


    public PhotosMenuDetailsPager(Activity activity, NewsMenuBean.NewsMenuData data,ImageButton ib) {
        super(activity);
        ib_view_type=ib;
    }

    @Override
    public View initView() {
        View view = View.inflate(myActivity, R.layout.pager_photos_menu_details, null);
        x.view().inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        getDataFromServer();
        ib_view_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lv_photos.getVisibility() == View.INVISIBLE) {
                    gv_photos.setVisibility(View.INVISIBLE);
                    lv_photos.setVisibility(View.VISIBLE);
                } else {
                    gv_photos.setVisibility(View.VISIBLE);
                    lv_photos.setVisibility(View.INVISIBLE);
                }
            }
        });
    }


    private void getDataFromServer() {
        x.http().get(new RequestParams(ContantValues.PHOTOS_URL), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)) {
                    processData(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.i(TAG, "onError");
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

    private void processData(String strJson) {
        Gson gson = new Gson();
        PhotoBeans photoBeans = gson.fromJson(strJson, PhotoBeans.class);
        news = photoBeans.data.news;

        photoAdapter = new PhotoAdapter(myActivity, news);
        lv_photos.setAdapter(photoAdapter);
        gv_photos.setAdapter(photoAdapter);


    }
}
