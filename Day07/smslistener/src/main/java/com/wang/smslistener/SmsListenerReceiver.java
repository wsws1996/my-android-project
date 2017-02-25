package com.wang.smslistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

/**
 * Created by wang on 17-2-25.
 */

public class SmsListenerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");

        for (Object pdu :
                pdus) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
            String body = smsMessage.getMessageBody();
            String address = smsMessage.getOriginatingAddress();
            System.out.println("body:" + body + "----" + address);
        }
    }
}
