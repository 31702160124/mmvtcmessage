package com.qq.a1843318972.mmvtcmessage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public abstract class BaseActivity extends AppCompatActivity {

    private static String string;

    public void setTopBar(TopBar topBar) {
        if (string != null)
            topBar.setBackgroundColor((Color.parseColor(string)));
    }

    //沉浸式状态栏 有颜色时 0 背景色 1 状态栏沉浸
    public void initTopbar(int i, String color) {
        //4.4以上设置状态栏为透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 5.0以上系统状态栏透明，
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (i == 0)
                window.setStatusBarColor(Color.TRANSPARENT);//设置状态栏颜色和主布局背景颜色相同
            if (i == 1)
                if (color == null) {
                    window.setStatusBarColor(Color.parseColor("#529bff"));//设置状态栏为指定颜色
                } else {
                    string = color;
                    window.setStatusBarColor(Color.parseColor(color));//设置状态栏为指定颜色
                }
        }
    }

    public void startActivity(Class clazz) {
        startActivity(new Intent(getApplicationContext(), clazz));
    }

    public Boolean isWelcome() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userConfig", Context.MODE_PRIVATE);
        Boolean isWelcome = sharedPreferences.getBoolean("isWelcome", true);
        return isWelcome;
    }

    public int isImmerse() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userConfig", Context.MODE_PRIVATE);
        int isImmerse = sharedPreferences.getInt("isImmerse", 1);
        return isImmerse;
    }

}
