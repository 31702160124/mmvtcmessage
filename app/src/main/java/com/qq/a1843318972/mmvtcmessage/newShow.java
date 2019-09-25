package com.qq.a1843318972.mmvtcmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class newShow extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopbar(isImmerse(), newShowColor(getApplicationContext()));
        setContentView(R.layout.activity_new_show);
        TopBar setBack = (TopBar) findViewById(R.id.set_back);
        setBack.setTitle(this, "设置");
        setTopBar(setBack);
        setBack.getLeftBtnImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMain();
            }
        });
    }

    //监听返回键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goMain();
    }
}
