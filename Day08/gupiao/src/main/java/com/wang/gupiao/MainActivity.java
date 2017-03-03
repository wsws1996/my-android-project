package com.wang.gupiao;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(

        ) {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(1000);
                    System.out.println("拿数据.....");
                }
            }
        }.start();
    }

}
