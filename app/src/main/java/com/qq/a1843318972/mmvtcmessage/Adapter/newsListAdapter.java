package com.qq.a1843318972.mmvtcmessage.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qq.a1843318972.mmvtcmessage.R;
import com.qq.a1843318972.mmvtcmessage.entity.newsListItem;
import com.qq.a1843318972.mmvtcmessage.newShow;

import java.util.ArrayList;

public class newsListAdapter extends BaseAdapter {

    private ArrayList<newsListItem> newsArrayList;
    private Context context;
    private String shouListName;
    private int shouListid;
    private Activity activity;

    public newsListAdapter(Context context, ArrayList<newsListItem> newsArrayList, String shouListName, int shouListid) {
        this.newsArrayList = newsArrayList;
        this.context = context;
        this.shouListName = shouListName;
        this.shouListid = shouListid;
    }

    // 设置gridView一个多少个条目
    @Override
    public int getCount() {
        return newsArrayList.size();
    }

    // 设置每个条目的界面
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = View.inflate(context, R.layout.newsliset_item, null);
        TextView tv_date = (TextView) view.findViewById(R.id.newsLists_date);
        TextView tv_name = (TextView) view.findViewById(R.id.newsLists_name);
        LinearLayout go_news = (LinearLayout) view.findViewById(R.id.go_news);
        tv_date.setText(newsArrayList.get(position).getDate());
        tv_name.setText(newsArrayList.get(position).getTitleNames());
        go_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newsArrayList.get(position).getNewsUrl() != null) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(context, newShow.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("id", shouListid);
                            intent.putExtra("url", newsArrayList.get(position).getNewsUrl());
                            intent.putExtra("name", shouListName);
                            context.startActivity(intent);
                        }
                    });
                }
            }
        });
        return view;
    }

    //  后面两个方法暂时不需要设置
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}

