package com.wang.sourcelook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wang on 17-2-14.
 */

public class StreamUtils {
    public static String streamToString(InputStream in) {
        String result = "";
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
                out.flush();
            }
            result = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
