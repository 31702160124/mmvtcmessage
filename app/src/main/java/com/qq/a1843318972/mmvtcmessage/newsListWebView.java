package com.qq.a1843318972.mmvtcmessage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qq.a1843318972.mmvtcmessage.entity.homeItem;
import com.qq.a1843318972.mmvtcmessage.utils.webViewSetting;

public class newsListWebView extends BaseActivity implements View.OnClickListener {
    private String TAG = "newsListWebView";
    private WebView newsListWeb;
    private homeItem homeItem;
    private ImageView up_img, down_img;
    private long exitTime = 0;
    private String baseUrl = "https://www.mmvtc.cn/templet/default/ShowClassPage.jsp?id=";
    private String jsjgcxUrl = "https://www.mmvtc.cn/templet/jsjgcx/ShowClass.jsp?id=";
    private int id, b = 1;
    private String page_name;

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopbar(isImmerse(), newsListColor(getApplicationContext()));
        setContentView(R.layout.activity_news_list_webview);
        Intent intent = getIntent();
        TopBar setBack = (TopBar) findViewById(R.id.set_back);
        setBack.setTitle(page_name = intent.getStringExtra("name"));
        setTopBar(setBack);
        setBack.getLeftBtnImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMain();
            }
        });
        newsListWeb = findViewById(R.id.newsListWeb);
        if (page_name.contains("计算机工程系")) {
            //            newsListWeb.loadUrl(jsjgcxUrl + (id = intent.getIntExtra("id", 1212)));
            newsListWeb.loadUrl(jsjgcxUrl + id);
        } else {
            //            newsListWeb.loadUrl(baseUrl + (id = intent.getIntExtra("id", 915)));
            newsListWeb.loadUrl(baseUrl + id);
        }
        Toast.makeText(this, page_name, Toast.LENGTH_SHORT).show();
        newsListWeb.addJavascriptInterface(new myNewsList(), "myObj");
        webViewSetting.webviewSetting(this, newsListWeb);
        up_img = findViewById(R.id.up_img);
        down_img = findViewById(R.id.down_img);
        LinearLayout up_page = findViewById(R.id.up_page);
        LinearLayout down_page = findViewById(R.id.down_page);
        up_page.setOnClickListener(this);
        down_page.setOnClickListener(this);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        String port = "&pn=";
        switch (view.getId()) {
            case R.id.up_page:
                --b;
                if (b <= 1) {
                    if (b == 0) {
                        b = 1;
                        Toast.makeText(this, "没有上一页", Toast.LENGTH_SHORT).show();
                        up_img.setBackground(getResources().getDrawable(R.drawable.up_black));
                    } else {
                        if (page_name.contains("计算机工程系")) {
                            newsListWeb.loadUrl(jsjgcxUrl + id + port + b);
                        } else {
                            newsListWeb.loadUrl(baseUrl + id + port + b);
                        }
                    }
                    up_img.setBackground(getResources().getDrawable(R.drawable.up_black));
                    down_img.setBackground(getResources().getDrawable(R.drawable.down_blue));
                } else {
                    if (page_name.contains("计算机工程系")) {
                        newsListWeb.loadUrl(jsjgcxUrl + id + port + b);
                    } else {
                        newsListWeb.loadUrl(baseUrl + id + port + b);
                    }
                    up_img.setBackground(getResources().getDrawable(R.drawable.up_blue));
                    down_img.setBackground(getResources().getDrawable(R.drawable.down_blue));
                }
                break;
            case R.id.down_page:
                ++b;
                if (b >= 6) {
                    if (b == 7) {
                        b = 6;
                        Toast.makeText(this, "没有下一页" + b, Toast.LENGTH_SHORT).show();
                        down_img.setBackground(getResources().getDrawable(R.drawable.down_black));
                    } else {
                        if (page_name.contains("计算机工程系")) {
                            newsListWeb.loadUrl(jsjgcxUrl + id + port + b);
                        } else {
                            newsListWeb.loadUrl(baseUrl + id + port + b);
                        }
                    }
                    down_img.setBackground(getResources().getDrawable(R.drawable.down_black));
                    up_img.setBackground(getResources().getDrawable(R.drawable.up_blue));
                } else {
                    if (page_name.contains("计算机工程系")) {
                        newsListWeb.loadUrl(jsjgcxUrl + id + port + b);
                    } else {
                        newsListWeb.loadUrl(baseUrl + id + port + b);
                    }
                    down_img.setBackground(getResources().getDrawable(R.drawable.down_blue));
                    up_img.setBackground(getResources().getDrawable(R.drawable.up_blue));
                }
                break;
        }
    }
    //监听返回键
    //    @Override
    //    public void onBackPressed() {
    //        super.onBackPressed();
    //        goMain();
    //    }

    public class myNewsList {

        myNewsList() {

        }

    }

}
