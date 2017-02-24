package com.wang.life;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("onCreate");
    }

    @Override
    protected void onDestroy() {
        System.out.println("onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        System.out.println("onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        System.out.println("onStop");
        super.onStop();
    }

    @Override
    protected void onResume() {
        System.out.println("onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("onPause");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        System.out.println("onRestart");
        super.onRestart();
    }

    public void click(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
}
