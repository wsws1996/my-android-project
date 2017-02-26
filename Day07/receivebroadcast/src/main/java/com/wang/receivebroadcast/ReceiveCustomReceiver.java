package com.wang.receivebroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by wang on 17-2-26.
 */

public class ReceiveCustomReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        abortBroadcast();
        String content = intent.getStringExtra("name");
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
