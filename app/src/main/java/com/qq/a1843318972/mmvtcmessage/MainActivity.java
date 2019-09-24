package com.qq.a1843318972.mmvtcmessage;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout page_main;
    private ImageView    page_home, page_me;
    private TopBar mainSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopbar(isImmerse(), mainColor(getApplicationContext()));
        setContentView(R.layout.activity_main);
        mainSet = findViewById(R.id.main_set);
        page_main = findViewById(R.id.page_main);
        page_home = findViewById(R.id.page_home);
        page_me = findViewById(R.id.page_me);

        page_home.setOnClickListener(this);
        page_me.setOnClickListener(this);

        setTopBar(mainSet);
        mainSet.setTitle("首页");
        mainSet.getRightBtnImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SettingActivity.class);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.page_home:
                mainSet.setTitle("首页");
                //                beginTransaction.replace(R.id.ll_layout, new libFragment());
                page_home.setImageResource(R.drawable.home1);
                page_home.setPadding(0, 0, 0, 0);
                page_me.setImageResource(R.drawable.me2);
                page_me.setPadding(50, 50, 50, 50);
                break;
            case R.id.page_me:
                mainSet.setTitle("个人");
                //                beginTransaction.replace(R.id.ll_layout, new libFragment());
                page_home.setImageResource(R.drawable.home2);
                page_home.setPadding(50, 50, 50, 50);
                page_me.setImageResource(R.drawable.me1);
                page_me.setPadding(0, 0, 0, 0);
                break;
        }
    }
}
