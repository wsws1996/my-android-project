package com.wang.login_sdcard.util;

import android.content.Context;
import android.os.Environment;

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
    public static boolean saveUserInfo(Context context, String username, String password) {
        try {
            String userinfo = username + "##" + password;
            String path = Environment.getExternalStorageDirectory().getPath();
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
            String path = Environment.getExternalStorageDirectory().getPath();
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
