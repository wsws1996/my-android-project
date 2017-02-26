package com.wang.dialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("警告");
        builder.setMessage("世界上最遥远的距离就是没有网络");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("点击了确定按钮");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("点击了取消按钮");
            }
        });
        builder.show();
    }

    public void click2(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择您喜欢的课程");
        final String items[] = {"android", "ios", "php", "j2ee", "c", "c++", "html"};
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "您选中了" + items[which], Toast.LENGTH_LONG)
                        .show();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void click3(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择您喜欢的水果");
        final String items[] = {"榴莲", "苹果", "葡萄", "火龙果", "荔枝", "草莓", "番茄"};

        final boolean[] checkedItems = {true, false, true, false, false, false, true};
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface
                .OnMultiChoiceClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < checkedItems.length; i++) {
                    if (checkedItems[i]) {
                        String fruit = items[i];
                        stringBuffer.append(fruit + " ");
                    }
                }
                Toast.makeText(getApplicationContext(), stringBuffer.toString(), Toast
                        .LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void click4(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("正在加载...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        progressDialog.setMax(100);
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    SystemClock.sleep(50);
                    progressDialog.setProgress(i);
                }
                progressDialog.dismiss();
            }
        }.start();
    }
}
