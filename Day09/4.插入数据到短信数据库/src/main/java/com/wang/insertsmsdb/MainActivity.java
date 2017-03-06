package com.wang.insertsmsdb;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        Uri uri = Uri.parse("content://sms/");
        ContentValues values = new ContentValues();
        values.put("address", "95555");
        values.put("body", "您的余额为1000000000");
        values.put("date", System.currentTimeMillis());

        getContentResolver().insert(uri, values);
    }
}
