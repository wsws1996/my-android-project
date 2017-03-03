package com.wang.receiveorderedbroadcast2;

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
        String content = getResultData();
        Toast.makeText(context, "2：" + content, Toast.LENGTH_LONG).show();

        setResultData((Integer.parseInt(content)-100)+"");
    }
}
