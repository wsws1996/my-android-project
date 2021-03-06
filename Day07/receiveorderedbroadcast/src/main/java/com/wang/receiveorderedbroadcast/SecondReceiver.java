package com.wang.receiveorderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by wang on 17-2-26.
 */

public class SecondReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        abortBroadcast();
        String content = getResultData();
        Toast.makeText(context, "二：" + content, Toast.LENGTH_LONG).show();

        setResultData((Integer.parseInt(content)-100)+"");
    }
}
