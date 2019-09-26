package com.qq.a1843318972.mmvtcmessage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.qq.a1843318972.mmvtcmessage.newsList.newsList;
import com.qq.a1843318972.mmvtcmessage.utils.webViewSetting;

public class newShow extends BaseActivity {
    private String TAG = "newShow";
    private WebView newShowwebView;
    private String newShowUrl, showNewsName;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopbar(isImmerse(), newShowColor(getApplicationContext()));
        setContentView(R.layout.activity_new_show);
        Intent intent = getIntent();
        TopBar setBack = (TopBar) findViewById(R.id.set_back);
        setBack.setTitle(showNewsName = intent.getStringExtra("name"));
        id = intent.getIntExtra("id", 915);
        setTopBar(setBack);
        setBack.getLeftBtnImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), newsList.class);
                intent.putExtra("name", showNewsName);
                intent.putExtra("id", id);
                startActivity(intent1);
                finish();
            }
        });
        newShowUrl = intent.getStringExtra("url");
        newShowwebView = findViewById(R.id.newshowweb);
        newShowwebView.loadUrl(newShowUrl);
        //        newShowwebView.loadUrl("file:///android_asset/newShow.html");
        //        newShowwebView.addJavascriptInterface(new newsshow(), "newsshow");
        webViewSetting.webviewSetting(this, newShowwebView);
    }

    //监听返回键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        newsList.instance.finish();
        goMain();
    }

    public class newsshow {
        //将显示Toast和对话框的方法暴露给JS脚本调用
        @JavascriptInterface
        public void newsshowhtml() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    String imgSrc = "[\"https://www.mmvtc.cn/templet/default/slider/5.png\",\"https://www.mmvtc.cn/templet/default/slider/4.png\",\"https://www.mmvtc.cn/templet/default/slider/3.png\"]";
                    newShowwebView.loadUrl("javascript:setNews('" + imgSrc + "')");
                }
            });
        }

    }

}
