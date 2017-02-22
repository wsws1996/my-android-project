package com.wang.rpcalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

/**
 * Created by wang on 17-2-22.
 */

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView tv_name = (TextView) findViewById(R.id.tv_name);
        TextView tv_sex = (TextView) findViewById(R.id.tv_sex);
        TextView tv_result = (TextView) findViewById(R.id.tv_result);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int sex = intent.getIntExtra("sex", 0);

        tv_name.setText(name);
        byte[] bytes = null;
        try {
            switch (sex) {
                case 1:
                    tv_sex.setText("男");
                   bytes= name.getBytes("gbk");
                    break;
                case 2:
                    tv_sex.setText("女");
                    bytes=name.getBytes("utf-8");
                    break;
                case 3:
                    tv_sex.setText("人妖");
                    bytes= name.getBytes("iso-8859-1");
                    break;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int total = 0;
        for (byte b :
                bytes) {
            int number = b & 0xff;
            total += number;
        }
        int score = Math.abs(total) % 100;
        if (score > 90) {
            tv_result.setText("优");
        } else if (score > 80) {
            tv_result.setText("良");
        } else if (score > 70) {
            tv_result.setText("中");
        } else {
            tv_result.setText("差");
        }
    }
}
