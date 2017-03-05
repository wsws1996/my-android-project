package com.wang.readdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View view) {
        Uri uri = Uri.parse("content://com.wang.provider/insert");
        ContentValues values = new ContentValues();
        values.put("name", "zhaoliu");
        values.put("money", 1000);
        Uri uri1 = getContentResolver().insert(uri, values);
        System.out.println("uri1:" + uri1);
    }

    public void click2(View view) {
        Uri uri = Uri.parse("content://com.wang.provider/delete");
        int delete = getContentResolver().delete(uri, "name=?", new String[]{"zhaoliu"});
        Toast.makeText(getApplicationContext(), "删除了" + delete + "行", Toast.LENGTH_LONG).show();
    }

    public void click3(View view) {
        Uri uri = Uri.parse("content://com.wang.provider/update");
        ContentValues values = new ContentValues();
        values.put("money", "1000000");
        int update = getContentResolver().update(uri, values, "name=?", new String[]{"zhaoliu"});
        Toast.makeText(getApplicationContext(), "更新了" + update + "行", Toast.LENGTH_LONG).show();
    }

    public void click4(View view) {
//        SQLiteDatabase database = SQLiteDatabase.openDatabase("/data/data/com.wang" +
//        ".db/databases/Account.db", null, SQLiteDatabase.OPEN_READWRITE);
//        Cursor cursor = database.query("info", null, null, null, null, null, null);
        Uri uri = Uri.parse("content://com.wang.provider/query");
        Cursor cursor = getContentResolver().query(uri, new String[]{"name", "money"}, null, null,
                null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                String money = cursor.getString(1);

                System.out.println("第二个应用：" + name + "---------" + money);
            }
        }
    }
}
