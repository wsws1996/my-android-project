package com.wang.localservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wang.remoteservice.Iservice;

public class MainActivity extends AppCompatActivity {

    private MyConn conn;
    private Iservice iservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("com.wang.remoteservice");

        conn = new MyConn();
        bindService(intent, conn, BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }

    public void click(View view) {
        try {
            iservice.callTestMethod();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iservice = Iservice.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
