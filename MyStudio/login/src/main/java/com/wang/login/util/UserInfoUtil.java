package com.wang.login.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang on 17-2-9.
 */
public class UserInfoUtil {
    public static Map<String, String> getUserInfo_android(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo.txt",
                    Context.MODE_PRIVATE);
//            SharedPreferences sharedPreferences = PreferenceManager
//                    .getDefaultSharedPreferences(context);
            String username = sharedPreferences.getString("username", "");
            String password = sharedPreferences.getString("password", "");

//            FileInputStream fileInputStream = context.openFileInput("userinfo.txt");
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
//                    (fileInputStream));
//            String readLine = bufferedReader.readLine();
//            bufferedReader.close();
//            fileInputStream.close();
//            String[] split = readLine.split("##");
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("username", username);
            hashMap.put("password", password);
            return hashMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean saveUserInfo_android(Context context, String username, String password) {
        try {


            SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo.txt",
                    Context.MODE_PRIVATE);
//            SharedPreferences sharedPreferences = PreferenceManager
//                    .getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.commit();


//            String userinfo = username + "##" + password;
//            FileOutputStream fileOutputStream = context.openFileOutput("userinfo.txt", context
//                    .MODE_PRIVATE);
//            fileOutputStream.write(userinfo.getBytes());
//            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean saveUserInfo(Context context, String username, String password) {
        try {
            String userinfo = username + "##" + password;
//            String path = "/data/data/com.wang.login/";
            String path = context.getFilesDir().getPath();
            System.out.println(".........................:" + path);
            File file = new File(path, "userinfo.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(userinfo.getBytes());
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Map<String, String> getUserInfo(Context context) {
        try {
//            String path = "/data/data/com.wang.login/";
            String path = context.getFilesDir().getPath();
            File file = new File(path, "userinfo.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                    (fileInputStream));
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            fileInputStream.close();
            String[] split = readLine.split("##");
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("username", split[0]);
            hashMap.put("password", split[1]);
            return hashMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
