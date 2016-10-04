package com.example.rhymedys.intelligencebj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rhymedys.intelligencebj.R;
import com.example.rhymedys.intelligencebj.bean.NewsMenuBean;

import java.util.IllegalFormatCodePointException;
import java.util.List;

/**
 * Created by Rhymedys on 2016/9/25.
 */

public class LeftMenuAdapter extends BaseAdapter {

    private final Context context;
    private final List<NewsMenuBean.NewsMenuData> data;

    public int currentItemPosition=0;

    static class ViewHolder {
        TextView tv_menu;
    }

    public LeftMenuAdapter(Context context, List<NewsMenuBean.NewsMenuData> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public NewsMenuBean.NewsMenuData getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = View.inflate(context, R.layout.list_item_left_menu, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_menu = (TextView) convertView.findViewById(R.id.tv_menu);
            convertView.setTag(viewHolder);
        }

        viewHolder.tv_menu.setText(getItem(position).title);

        if (position==currentItemPosition)
        {
            viewHolder.tv_menu.setEnabled(true);
        }else {
            viewHolder.tv_menu.setEnabled(false);
        }

        return convertView;
    }
}
