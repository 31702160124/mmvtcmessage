package com.qq.a1843318972.mmvtcmessage.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qq.a1843318972.mmvtcmessage.R;
import com.qq.a1843318972.mmvtcmessage.entity.homeItem;

import java.util.ArrayList;

public class homeAdapter extends BaseAdapter {

    private ArrayList<homeItem> homeItems;
    private Context context;

    public homeAdapter(Context context, ArrayList<homeItem> homeItems) {
        this.context = context;
        this.homeItems = homeItems;
    }

    // 设置gridView一个多少个条目
    @Override
    public int getCount() {
        return homeItems.size();
    }

    // 设置每个条目的界面
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = View.inflate(context, R.layout.xx_item, null);
        ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        if (homeItems.get(position).getImage() != 0) {
            iv_icon.setImageResource(homeItems.get(position).getImage());
        }
        if (position <= 2) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv_icon.getLayoutParams();
            lp.setMargins(0, 0, 0, 20);
            iv_icon.setLayoutParams(lp);
            tv_name.setVisibility(View.GONE);
        }
        if (position == 9 || position == 10 || position == 11) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv_icon.getLayoutParams();
            lp.setMargins(0, 0, 0, 20);
            iv_icon.setLayoutParams(lp);
            tv_name.setVisibility(View.GONE);
        }
        tv_name.setText(homeItems.get(position).getNames());
        //        tv_name.setId(homeItems.get(position).getNamesId());
        return view;
    }

    //  后面两个方法暂时不需要设置
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return homeItems.get(position).getNamesId();
    }
}

