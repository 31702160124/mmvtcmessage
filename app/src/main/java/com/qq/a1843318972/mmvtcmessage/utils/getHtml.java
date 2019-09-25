package com.qq.a1843318972.mmvtcmessage.utils;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class getHtml {

    private static String TAG = "gethtml";

    public static String getImgSrc(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        String imgSrc = client.newCall(request).execute().body().string();
        String json = "[\"https://www.mmvtc.cn/templet/default/slider/5.png\",\"https://www.mmvtc.cn/templet/default/slider/4.png\",\"https://www.mmvtc.cn/templet/default/slider/3.png\"]";
        Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        Matcher m = p.matcher(imgSrc);
        ArrayList htmlSrc = new ArrayList();
        while (m.find()) {
            //整个img标签
            if (m.group(1).split("/")[0].equals("slider"))
                htmlSrc.add("\"https://www.mmvtc.cn/templet/default/" + m.group(1) + "\"");
        }

        String json2 = "";
        for (int i = 1; i < htmlSrc.size(); i++) {
            if (i == 1)
                json2 = json2 + htmlSrc.get(0);
            json2 = json2 + "," + htmlSrc.get(i);
        }
        if (json2.equals("")) {
            return json;
        } else {
            return "[" + json2 + "]";
        }
    }

}
