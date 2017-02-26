package com.wang.sendorderedbroadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        Intent intent = new Intent();
        intent.setAction("com.wang.orderedbroadcast");
        sendOrderedBroadcast(intent, null, new FinalReceiver(), null, 1, "有序广播1000", null);
    }
}
