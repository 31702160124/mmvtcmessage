package com.qq.a1843318972.mmvtcmessage.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qq.a1843318972.mmvtcmessage.R;

public class homeAdapter extends BaseAdapter {

    private int[] imageId;
    private String[] names;
    private Context context;

    public homeAdapter(Context context, int[] imageId, String[] names) {
        this.context = context;
        this.imageId = imageId;
        this.names = names;
    }

    // 设置gridView一个多少个条目
    @Override
    public int getCount() {
        return names.length;
    }

    // 设置每个条目的界面
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = View.inflate(context, R.layout.xx_item, null);
        ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        iv_icon.setImageResource(imageId[position]);
        tv_name.setText(names[position]);
        return view;
    }

    //  后面两个方法暂时不需要设置
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}

