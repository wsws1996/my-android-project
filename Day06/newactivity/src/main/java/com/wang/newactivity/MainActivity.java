package com.wang.newactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + 119));
        startActivity(intent);
    }

    public void click2(View view) {
        Intent intent = new Intent();
        intent.setAction("com.wang.testactivity");
//        intent.setDataAndType(Uri.parse("wang1:" + 1655), "aa/bb1");
        intent.setData(Uri.parse("wang:" + 125));
        intent.addCategory("android.intent.category.DEFAULT");
        startActivity(intent);
    }

    public void click3(View view) {
        Intent intent = new Intent(this, TestActivity2.class);
//        intent.setClassName("com.wang.newactivity", "com.wang.newactivity.TestActivity2");
        startActivity(intent);
    }
}
