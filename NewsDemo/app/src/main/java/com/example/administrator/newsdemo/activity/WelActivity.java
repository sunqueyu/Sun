package com.example.administrator.newsdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.administrator.newsdemo.R;

import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.android.api.JPushInterface;

/**
 * 类的用途：
 * Created by Administrator on 2017/3/17.
 */

public class WelActivity extends Activity {

    private int time = 3;
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (time > 1) {
                        time--;
                    } else {
                        timer.cancel();
                        Intent intent = new Intent(WelActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        timer.schedule(task, 1000, 1000);

        //初始化JPush
        JPushInterface.init(this);
        //设置debug模式
        JPushInterface.setDebugMode(true);
    }
}
