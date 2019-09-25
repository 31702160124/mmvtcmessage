package com.qq.a1843318972.mmvtcmessage.fragment;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.qq.a1843318972.mmvtcmessage.Adapter.homeAdapter;
import com.qq.a1843318972.mmvtcmessage.R;
import com.qq.a1843318972.mmvtcmessage.config.userConfig;
import com.qq.a1843318972.mmvtcmessage.utils.getHtml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;

public class page_home extends Fragment {
    private String TAG = "paghome";
    private WebView webView;
    private GridView xx_home;
    private static final String APP_CACHE_DIRNAME = "/webcache"; // web缓存目录

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        webView = view.findViewById(R.id.web);
        webView.loadUrl("file:///android_asset/lunbotu.html");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setUseWideViewPort(true);//设定支持viewport
        settings.setLoadWithOverviewMode(true);   //自适应屏幕
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(false);//设定缩放
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启DOM storage API 功能
        settings.setDomStorageEnabled(true);
        // 开启database storage API功能
        settings.setDatabaseEnabled(true);
        String cacheDirPath = getContext().getFilesDir().getAbsolutePath() + APP_CACHE_DIRNAME;
        Log.i("cachePath", cacheDirPath);
        // 设置数据库缓存路径
        settings.setAppCachePath(cacheDirPath);
        settings.setAppCacheEnabled(true);
        Log.i("databasepath", settings.getDatabasePath());
        webView.addJavascriptInterface(new MyObject(), "myObj");
        xx_home = view.findViewById(R.id.xx_home);
        xx_home.setAdapter(new homeAdapter(getContext(), userConfig.imageId, userConfig.names));
        xx_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), userConfig.names[i], Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e(TAG, "onItemClick: " + userConfig.names[i] + "  " + i);
            }
        });
        return view;
    }

    public class MyObject {

        //将显示Toast和对话框的方法暴露给JS脚本调用
        @JavascriptInterface
        public void showimg() {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String imgSrc = null;
                    try {
                        imgSrc = getHtml.getHtmlContent("https://www.mmvtc.cn/templet/default/index.jsp");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String finalImgSrc = imgSrc;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("lunbotu", "setmg: " + finalImgSrc);
                            webView.loadUrl("javascript:setImg('" + finalImgSrc + "')");
                        }
                    });
                }
            }).start();
        }

    }

    public void webviewDebug() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            if (0 != (Objects.requireNonNull(getActivity()).getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE))

            {
                WebView.setWebContentsDebuggingEnabled(true);
            }

        }
    }

}
