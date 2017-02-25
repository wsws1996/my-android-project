package com.wang.sdcardstate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by wang on 17-2-25.
 */

public class SdcardReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
            System.out.println("说明sd卡卸载了");
        } else if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
            System.out.println("说明sd卡挂载了");
        }
    }
}
