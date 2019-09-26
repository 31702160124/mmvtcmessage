package com.qq.a1843318972.mmvtcmessage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.qq.a1843318972.mmvtcmessage.newsList.newsList;

public class newShow extends BaseActivity {

    private String newShowUrl, showNewsName;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopbar(isImmerse(), newShowColor(getApplicationContext()));
        setContentView(R.layout.activity_new_show);
        Intent intent = getIntent();
        TopBar setBack = (TopBar) findViewById(R.id.set_back);
        setBack.setTitle(showNewsName = intent.getStringExtra("name"));
        id = intent.getIntExtra("id", 915);
        setTopBar(setBack);
        setBack.getLeftBtnImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), newsList.class);
                intent.putExtra("name", showNewsName);
                intent.putExtra("id", id);
                startActivity(intent1);
                finish();
            }
        });
        newShowUrl = intent.getStringExtra("url");
        Toast.makeText(this, showNewsName + id, Toast.LENGTH_SHORT).show();
    }

    //监听返回键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        newsList.instance.finish();
        goMain();
    }
}
