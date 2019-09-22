package com.qq.a1843318972.mmvtcmessage;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class welcomeActivity extends AppCompatActivity {

    private String TAG = "welcomeAct";
    private LinearLayout title, pikaqiu;
    private ImageView logo;
    private TextView title1;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        logo = findViewById(R.id.logo);
        pikaqiu = findViewById(R.id.pikaqiu);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500L);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ValueAnimator rValue = ValueAnimator.ofInt(0, 360);
                            rValue.setDuration(1000L);
                            rValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    float fractionValue = animation.getAnimatedFraction();
                                    title.setAlpha(fractionValue);
                                }
                            });
                            rValue.setInterpolator(new DecelerateInterpolator());
                            rValue.start();
                        }
                    });
                    Thread.sleep(800L);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ValueAnimator rValue = ValueAnimator.ofInt(0, 360);
                            rValue.setDuration(1000L);
                            rValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    float fractionValue = animation.getAnimatedFraction();
                                    pikaqiu.setAlpha(fractionValue);
                                }
                            });
                            rValue.setInterpolator(new DecelerateInterpolator());
                            rValue.start();
                        }
                    });
                    Thread.sleep(1000L);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ValueAnimator rValue = ValueAnimator.ofInt(0, 360);
                            rValue.setDuration(1000L);
                            rValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    int rotateValue = (Integer) animation.getAnimatedValue();
                                    logo.setVisibility(View.VISIBLE);
                                    logo.setRotation(rotateValue);
                                }
                            });
                            rValue.setInterpolator(new DecelerateInterpolator());
                            rValue.start();
                        }
                    });
                    Thread.sleep(1000L);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ValueAnimator vValue = ValueAnimator.ofFloat(1.0f, 0.6f, 1.2f, 1.0f, 0.6f, 1.2f, 1.0f);
                            vValue.setDuration(1000L);
                            vValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    float scale = (Float) animation.getAnimatedValue();
                                    title1.setVisibility(View.VISIBLE);
                                    title1.setScaleX(scale);
                                    title1.setScaleY(scale);
                                }
                            });
                            vValue.setInterpolator(new LinearInterpolator());
                            vValue.start();
                        }
                    });
                    Thread.sleep(800L);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}