package com.qq.a1843318972.mmvtcmessage.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.qq.a1843318972.mmvtcmessage.Adapter.newsListAdapter;
import com.qq.a1843318972.mmvtcmessage.entity.newsListItem;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
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

    public static void getNewsList(Activity activity, String url, int nextId, ListView listView, String page_name, int id) {
        ArrayList<newsListItem> newsArrayList = new ArrayList<newsListItem>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                newsArrayList.add(new newsListItem(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(System.currentTimeMillis())), "获取失败"));
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(new newsListAdapter(activity.getApplicationContext(), newsArrayList, page_name, id));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String newsListContext = response.body().string();
                Log.e(TAG, "onResponse: " + newsListContext);

                newsArrayList.add(new newsListItem(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(System.currentTimeMillis())), "获取成功"));
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(new newsListAdapter(activity.getApplicationContext(), newsArrayList, page_name, id));
                    }
                });
            }
        });
    }

}
