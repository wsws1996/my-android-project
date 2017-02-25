package com.wang.ipdail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by wang on 17-2-25.
 */

public class OutGoingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context
                .MODE_PRIVATE);


        String ipnumber = sharedPreferences.getString("ipnumber", "");

        String currentNumber = getResultData();
        System.out.println("number:" + currentNumber);

        if (currentNumber.startsWith("0")) {
            setResultData(ipnumber + currentNumber);
        }
    }
}
