package com.example.rhymedys.intelligencebj;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.example.rhymedys.intelligencebj.activity.GuideActivity;
import com.example.rhymedys.intelligencebj.activity.MainActivity;
import com.example.rhymedys.intelligencebj.util.ContantValues;
import com.example.rhymedys.intelligencebj.util.SpUtils;

public class SplashActivity extends AppCompatActivity {

    private Context context;
    private LinearLayout ll_splash;
    private Animation splash_anim;
    private boolean is_first_init;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.context = this;
        is_first_init = SpUtils.getBoolean(context, ContantValues.IS_FIRST_INIT, true);

        initUI();
    }

    /**
     * 初始化UI
     */
    private void initUI() {

        ll_splash = (LinearLayout) findViewById(R.id.ll_splash);
        splash_anim = AnimationUtils.loadAnimation(context, R.anim.anim_splash);
        ll_splash.startAnimation(splash_anim);

        //动画监听
        splash_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (is_first_init) {
                    startActivity(new Intent(context,GuideActivity.class));
                    SpUtils.putBoolean(context, ContantValues.IS_FIRST_INIT, false);
                } else {
                    startActivity(new Intent(context,MainActivity.class));
                }
                finish();
            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
