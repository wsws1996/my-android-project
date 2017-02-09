package com.wang.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.wang.login.util.UserInfoUtil;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_rem;
    private Button bt_login;
    private Context mContext;

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

        Map<String, String> map = UserInfoUtil.getUserInfo_android(mContext);
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

    private void login() {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        boolean isrem = cb_rem.isChecked();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(mContext, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isrem) {
            boolean result = UserInfoUtil.saveUserInfo_android(mContext, username, password);
            if (result) {
                Toast.makeText(mContext, "用户名密码保存成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "用户名密码保存失败", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(mContext, "无需保存", Toast.LENGTH_SHORT).show();
        }
    }
}
