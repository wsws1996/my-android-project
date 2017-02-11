package com.wang.createdatabase.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wang.createdatabase.MySqliteOpenHelper;
import com.wang.createdatabase.bean.InfoBean;

/**
 * Created by wang on 17-2-11.
 */

public class InfoDao {

    private final MySqliteOpenHelper mySqliteOpenHelper;

    public InfoDao(Context context) {
        mySqliteOpenHelper = new MySqliteOpenHelper(context);
    }

    public boolean add(InfoBean bean) {
        SQLiteDatabase readableDatabase = mySqliteOpenHelper.getReadableDatabase();
//        readableDatabase.execSQL("insert into info(name,phone) values(?,?);", new Object[]{bean
//                .name, bean.phone});
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", bean.name);
        contentValues.put("phone", bean.phone);
        long result = readableDatabase.insert("info", null, contentValues);
        readableDatabase.close();
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }

    public int del(String name) {
        SQLiteDatabase readableDatabase = mySqliteOpenHelper.getReadableDatabase();
//        readableDatabase.execSQL("delete from info where name=?;", new Object[]{name});
        int result = readableDatabase.delete("info", "name=?", new String[]{name});
        readableDatabase.close();
        return result;
    }

    public int update(InfoBean bean) {
        SQLiteDatabase readableDatabase = mySqliteOpenHelper.getReadableDatabase();
//        readableDatabase.execSQL("update info set phone=? where name=?;", new Object[]{bean
//                .phone, bean.name});
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", bean.phone);
        int result = readableDatabase.update("info", contentValues, "name=?", new String[]{bean
                .name});
        readableDatabase.close();
        return result;
    }

    public void query(String name) {
        SQLiteDatabase readableDatabase = mySqliteOpenHelper.getReadableDatabase();
//        Cursor cursor = readableDatabase.rawQuery("select _id, name,phone from info where
// name=?;",
//                new String[]{name});
        Cursor cursor = readableDatabase.query("info", new String[]{"_id", "name", "phone"},
                "name=?", new String[]{name}, null, null, "_id desc");
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name_str = cursor.getString(1);
                String phone = cursor.getString(2);

                System.out.println("_id:" + id + ";name:" + name + ";phone:" + phone);
            }
            cursor.close();
        }

        readableDatabase.close();
    }
}
