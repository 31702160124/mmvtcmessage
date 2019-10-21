package com.qq.a1843318972.mmvtcmessage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.qq.a1843318972.mmvtcmessage.newsList.newsList;
import com.qq.a1843318972.mmvtcmessage.utils.getHtml;
import com.qq.a1843318972.mmvtcmessage.utils.webViewSetting;

public class newShow extends BaseActivity {
    private static String TAG = "newShow";
    private ProgressDialog progressDialog;
    private WebView newShowwebView;
    private String newShowUrl, showNewsName;
    private int id;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopbar(isImmerse(), newShowColor(getApplicationContext()));
        setContentView(R.layout.activity_new_show);
        TopBar setBack = (TopBar) findViewById(R.id.set_back);
        intent = getIntent();
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
                if (newShowwebView!=null){
                    newShowwebView.destroy();
                }
                finish();
            }
        });
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading,不要急");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        newShowUrl = intent.getStringExtra("url");
        newShowwebView = findViewById(R.id.newshowweb);
        Log.e(TAG, "onResponse: " + showNewsName);
        if (showNewsName.contains("计算机工程系")) {
            getHtml.getNewsShow(this, newShowwebView, newShowUrl, 1, progressDialog);
        } else {
            getHtml.getNewsShow(this, newShowwebView, newShowUrl, 0, progressDialog);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                newShowwebView.loadUrl("file:///android_asset/newShow.html");
            }
        });
        webViewSetting.webviewSetting(this, newShowwebView);
    }


    //监听返回键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent(getApplicationContext(), newsList.class);
        intent.putExtra("name", showNewsName);
        intent.putExtra("id", id);
        startActivity(intent1);
        if (newShowwebView!=null){
            newShowwebView.destroy();
        }
        finish();
    }

}
