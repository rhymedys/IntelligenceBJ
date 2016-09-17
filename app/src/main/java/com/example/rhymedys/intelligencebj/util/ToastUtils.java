package com.example.rhymedys.intelligencebj.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Rhymedys on 2016/9/17.
 */
public class ToastUtils {
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
