package com.wang.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyConn conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click1(View view) {
        Intent intent = new Intent(this, FirstService.class);
        startService(intent);
    }

    public void click2(View view) {
        Intent intent = new Intent(this, FirstService.class);
        stopService(intent);
    }

    public void click3(View view) {
        Intent intent = new Intent(this, FirstService.class);
        conn = new MyConn();
        System.out.println("bind----------" + conn);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    public void click4(View view) {
        System.out.println("unbind----------" + conn);
        unbindService(conn);
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        System.out.println("unbind");
        super.onDestroy();
    }

    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

}