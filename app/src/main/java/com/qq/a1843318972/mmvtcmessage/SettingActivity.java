package com.qq.a1843318972.mmvtcmessage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class SettingActivity extends BaseActivity{

    private Switch isWelcome, isImmerse;
    private TextView main_color, set_color, msg_color;
    private ListView colorlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopbar(isImmerse(), setColor(getApplicationContext()));
        setContentView(R.layout.activity_setting);
        TopBar setBack = (TopBar) findViewById(R.id.set_back);
        setBack.setTitle("设置");
        setTopBar(setBack);
        setBack.getLeftBtnImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMain();
            }
        });

        initSwitch();
        initColorSet();
    }

    //页面状态栏设置
    private void initColorSet() {
        ArrayList mListData = new ArrayList();
        for (String s : getResources().getStringArray(R.array.colorset))
            mListData.add(s);
        colorlist = (ListView) findViewById(R.id.colorlist);
        colorSetAdapter colorAdapter = new colorSetAdapter(this, mListData, getResources().getStringArray(R.array.color));
        colorlist.setAdapter(colorAdapter);
    }

    //switch设置
    private void initSwitch() {

        isWelcome = (Switch) findViewById(R.id.isWelcome);
        isImmerse = (Switch) findViewById(R.id.isImmerse);

        if (isWelcome()) {
            isWelcome.setChecked(true);
        } else {
            isWelcome.setChecked(false);
        }
        if (isImmerse() == 1) {
            isImmerse.setChecked(true);
        } else if (isImmerse() == 0) {
            isImmerse.setChecked(false);
        }
        isWelcome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    saveisWelcome(true);
                } else {
                    saveisWelcome(false);
                }
            }
        });

        isImmerse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    saveisImmerse(1);
                } else {
                    saveisImmerse(0);
                }
                finish();
                startActivity(getIntent());
            }
        });
    }

    //监听返回键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goMain();
    }

    //保存是否开启欢迎页
    private void saveisWelcome(Boolean isWelcome) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userConfig", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isWelcome", isWelcome);
        editor.commit();
    }

    //保存是否状态栏沉浸
    private void saveisImmerse(int isImmerse) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userConfig", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("isImmerse", isImmerse);
        editor.commit();
    }


    private void goMain() {
        startActivity(MainActivity.class);
        finish();
    }
}
