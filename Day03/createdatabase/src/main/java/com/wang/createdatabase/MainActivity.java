package com.wang.createdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wang.createdatabase.bean.InfoBean;
import com.wang.createdatabase.dao.InfoDao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        MySqliteOpenHelper mySqliteOpenHelper = new MySqliteOpenHelper(mContext);
        SQLiteDatabase readableDatabase = mySqliteOpenHelper.getReadableDatabase();
        findViewById(R.id.bt_add).setOnClickListener(this);
        findViewById(R.id.bt_del).setOnClickListener(this);
        findViewById(R.id.bt_update).setOnClickListener(this);
        findViewById(R.id.bt_query).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        InfoDao infoDao = new InfoDao(mContext);
        switch (v.getId()) {
            case R.id.bt_add:
                InfoBean infoBean = new InfoBean();
                infoBean.name = "张三";
                infoBean.phone = "110";
                boolean result = infoDao.add(infoBean);
                if (result) {
                    Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "添加失败", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.bt_del:
                int del = infoDao.del("张三");
                Toast.makeText(mContext, "成功删除" + del + "行", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_update:
                InfoBean infoBean2 = new InfoBean();
                infoBean2.name = "张三";
                infoBean2.phone = "119";
                int update = infoDao.update(infoBean2);
                Toast.makeText(mContext, "成功修改" + update + "行", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_query:
                infoDao.query("张三");
                break;
            default:
                break;
        }
    }
}
