package com.example.rhymedys.intelligencebj.util;

import android.content.Context;

/**
 * Created by Rhymedys on 2016/9/25.
 * 网络缓存工具类
 */

public class CacheUtils {
    /**
     * 以url为key，以json为value，保存在本地
     *
     * @param url
     * @param value
     */
    public static void setCache(Context context, String url, String value) {
        SpUtils.putString(context, url, value);
    }

    /**
     * 以url为key，以json为value，保存在本地
     *
     * @param url
     */
    public static String getCache(Context context, String url) {
        return
                SpUtils.getString(context, url, null);
    }

}
