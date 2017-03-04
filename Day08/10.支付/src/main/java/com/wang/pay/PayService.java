package com.wang.pay;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by wang on 17-3-4.
 */

public class PayService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public boolean pay(String username, String pwd, int money) {
        System.out.println("1.验证用户名和密码");
        System.out.println("2.验证手机是否携带病毒");
        System.out.println("3.加密处理");
        if ("abc".equals(username) && "123".equals(pwd) && money < 5000) {
            return true;
        } else {
            return false;
        }
    }

    private class MyBinder extends Iservice.Stub {

        @Override
        public boolean callPay(String name, String pwd, int money) throws RemoteException {
            return pay(name, pwd, money);
        }
    }
}
