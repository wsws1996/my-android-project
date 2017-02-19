package com.wang.login_sdcard;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.wang.login_sdcard.net.LoginHttpUtils;
import com.wang.login_sdcard.util.UserInfoUtil;

import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_rem;
    private Button bt_login;
    private Context mContext;
    private String username;
    private String password;
    private boolean isrem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;


        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        cb_rem = (CheckBox) findViewById(R.id.cb_rem);
        bt_login = (Button) findViewById(R.id.bt_login);

        bt_login.setOnClickListener(this);

        Map<String, String> map = UserInfoUtil.getUserInfo(mContext);
        if (map != null) {
            String username = map.get("username");
            String password = map.get("password");
            et_username.setText(username);
            et_password.setText(password);
            cb_rem.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                login();
                break;
            default:
                break;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String result = (String) msg.obj;
            switch (msg.what) {
                case 1:
                    Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
//                    if (issuccess) {
//
//                        if (isrem) {
//
//                            if (!Environment.getExternalStorageState().equals(Environment
//                                    .MEDIA_MOUNTED)) {
//                                Toast.makeText(mContext, "sdcard不存在或未挂载", Toast.LENGTH_SHORT)
//                                        .show();
//                                return;
//                            }
//
//                            boolean result = UserInfoUtil.saveUserInfo(mContext, username,
//                                    password);
//                            if (result) {
//                                Toast.makeText(mContext, "get:用户名密码保存成功", Toast.LENGTH_SHORT)
//                                        .show();
//                            } else {
//                                Toast.makeText(mContext, "get:用户名密码保存失败", Toast.LENGTH_SHORT)
//                                        .show();
//                            }
//                        } else {
//                            Toast.makeText(mContext, "get:无需保存", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(mContext, "get:登录失败", Toast.LENGTH_SHORT).show();
//                    }

                    break;
                case 2:
                    Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
//                    if (issuccess) {
//                        if (isrem) {
//
//                            Strif (!Environment.getExternalStorageState().equals(Environment
//                                    .MEDIA_MOUNTED)) {
//                                Toast.makeText(mContext, "sdcard不存在或未挂载", Toast.LENGTH_SHORT)
//                                        .show();
//                                return;
//                            }
//
//                            boolean result = UserInfoUtil.saveUserInfo(mContext, username,
//                                    password);
//                            if (result) {
//                                Toast.makeText(mContext, "post:用户名密码保存成功", Toast.LENGTH_SHORT)
//                                        .show();
//                            } else {
//                                Toast.makeText(mContext, "post:用户名密码保存失败", Toast.LENGTH_SHORT)
//                                        .show();
//                            }
//                        } else {
//                            Toast.makeText(mContext, "post:无需保存", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(mContext, "post:登录失败", Toast.LENGTH_SHORT).show();
//                    }
//                    break;
            }
        }


    };

    private void login() {
        username = et_username.getText().toString().trim();
        password = et_password.getText().toString().trim();
        isrem = cb_rem.isChecked();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(mContext, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Random random = new Random();
        int num = random.nextInt(10);
        LoginHttpUtils.requestNetForPostLogin(handler, username, password);
//        LoginHttpUtils.requestNetForGetLogin(handler, username, password);

    }


}
