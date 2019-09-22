package com.qq.a1843318972.mmvtcmessage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class SettingActivity extends BaseActivity {

    private Switch isWelcome, isImmerse;
    private TextView main_color, set_color, msg_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopbar(isImmerse(), "#f1ae29");
        setContentView(R.layout.activity_setting);
        TopBar setBack = findViewById(R.id.set_back);
        setBack.setTitle("设置");
        setTopBar(setBack);
        setBack.getLeftBtnImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MainActivity.class);
                finish();
            }
        });

        //初始化switch
        initSwitch();

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
            }
        });

    }

    private void initSwitch() {

        isWelcome = findViewById(R.id.isWelcome);
        isImmerse = findViewById(R.id.isImmerse);

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
    }

    //监听返回键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(MainActivity.class);
        finish();
    }

    //是否开启欢迎页
    private void saveisWelcome(Boolean isWelcome) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userConfig", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isWelcome", isWelcome);
        editor.commit();
    }

    //是否开启状态栏沉浸
    private void saveisImmerse(int isImmerse) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userConfig", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("isImmerse", isImmerse);
        editor.commit();
    }
}
