package com.wang.purchase;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wang.pay.Iservice;

public class MainActivity extends AppCompatActivity {

    private MyConn myConn;
    private Iservice iservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("com.wang.pay");
        myConn = new MyConn();
        bindService(intent, myConn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(myConn);
        super.onDestroy();
    }

    public void click(View view) {
        try {
            boolean result = iservice.callPay("abcdd", "123", 100);
            if (result) {
                Toast.makeText(getApplicationContext(), "支付成功", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "支付失败", Toast.LENGTH_LONG).show();
            }
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
