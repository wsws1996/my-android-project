package com.wang.muchthreaddownload_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by wang on 17-2-21.
 */

public class SharedUtils {
    public static int getLastposition(Context context, int threadId) {
        SharedPreferences defaultSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return defaultSharedPreferences.getInt("lastPosition" + threadId, -1);
    }

    public static void setLastposition(Context context, int threadId, int position) {
        SharedPreferences defaultSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        defaultSharedPreferences.edit().putInt("lastPosition" + threadId, position).commit();
    }
}
