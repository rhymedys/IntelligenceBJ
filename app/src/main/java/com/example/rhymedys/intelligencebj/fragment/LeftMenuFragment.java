package com.example.rhymedys.intelligencebj.fragment;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rhymedys.intelligencebj.R;
import com.example.rhymedys.intelligencebj.adapter.LeftMenuAdapter;
import com.example.rhymedys.intelligencebj.base.impl.NewsMenuDetailsPager;
import com.example.rhymedys.intelligencebj.base.impl.NewsPager;
import com.example.rhymedys.intelligencebj.bean.NewsMenuBean;
import com.example.rhymedys.intelligencebj.util.GetMainActivityUtils;
import com.example.rhymedys.intelligencebj.util.LogUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.List;

/**
 * 左侧菜单Fragment
 */
public class LeftMenuFragment extends BaseFragment {

    private static final String TAG = "LeftMenuFragment";
    private List<NewsMenuBean.NewsMenuData> data;
    private ListView lv_leftmenuitem;
    private LeftMenuAdapter leftMenuAdapter;


    @Override
    public View initView() {
        View view = View.inflate(myActivity, R.layout.fragment_left_menu, null);
        lv_leftmenuitem = (ListView) view.findViewById(R.id.lv_leftmenuitem);

        lv_leftmenuitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //第3个参数为position
                if (leftMenuAdapter != null) {
                    leftMenuAdapter.currentItemPosition = position;
                    leftMenuAdapter.notifyDataSetChanged();

                    SlidingMenu slidingMenu = GetMainActivityUtils.getSlidingMenu(myActivity);
                    if (slidingMenu == null) {
                        return;
                    }
                    slidingMenu.toggle();

                    setCurrentDetailsPager(position);

                }
            }
        });
        return view;
    }

    /**
     * 设置当前菜单详情页
     *
     * @param detailsPagerPosition
     */
    private void setCurrentDetailsPager(int detailsPagerPosition) {
        ContentFragment contentFragment = GetMainActivityUtils.getContentFragment(myActivity);
        if (contentFragment == null) {
            return;
        }
        NewsPager newspager = (NewsPager) contentFragment.getPager(ContentFragment.NEWS_PAPGER);
        if (newspager == null) {
            LogUtils.i(TAG, "newspager is null");
            return;
        } else {
            LogUtils.i(TAG, "newspager is not null");
            newspager.setCurrentDetailsPager(detailsPagerPosition);
        }
    }

    @Override
    public void initData() {

    }

    /**
     * 设置侧边菜单项目
     *
     * @param data
     */
    public void setMenuData(List<NewsMenuBean.NewsMenuData> data) {
        this.data = data;
        if (leftMenuAdapter == null||!this.data.equals(data)) {
            leftMenuAdapter = new LeftMenuAdapter(myActivity, data);
            lv_leftmenuitem.setAdapter(leftMenuAdapter);
        }
        setCurrentDetailsPager(leftMenuAdapter.currentItemPosition);
    }
}
