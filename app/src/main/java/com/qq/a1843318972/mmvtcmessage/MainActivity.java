package com.qq.a1843318972.mmvtcmessage;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopbar(isImmerse(), mainColor(getApplicationContext()));
        setContentView(R.layout.activity_main);
        TopBar mainSet = findViewById(R.id.main_set);
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
}
