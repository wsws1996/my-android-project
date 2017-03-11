package com.wang.timer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer timer;
    private TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("我被执行了");
            }
        };
        timer.schedule(task, 5000, 1000);
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        task.cancel();
        super.onDestroy();
    }
}
