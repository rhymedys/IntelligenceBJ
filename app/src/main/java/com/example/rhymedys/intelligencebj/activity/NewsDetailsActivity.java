package com.example.rhymedys.intelligencebj.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.rhymedys.intelligencebj.R;
import com.example.rhymedys.intelligencebj.util.LogUtils;
import com.example.rhymedys.intelligencebj.util.SpUtils;
import com.example.rhymedys.intelligencebj.util.ToastUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.sharesdk.framework.ShareSDK;

public class NewsDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "NewsDetailsActivity";
    private Context context;

    @ViewInject(R.id.ib_share)
    private ImageView ib_share;
    @ViewInject(R.id.ib_textsize)
    private ImageView ib_textsize;
    @ViewInject(R.id.ib_back)
    private ImageView ib_back;
    @ViewInject(R.id.wb_view)
    private WebView wb_view;
    @ViewInject(R.id.pb_loading)
    private ProgressBar pb_loading;

    private String newsDetailsURL;
    private int tempWhich;
    private WebSettings settings;
    private int mCurrentTextSize = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        this.context = this;
        x.view().inject(this);
        ShareSDK.initSDK(context,"17a0b3e21051e");
        newsDetailsURL = getIntent().getStringExtra("detailsURL");
        LogUtils.i(TAG, newsDetailsURL);

        initUI();
        initData();
    }

    private void initData() {
        if (!TextUtils.isEmpty(newsDetailsURL)) {
            wb_view.loadUrl("newsDetailsURL");
        } else {
            ToastUtils.showToast(context, "network error");
        }
        wb_view.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                pb_loading.setVisibility(View.INVISIBLE);
                super.onPageFinished(view, url);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pb_loading.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        wb_view.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    private void initUI() {
        ib_share.setOnClickListener(this);
        ib_textsize.setOnClickListener(this);
        ib_back.setOnClickListener(this);

        settings = wb_view.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                this.finish();
                break;
            case R.id.ib_share:
                break;
            case R.id.ib_textsize:
                showTextSizeDialog();
                break;
            case R.id.wb_view:
                break;
            default:
                break;
        }
    }

    private void showTextSizeDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("字体设置");
        String[] items = {"超大号字体", "大号字体", "正常字体", "小号字体", "超小号字体"};
        builder.setSingleChoiceItems(items, mCurrentTextSize, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tempWhich = which;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (tempWhich) {
                    case 0:
                        settings.setTextSize(WebSettings.TextSize.LARGEST);
                        break;
                    case 1:
                        settings.setTextSize(WebSettings.TextSize.LARGER);
                        break;
                    case 2:
                        settings.setTextSize(WebSettings.TextSize.NORMAL);
                        break;
                    case 3:
                        settings.setTextSize(WebSettings.TextSize.SMALLER);
                        break;
                    case 4:
                        settings.setTextSize(WebSettings.TextSize.SMALLEST);
                        break;
                    default:
                        break;

                }
                mCurrentTextSize = tempWhich;

            }
        });

        builder.setNegativeButton("取消", null);

        builder.show();
    }


}
