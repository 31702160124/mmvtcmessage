package com.qq.a1843318972.mmvtcmessage.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.qq.a1843318972.mmvtcmessage.Adapter.homeAdapter;
import com.qq.a1843318972.mmvtcmessage.R;
import com.qq.a1843318972.mmvtcmessage.config.homeConfig;
import com.qq.a1843318972.mmvtcmessage.entity.homeItem;
import com.qq.a1843318972.mmvtcmessage.newsList.newsList;
import com.qq.a1843318972.mmvtcmessage.utils.getHtml;
import com.qq.a1843318972.mmvtcmessage.utils.webViewSetting;

import java.io.IOException;
import java.util.Objects;

public class page_home extends Fragment {
    private String   TAG = "paghome";
    private WebView  pagehomewebView;
    private GridView xx_home;

    public interface myNewsList {
        public void whoNewsList(homeItem homeItem);
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        pagehomewebView = view.findViewById(R.id.pagehomeweb);
        pagehomewebView.loadUrl("file:///android_asset/lunbotu.html");
        pagehomewebView.addJavascriptInterface(new pageHome(), "pageHome");
        webViewSetting.webviewSetting(getContext(), pagehomewebView);
        xx_home = view.findViewById(R.id.xx_home);
        xx_home.setAdapter(new homeAdapter(getContext(), homeConfig.getHomeItems()));
        xx_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (homeConfig.getHomeItems().get(i).getNamesId() == 1) {
                            Toast.makeText(getContext(), "下滑查看更多哟.....", Toast.LENGTH_SHORT).show();
                        } else if (homeConfig.getHomeItems().get(i).getNamesId() == 0) {
                            String s = homeConfig.getRandmSay();
                            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                        } else {
                            String name = homeConfig.getHomeItems().get(i).getNames();
                            int id = homeConfig.getHomeItems().get(i).getNamesId();
                            Log.e(TAG, "run: " + id);
                            Intent intent = new Intent(getContext(), newsList.class);
                            intent.putExtra("name", name);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }
                });
            }
        });
        return view;
    }

    public class pageHome {

        @JavascriptInterface
        public void showimg() {
            getHtml.getImgSrc(pagehomewebView, getActivity(), "https://www.mmvtc.cn/templet/default/index.jsp");
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
