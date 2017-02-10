package com.wang.xml;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wang.xml.util.SmsUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        Button bt_backup = (Button) findViewById(R.id.bt_backup);
        Button bt_restore = (Button) findViewById(R.id.bt_restore);
        bt_backup.setOnClickListener(this);
        bt_restore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_backup:
                if (SmsUtils.backupSms(mContext)) {
                    Toast.makeText(mContext, "短信备份成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "短信备份失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_restore:
                int result = SmsUtils.restoreSms(mContext);
                if (result != -1) {
                    Toast.makeText(mContext, "成功恢复" + result + "条短信", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "恢复短信失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
