package com.wang.autostartmoretvmain;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by wang on 17-3-5.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent moreTV = new Intent("moretv.action.applaunch");
        moreTV.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(moreTV);
    }
}
