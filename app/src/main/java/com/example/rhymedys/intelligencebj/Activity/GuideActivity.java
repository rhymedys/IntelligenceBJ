package com.example.rhymedys.intelligencebj.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.rhymedys.intelligencebj.R;
import com.example.rhymedys.intelligencebj.adapter.GuideApater;
import com.example.rhymedys.intelligencebj.util.ContantValues;
import com.example.rhymedys.intelligencebj.util.LogUtils;
import com.example.rhymedys.intelligencebj.util.SpUtils;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private static final String TAG = "GuideActivity";
    private Context context;
    private ViewPager vp_guide;
    private LinearLayout ll_container;
    private Button btn_trial;
    private int[] drawableGuide;
    private List<ImageView> listBackground;
    private ImageView iv;
    private GuideApater guideApater;
    private ImageView ivPoint;
    private ImageView iv_redpoint;
    private int intDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        this.context = this;


        initUI();
        initData();
    }

    private void initData() {
        drawableGuide = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
        listBackground = new ArrayList<>();
        ImageView ivBackground = null;
        ImageView ivPoint = null;
        for (int i = 0; i < drawableGuide.length; i++) {
            ivBackground = new ImageView(context);
            ivBackground.setBackgroundResource(drawableGuide[i]);
            listBackground.add(ivBackground);

            ivPoint = new ImageView(context);
            ivPoint.setImageResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                params.leftMargin += 10;
            }
            ivPoint.setLayoutParams(params);
            ll_container.addView(ivPoint);
        }


        guideApater = new GuideApater(context, listBackground);
        vp_guide.setAdapter(guideApater);
    }

    private void initUI() {
        vp_guide = (ViewPager) findViewById(R.id.viewpager_guide);
        ll_container = (LinearLayout) findViewById(R.id.ll_step);
        btn_trial = (Button) findViewById(R.id.btn_trial);
        iv_redpoint = (ImageView) findViewById(R.id.iv_redpoint);

        iv_redpoint.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    //layout方法执行结束后的回调
                    @Override
                    public void onGlobalLayout() {
                        iv_redpoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        //获取两点之间的距离
                        intDistance = ll_container.getChildAt(1).getLeft() - ll_container.getChildAt(0).getLeft();
                    }
                });


        vp_guide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (intDistance != 0) {

                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) iv_redpoint.getLayoutParams();
                    layoutParams.leftMargin = (int) (intDistance * positionOffset) + position * intDistance;
                    iv_redpoint.setLayoutParams(layoutParams);

                }
            }

            @Override
            public void onPageSelected(int position) {
                try {
                    if (vp_guide.getAdapter().getCount() - 1 == position) {
                        btn_trial.setVisibility(View.VISIBLE);
                    } else {
                        btn_trial.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    LogUtils.i(TAG, "vp_guide.getAdapter() fail!!!!!!!!!");
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn_trial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtils.putBoolean(context,ContantValues.IS_FIRST_INIT, false);
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }
        });

    }
}
