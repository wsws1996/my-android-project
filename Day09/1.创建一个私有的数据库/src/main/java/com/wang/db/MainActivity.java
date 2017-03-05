package com.wang.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyOpenHelper helper = new MyOpenHelper(getApplicationContext());
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor cursor = database.query("info", null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(1);
                String money = cursor.getString(2);
                System.out.println("name:" + name + "------------" + money);
            }
            cursor.close();
        }
    }
}
