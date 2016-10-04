package com.example.rhymedys.intelligencebj.bean;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Rhymedys on 2016/9/25.
 */

public class NewsMenuBean {
    public int retcode;
    public List<Integer> extend;
    public List<NewsMenuData> data;

    /**
     * 侧边栏菜单
     */
    public class NewsMenuData {
        public int id;
        public String title;
        public int type;

        public List<ChildrenData> children;

        @Override
        public String toString() {
            return "NewsMenuData{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", children=" + children +
                    '}';
        }
    }

    /**
     * 页签对象
     */
    public class ChildrenData {
        public int id;
        public String title;
        public int type;
        public String url;

        @Override
        public String toString() {
            return "ChildrenData{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsMenuBean{" +
                "retcode=" + retcode +
                ", extend=" + extend +
                ", data=" + data +
                '}';
    }
}
