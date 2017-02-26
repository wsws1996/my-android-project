package com.wang.receiveorderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by wang on 17-2-26.
 */

public class FirstReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String content = getResultData();
        Toast.makeText(context, "一：" + content, Toast.LENGTH_LONG).show();
        abortBroadcast();
//        setResultData("有序广播500");
    }
}
