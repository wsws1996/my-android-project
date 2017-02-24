package com.wang.customsms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText et_number;
    private EditText et_sms_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_number = (EditText) findViewById(R.id.et_number);
        et_sms_content = (EditText) findViewById(R.id.et_sms_content);


    }

    public void insert(View view) {
        Intent intent = new Intent(this, SmsTemplateActivity.class);
        startActivityForResult(intent, 2);
    }

    public void add(View view) {
        Intent intent = new Intent(this, ContactActivity.class);
//        startActivity(intent);
        startActivityForResult(intent, 1);
    }

    public void click(View view) {
        String number = et_number.getText().toString().trim();
        String content = et_sms_content.getText().toString().trim();
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> divideMessages = smsManager.divideMessage(content);
        for (String div :
                divideMessages) {
            smsManager.sendTextMessage(number, null, div, null, null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            String phone = data.getStringExtra("name");
            et_number.setText(phone);
        } else if (requestCode == 2) {
            String smscontent = data.getStringExtra("smscontent");
            et_sms_content.setText(smscontent);
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

}
