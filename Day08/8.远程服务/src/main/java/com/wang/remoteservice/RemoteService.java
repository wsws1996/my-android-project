package com.wang.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by wang on 17-3-4.
 */

public class RemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void testMethod() {
        System.out.println("我是远程服务里面的方法");
    }

    private class MyBinder extends Iservice.Stub{
        @Override
        public void callTestMethod() {
            testMethod();
        }
    }
}
