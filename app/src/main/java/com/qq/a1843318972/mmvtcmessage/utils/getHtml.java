package com.qq.a1843318972.mmvtcmessage.utils;

import android.app.Activity;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.Toast;

import com.qq.a1843318972.mmvtcmessage.Adapter.newsListAdapter;
import com.qq.a1843318972.mmvtcmessage.entity.newsListItem;
import com.qq.a1843318972.mmvtcmessage.newsList.newsList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class getHtml {

    private static String TAG = "gethtml";

    public static void getImgSrc(WebView webView, Activity activity, String url) {
        String json = "[\"image/1.png\",\"image/2.png\",\"image/3.png\",\"image/4.png\",\"image/5.png\"]";
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("javascript:setImg('" + json + "')");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String imgSrc = response.body().string();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
                        json2 = "[" + json2 + "]";
                        webView.loadUrl("javascript:setImg('" + json2 + "')");
                    }
                });
            }
        });
    }

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

    public static void getNewsList(Activity activity, android.os.Handler handler, String url, int whoList, ListView listView, String page_name, int id) {
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
                Document doc = Jsoup.parse(newsListContext);
                switch (whoList) {
                    case 0:
                        Elements homesize = doc.select("div#page td > span");
                        newsList.down_ID = Integer.valueOf(homesize.text());
                        if (!homesize.text().isEmpty()) {
                            handler.sendEmptyMessage(0);
                        }
                        Elements homeEsLi = doc.select("div.mt-20 ul > li");
                        for (Element homeELi : homeEsLi) {
                            Elements homeTime = homeELi.getElementsByTag("time");
                            Elements HomeName = homeELi.select("div > a");
                            newsArrayList.add(new newsListItem(homeTime.text(), HomeName.text(), "https://www.mmvtc.cn" + HomeName.attr("href")));
                        }
                        break;
                    case 1:
                        Elements jsjsize = doc.select("span#htmlPageCount");
                        newsList.down_ID = Integer.valueOf(jsjsize.text());
                        if (!jsjsize.text().isEmpty()) {
                            handler.sendEmptyMessage(0);
                        }
                        Elements esLi = doc.select("div.cbox ul > li");
                        for (Element eLi : esLi) {
                            Elements estime = eLi.getElementsByTag("span");
                            Elements esName = eLi.select("div > a");
                            newsArrayList.add(new newsListItem(estime.text(), esName.text(), "https://www.mmvtc.cn" + esName.attr("href")));
                        }
                        break;
                    case 2:
                        //扩展
                        break;
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (netWork.isNewworkConnected(activity.getApplicationContext())) {
                            listView.setAdapter(new newsListAdapter(activity.getApplicationContext(), newsArrayList, page_name, id));
                        } else {
                            Toast.makeText(activity.getApplicationContext(), "请打开网络......", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}
