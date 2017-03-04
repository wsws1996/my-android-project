package com.wang.whybindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by wang on 17-3-4.
 */

public class TestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void test(int value) {
        if (value > 1000) {
            Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "失败", Toast.LENGTH_LONG).show();
        }
    }

    public void playMaJiang() {
        System.out.println("打麻将");
    }

    public void 洗桑拿() {
        System.out.println("洗桑拿");
    }

    private class MyBinder extends Binder implements Iservice {
        public void callTest(int value) {
            test(value);
        }

        public void callPlayMaJiang() {
            playMaJiang();
        }

        public void callXiSangNa() {
            洗桑拿();
        }
    }
}
