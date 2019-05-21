package com.example.zhaomingyang.music;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Start_animation extends AppCompatActivity {

    Animation animation;
    LinearLayout tv_lin;
    LinearLayout tv_hide_lin;
    ImageView logo;
    int sum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_animation);

//        Thread newThread; //声明一个子线程
//        newThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
        //这里写入子线程需要做的工作
        tv_lin= (LinearLayout) findViewById(R.id.text_lin);//要显示的字体
        tv_hide_lin= (LinearLayout) findViewById(R.id.text_hide_lin);//所谓的布
        logo= (ImageView) findViewById(R.id.image);//图片
        animation = AnimationUtils.loadAnimation(Start_animation.this,R.anim.splash);
        logo.startAnimation(animation);//开始执行动画
//            }
//        });
        //动画回调函数//
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                //第一个动画执行开始后执行第二个动画就是那个字体显示那部分
                animation=AnimationUtils.loadAnimation(Start_animation.this,R.anim.text_splash_position);
                tv_lin.startAnimation(animation);
                animation=AnimationUtils.loadAnimation(Start_animation.this,R.anim.text_canvas);
                tv_hide_lin.startAnimation(animation);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //change to mainactivity

                Intent intent =new Intent(Start_animation.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

}
