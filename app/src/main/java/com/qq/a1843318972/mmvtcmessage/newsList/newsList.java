package com.qq.a1843318972.mmvtcmessage.newsList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qq.a1843318972.mmvtcmessage.BaseActivity;
import com.qq.a1843318972.mmvtcmessage.R;
import com.qq.a1843318972.mmvtcmessage.TopBar;
import com.qq.a1843318972.mmvtcmessage.entity.homeItem;
import com.qq.a1843318972.mmvtcmessage.utils.getHtml;

public class newsList extends BaseActivity implements View.OnClickListener {
    private static String TAG = "newsListListview";
    @SuppressLint("StaticFieldLeak")
    public static newsList instance = null;
    private ListView newsLists;
    private ImageView up_img, down_img;
    private homeItem homeItem;
    private TextView page_id;
    private long exitTime = 0;
    private String baseUrl = "https://www.mmvtc.cn/templet/default/ShowClassPage.jsp?id=";
    private String jsjgcxUrl = "https://www.mmvtc.cn/templet/jsjgcx/ShowClass.jsp?id=";
    private String port = "&pn=";
    private int id, b = 1;
    private String page_name;
    public static int down_ID;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                page_id.setText(String.valueOf(b + "/" + down_ID));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopbar(isImmerse(), newsListColor(getApplicationContext()));
        setContentView(R.layout.activity_news_list);
        instance = this;
        Intent intent = getIntent();
        TopBar setBack = (TopBar) findViewById(R.id.set_back);
        setBack.setTitle(page_name = intent.getStringExtra("name"));
        id = intent.getIntExtra("id", 915);
        setTopBar(setBack);
        setBack.getLeftBtnImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMain();
            }
        });
        newsLists = findViewById(R.id.newsLists);
        if (page_name.contains("计算机工程系")) {
            getHtml.getNewsList(this, handler, jsjgcxUrl + id + port + b, 1, newsLists, page_name, id);
        } else {
            getHtml.getNewsList(this, handler, baseUrl + id + port + b, 0, newsLists, page_name, id);
        }
//        Toast.makeText(this, page_name, Toast.LENGTH_SHORT).show();
        page_id = findViewById(R.id.page_id);
        up_img = findViewById(R.id.up_img);
        down_img = findViewById(R.id.down_img);
        LinearLayout up_page = findViewById(R.id.up_page);
        LinearLayout down_page = findViewById(R.id.down_page);
        up_page.setOnClickListener(this);
        down_page.setOnClickListener(this);
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
                        page_id.setText(String.valueOf(b + "/" + down_ID));
                        Toast.makeText(this, "没有上一页", Toast.LENGTH_SHORT).show();
                        up_img.setBackground(getResources().getDrawable(R.drawable.up_black));
                    } else {
                        page_id.setText(String.valueOf(b + "/" + down_ID));
                        if (page_name.contains("计算机工程系")) {
                            getHtml.getNewsList(this, handler, jsjgcxUrl + id + port + b, 1, newsLists, page_name, id);
                        } else {
                            getHtml.getNewsList(this, handler, baseUrl + id + port + b, 0, newsLists, page_name, id);
                        }
                    }
                    up_img.setBackground(getResources().getDrawable(R.drawable.up_black));
                    down_img.setBackground(getResources().getDrawable(R.drawable.down_blue));
                } else {
                    page_id.setText(String.valueOf(b + "/" + down_ID));
                    if (page_name.contains("计算机工程系")) {
                        getHtml.getNewsList(this, handler, jsjgcxUrl + id + port + b, 1, newsLists, page_name, id);
                    } else {
                        getHtml.getNewsList(this, handler, baseUrl + id + port + b, 0, newsLists, page_name, id);
                    }
                    up_img.setBackground(getResources().getDrawable(R.drawable.up_blue));
                    down_img.setBackground(getResources().getDrawable(R.drawable.down_blue));
                }
                break;
            case R.id.down_page:
                ++b;
                page_id.setText(String.valueOf(b + "/" + down_ID));
                if (b >= down_ID) {
                    if (b == (down_ID + 1)) {
                        b = down_ID;
                        page_id.setText(String.valueOf(b + "/" + down_ID));
                        Toast.makeText(this, "没有下一页", Toast.LENGTH_SHORT).show();
                        down_img.setBackground(getResources().getDrawable(R.drawable.down_black));
                    } else {
                        page_id.setText(String.valueOf(b + "/" + down_ID));
                        if (page_name.contains("计算机工程系")) {
                            getHtml.getNewsList(this, handler, jsjgcxUrl + id + port + b, 1, newsLists, page_name, id);
                        } else {
                            getHtml.getNewsList(this, handler, baseUrl + id + port + b, 0, newsLists, page_name, id);
                        }
                    }
                    down_img.setBackground(getResources().getDrawable(R.drawable.down_black));
                    up_img.setBackground(getResources().getDrawable(R.drawable.up_blue));
                } else {
                    page_id.setText(String.valueOf(b + "/" + down_ID));
                    if (page_name.contains("计算机工程系")) {
                        getHtml.getNewsList(this, handler, jsjgcxUrl + id + port + b, 1, newsLists, page_name, id);
                    } else {
                        getHtml.getNewsList(this, handler, baseUrl + id + port + b, 0, newsLists, page_name, id);
                    }
                    down_img.setBackground(getResources().getDrawable(R.drawable.down_blue));
                    up_img.setBackground(getResources().getDrawable(R.drawable.up_blue));
                }
                break;
        }
    }

    //    监听返回键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goMain();
    }
}