package com.wang.otherhandller;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv_simple = (TextView) findViewById(R.id.tv_simple);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tv_simple.setText("我又被更新...");
//                    }
//                });
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv_simple.setText("我又被更新");
                    }
                }, 1000 * 5);
            }
        }).start();
    }
}
