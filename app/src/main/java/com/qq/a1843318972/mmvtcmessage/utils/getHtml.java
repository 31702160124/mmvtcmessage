package com.qq.a1843318972.mmvtcmessage.utils;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class getHtml {

    private static String TAG = "gethtml";

    public static String getHtmlContent(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        String imgSrc = client.newCall(request).execute().body().string();
        Log.e(TAG, String.valueOf(imgSrc));
        Document document = Jsoup.parse(imgSrc);
        Log.e(TAG, String.valueOf(document));
        String json = "[\"https://www.mmvtc.cn/templet/default/slider/5.png\",\"https://www.mmvtc.cn/templet/default/slider/4.png\",\"https://www.mmvtc.cn/templet/default/slider/3.png\"]";
        return json;
    }

}
