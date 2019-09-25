package com.qq.a1843318972.mmvtcmessage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qq.a1843318972.mmvtcmessage.Adapter.homeAdapter;
import com.qq.a1843318972.mmvtcmessage.config.homeConfig;
import com.qq.a1843318972.mmvtcmessage.entity.homeItem;
import com.qq.a1843318972.mmvtcmessage.fragment.page_home;
import com.qq.a1843318972.mmvtcmessage.utils.getHtml;
import com.qq.a1843318972.mmvtcmessage.utils.webViewSetting;

import java.io.IOException;
import java.util.Objects;

public class newsList extends BaseActivity implements View.OnClickListener {
    private String TAG = "newsList";
    private WebView newsListWeb;
    private homeItem homeItem;
    private long exitTime = 0;
    private LinearLayout up_page, down_page;
    private String baseUrl = "https://www.mmvtc.cn/templet/default/ShowClassPage.jsp?id=";
    private String port = "&pn=";
    private int id, b = 1;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopbar(isImmerse(), newsListColor(getApplicationContext()));
        setContentView(R.layout.activity_news_list);
        Intent intent = getIntent();
        TopBar setBack = (TopBar) findViewById(R.id.set_back);
        setBack.setTitle(intent.getStringExtra("name"));
        setTopBar(setBack);
        setBack.getLeftBtnImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMain();
            }
        });
        newsListWeb = findViewById(R.id.newsListWeb);
        newsListWeb.loadUrl(baseUrl + (id = intent.getIntExtra("id", 915)));
        newsListWeb.addJavascriptInterface(new myNewsList(), "myObj");
        webViewSetting.webviewSetting(this, newsListWeb);
        up_page = findViewById(R.id.up_page);
        down_page = findViewById(R.id.down_page);
        up_page.setOnClickListener(this);
        down_page.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.up_page:
                if (b <= 1) {
                    b = 1;
                    newsListWeb.loadUrl(baseUrl + id + port + 0);
                } else {
                    newsListWeb.loadUrl(baseUrl + id + port + (--b));
                }
                Toast.makeText(this, "没有上一页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.down_page:
                if (b > 60) {
                    Toast.makeText(this, "没有下一页" + id + b, Toast.LENGTH_SHORT).show();
                }
                newsListWeb.loadUrl(baseUrl + id + port + (++b));
                break;
        }
    }

    public void webviewDebug() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            if (0 != (getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE))

            {
                WebView.setWebContentsDebuggingEnabled(true);
            }

        }
    }

    //监听返回键
    //    @Override
    //    public void onBackPressed() {
    //        super.onBackPressed();
    //        goMain();
    //    }

    public class myNewsList {
        //将显示Toast和对话框的方法暴露给JS脚本调用
        @JavascriptInterface
        public void showimg() {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String imgSrc = null;
                    try {
                        imgSrc = getHtml.getImgSrc("https://www.mmvtc.cn/templet/default/index.jsp");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String finalImgSrc = imgSrc;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("lunbotu", "setmg: " + finalImgSrc);
                            newsListWeb.loadUrl("javascript:setImg('" + finalImgSrc + "')");
                        }
                    });
                }
            }).start();
        }
    }

    @Override
    public void onBackPressed() {
        if (newsListWeb.canGoBack()) {
            newsListWeb.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                exitTime = System.currentTimeMillis();
            } else {
                goMain();
            }

        }
    }
}
