package com.wang.newactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wang on 17-2-22.
 */

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent = getIntent();
        Uri data = intent.getData();
        String scheme = data.getScheme();
        System.out.println("data-:" + scheme);
    }
}
