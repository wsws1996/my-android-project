package com.wang.baidumusic;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Iservice iservice;
    private MyConn conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MusicService.class);

        startService(intent);

        conn = new MyConn();
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    public void click1(View view) {
        iservice.callPlayMusic();
    }

    public void click2(View view) {
        iservice.callPauseMusic();
    }

    public void click3(View view) {
        iservice.callRePlayMusic();
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }

    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iservice = (Iservice) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
