package com.wang.whybindservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Iservice myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, TestService.class);
        MyConn conn = new MyConn();
        bindService(intent, conn, BIND_AUTO_CREATE);

    }

    public void click(View view) {
        myBinder.callTest(1020000);
        myBinder.callPlayMaJiang();
    }

    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (Iservice) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
