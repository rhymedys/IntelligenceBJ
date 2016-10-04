package com.example.rhymedys.intelligencebj.bean;

import java.util.List;
import java.util.Objects;

/**
 * Created by Rhymedys on 2016/9/29.
 */

public class NewsTabBean {
    public Tab data;

    public class Tab {
        public String more;
        public List<News> news;
        public List<TopNews> topnews;
    }

    /**
     * 新闻列表对象
     */
    public class News {
        public int id;
        public String listimage;
        public String pubdate;
        public String title;
        public String type;
        public String url;

        @Override
        public String toString() {
            return "News{" +
                    "id=" + id +
                    ", listimage='" + listimage + '\'' +
                    ", pubdate='" + pubdate + '\'' +
                    ", title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }


    /**
     * 头条新闻
     */
    public class TopNews {
        public int id;
        public String pubdate;
        public String title;
        public String topimage;
        public String type;
        public String url;

        @Override
        public String toString() {
            return "TopNews{" +
                    "id=" + id +
                    ", pubdate='" + pubdate + '\'' +
                    ", title='" + title + '\'' +
                    ", topimage='" + topimage + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsTabBean{" +
                "data=" + data +
                '}';
    }
}
