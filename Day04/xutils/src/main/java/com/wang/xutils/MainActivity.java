package com.wang.xutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void download(View view) {
        EditText et_url = (EditText) findViewById(R.id.et_url);
        String url = et_url.getText().toString().trim();
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.download(url, "/sdcard/j2se/j2se8.zip", new RequestCallBack<File>() {

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                System.out.println("total:" + total + ";current:" + current);
                super.onLoading(total, current, isUploading);
            }

            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                System.out.println(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }
}
