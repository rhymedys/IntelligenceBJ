package com.example.rhymedys.intelligencebj.bean;

import android.widget.ListView;

import java.util.List;

/**
 * Created by Rhymedys on 2016/10/4.
 */

public class PhotoBeans {
    public Data data;

    public class Data {
        public List<News> news;
        public String title;
    }


    public class News {
        public int id;
        public String listimage;
        public String title;
    }
}
