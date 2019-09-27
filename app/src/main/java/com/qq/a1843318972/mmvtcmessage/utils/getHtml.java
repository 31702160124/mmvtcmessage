package com.qq.a1843318972.mmvtcmessage.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ListView;

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
                        if (imgSrc.contains("茂名职业技术学院")) {
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
                        } else {
                            webView.loadUrl("javascript:setImg('" + json + "')");
                        }
                    }
                });
            }
        });
    }

    public static void getNewsShow(Activity activity, WebView webView, String url, int whoList, ProgressDialog progressDialog) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                progressDialog.dismiss();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String json = "<h1 style=\"text-align: center\">404 Not Found</h1>" + url;
                        webView.loadUrl("javascript:setNews('" + json.toString() + "')");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String newsShowContext = response.body().string();
                String json = "<h1 style=\"text-align: center\">404 Not Found</h1>" + url;
                Document doc = Jsoup.parse(newsShowContext);
                switch (whoList) {
                    case 0:
                        Elements homeDiv = doc.select("div.job__top__right");
                        Log.e(TAG, "onResponse: " + homeDiv.html());
                        if (!homeDiv.html().isEmpty()) {
                            json = homeDiv.html().toString();
                        }
                        break;
                    case 1:
                        Elements jsjDiv = doc.select("div.container");
                        if (!jsjDiv.html().isEmpty()) {
                            json = jsjDiv.html().toString();
                        }
                        break;
                    case 2:
                        //扩展
                        break;
                }

                String finalJson = json;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        webView.loadUrl("javascript:setNews('" + finalJson.toString() + "')");
                    }
                });
            }
        });
    }

    public static void getNewsList(Activity activity, android.os.Handler handler, String url, int whoList, ListView listView, String page_name, int id, ProgressDialog progressDialog) {
        ArrayList<newsListItem> newsArrayList = new ArrayList<newsListItem>();
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                progressDialog.dismiss();
                newsArrayList.add(new newsListItem(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(System.currentTimeMillis())), "请打开网络"));
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
                        progressDialog.dismiss();
                        listView.setAdapter(new newsListAdapter(activity.getApplicationContext(), newsArrayList, page_name, id));
                    }
                });
            }
        });
    }

    //    public static String getImgSrc(String url) throws IOException {
    //        OkHttpClient client = new OkHttpClient();
    //        Request request = new Request.Builder().url(url).build();
    //        String imgSrc = client.newCall(request).execute().body().string();
    //        String json = "[\"https://www.mmvtc.cn/templet/default/slider/5.png\",\"https://www.mmvtc.cn/templet/default/slider/4.png\",\"https://www.mmvtc.cn/templet/default/slider/3.png\"]";
    //        Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
    //        Matcher m = p.matcher(imgSrc);
    //        ArrayList htmlSrc = new ArrayList();
    //        while (m.find()) {
    //            //整个img标签
    //            if (m.group(1).split("/")[0].equals("slider"))
    //                htmlSrc.add("\"https://www.mmvtc.cn/templet/default/" + m.group(1) + "\"");
    //        }
    //
    //        String json2 = "";
    //        for (int i = 1; i < htmlSrc.size(); i++) {
    //            if (i == 1)
    //                json2 = json2 + htmlSrc.get(0);
    //            json2 = json2 + "," + htmlSrc.get(i);
    //        }
    //        if (json2.equals("")) {
    //            return json;
    //        } else {
    //            return "[" + json2 + "]";
    //        }
    //    }
}
