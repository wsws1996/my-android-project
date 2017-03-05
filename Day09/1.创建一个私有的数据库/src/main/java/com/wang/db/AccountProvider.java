package com.wang.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by wang on 17-3-5.
 */

public class AccountProvider extends ContentProvider {

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int QUERYSUCCESS = 0;
    private static final int INSERTSUCCESS = 1;
    private static final int DELETESUCCESS = 2;
    private static final int UPDATESUCCESS = 3;

    static {
        sURIMatcher.addURI("com.wang.provider", "query", QUERYSUCCESS);
        sURIMatcher.addURI("com.wang.provider", "insert", INSERTSUCCESS);
        sURIMatcher.addURI("com.wang.provider", "delete", DELETESUCCESS);
        sURIMatcher.addURI("com.wang.provider", "update", UPDATESUCCESS);
    }

    private MyOpenHelper myOpenHelper;

    @Override
    public boolean onCreate() {

        myOpenHelper = new MyOpenHelper(getContext());

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String
            selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        int code = sURIMatcher.match(uri);
        if (code == QUERYSUCCESS) {
            SQLiteDatabase database = myOpenHelper.getReadableDatabase();
            Cursor cursor = database.query("info", projection, selection, selectionArgs, null,
                    null, sortOrder);
//            database.close();
            return cursor;
        } else {
            throw new IllegalArgumentException("uri路径不匹配，请检查路径");
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int code = sURIMatcher.match(uri);
        if (code == INSERTSUCCESS) {
            SQLiteDatabase database = myOpenHelper.getReadableDatabase();
            long insert = database.insert("info", null, values);
            Uri uri1 = Uri.parse("com.wangasdfg/" + insert);
            database.close();
            return uri1;
        } else {
            throw new IllegalArgumentException("wg:uri路径不匹配，请检查路径");
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[]
            selectionArgs) {
        int code = sURIMatcher.match(uri);
        if (code == DELETESUCCESS) {
            SQLiteDatabase database = myOpenHelper.getReadableDatabase();
            int delete = database.delete("info", selection, selectionArgs);
            return delete;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String
            selection, @Nullable String[] selectionArgs) {

        int code = sURIMatcher.match(uri);
        if (code == UPDATESUCCESS) {
            SQLiteDatabase database = myOpenHelper.getWritableDatabase();
            int update = database.update("info", values, selection, selectionArgs);
            return update;
        } else {
            throw new IllegalArgumentException("ww:uri路径不匹配，请检查路径");
        }
    }
}
