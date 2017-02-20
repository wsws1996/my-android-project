package com.wang.login_sdcard.net;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * Created by wang on 17-2-19.
 */

public class LoginHttpUtils {
    public static void requestNetForGetLogin(final Context context, final Handler handler, final
    String username, final
                                             String password) {
        try {
            String path = "http://10.42.0.1:8080/wang/servlet/LoginServlet?username=" +
                    URLEncoder.encode(username) + "&pwd=" + URLEncoder.encode
                    (password);
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            asyncHttpClient.get(path, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[]
                        responseBody) {
                    if (statusCode == 200) {
                        try {
                            String result = new String(responseBody, "utf-8");
                            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[]
                        responseBody, Throwable error) {
                    System.out.println(".......................onFailure");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void requestNetForPostLogin(final Context context, final Handler handler, final
    String username, final
                                              String password) {
        String path = "http://10.0.2.2:8080/wang/servlet/LoginServlet";
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final RequestParams requestParams = new RequestParams();
        requestParams.put("username", username);
        requestParams.put("pwd", password);
        asyncHttpClient.setURLEncodingEnabled(true);
        asyncHttpClient.post(path, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    try {
                        String result = new String(responseBody, "utf-8");
                        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody,
                                  Throwable error) {

            }
        });


    }
}
