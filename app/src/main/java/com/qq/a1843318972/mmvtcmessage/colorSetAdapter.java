package com.qq.a1843318972.mmvtcmessage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class colorSetAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList mListData;
    private String[] mColorList;

    public colorSetAdapter(Context context, ArrayList data, String[] colorList) {
        mContext = context;
        mListData = data;
        mColorList = colorList;
    }

    @Override
    public int getCount() {
        return mListData == null ? 0 : mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData == null ? 0 : mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.colorlist_item, viewGroup, false);
        TextView title_tips = view.findViewById(R.id.title_tips);
        TextView color_tips = view.findViewById(R.id.color_tips);
        ImageView show_tips = view.findViewById(R.id.show_tips);
        LinearLayout color_menu = view.findViewById(R.id.color_menu);
        LinearLayout color_list = view.findViewById(R.id.color_list);

        color_list.setId(i);

        for (String string : mColorList) {
            TextView textview = new TextView(mContext);
            textview.setWidth(40);
            textview.setHeight(40);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 30, 0);//4个参数按顺序分别是左上右下
            textview.setLayoutParams(layoutParams);
            textview.setBackgroundColor((Color.parseColor(string)));
            textview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (color_list.getId()) {
                        case 0:
                            BaseActivity.saveMainColor(mContext, string);
                            goActivity(MainActivity.class);
                            break;
                        case 1:
                            BaseActivity.saveSetColor(mContext, string);
                            goActivity(SettingActivity.class);
                            break;
                    }
                }
            });
            color_list.addView(textview);
        }

        title_tips.setText((String) mListData.get(i));
        show_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (color_menu.getVisibility() == View.GONE) {
                    color_menu.setVisibility(View.VISIBLE);
                    show_tips.setImageDrawable(mContext.getResources().getDrawable(R.drawable.closecolor));
                } else {
                    color_menu.setVisibility(View.GONE);
                    show_tips.setImageDrawable(mContext.getResources().getDrawable(R.drawable.showcolor));
                }
            }
        });

        switch (color_list.getId()) {
            case 0:
                color_tips.setBackgroundColor((Color.parseColor(BaseActivity.mainColor(mContext))));
                break;
            case 1:
                color_tips.setBackgroundColor((Color.parseColor(BaseActivity.setColor(mContext))));
                break;
        }
        return view;
    }

    private void goActivity(Class clazz) {
        if (mContext instanceof Activity) {
            Activity activity = (Activity) mContext;
            mContext.startActivity(new Intent(mContext, clazz));
            activity.finish();
        }
    }

}
