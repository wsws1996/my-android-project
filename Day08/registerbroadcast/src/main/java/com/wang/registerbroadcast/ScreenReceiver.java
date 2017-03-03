package com.wang.registerbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by wang on 17-3-3.
 */

public class ScreenReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            System.out.println("锁屏了");
        } else if (Intent.ACTION_SCREEN_ON.equals(action)) {
            System.out.println("解锁了");
        }
    }
}
