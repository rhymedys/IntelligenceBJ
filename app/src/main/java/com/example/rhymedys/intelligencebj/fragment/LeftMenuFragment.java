package com.example.rhymedys.intelligencebj.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhymedys.intelligencebj.R;

/**
 * 左侧菜单Fragment
 */
public class LeftMenuFragment extends BaseFragment {


    public LeftMenuFragment() {
        // Required empty public constructor

    }

    @Override
    public View initView() {
        View fragment_left_menu = View.inflate(myActivity, R.layout.fragment_left_menu, null);
        return fragment_left_menu;
    }

    @Override
    public void initData() {

    }

}
