package com.wang.login_sdcard.net;

import android.os.Handler;
import android.os.Message;

import com.wang.login_sdcard.util.StreamUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by wang on 17-2-19.
 */

public class LoginHttpUtils {
    public static void requestNetForGetLogin(final Handler handler, final String username, final
    String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String path = "http://10.0.2.2:8080/wang/servlet/LoginServlet?username=" +
                            URLEncoder.encode(username) + "&pwd=" + URLEncoder.encode
                            (password);
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(path);
                    HttpResponse response = httpClient.execute(httpGet);
                    StatusLine statusLine = response.getStatusLine();
                    int code = statusLine.getStatusCode();
                    if (code == 200) {
                        HttpEntity entity = response.getEntity();
                        InputStream inputStream = entity.getContent();

                        String result = StreamUtils.streamToString(inputStream);

                        Message msg = Message.obtain();
                        msg.what = 1;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    public static void requestNetForPostLogin(final Handler handler, final String username, final
    String password) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String path = "http://10.0.2.2:8080/wang/servlet/LoginServlet";

                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(path);


                    ArrayList<NameValuePair> arrayList = new ArrayList<>();
                    BasicNameValuePair nameValuePair = new BasicNameValuePair("username", username);
                    arrayList.add(nameValuePair);
                    BasicNameValuePair nameValuePair1 = new BasicNameValuePair("pwd", password);
                    arrayList.add(nameValuePair1);
                    UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity
                            (arrayList, "utf-8");
                    httpPost.setEntity(urlEncodedFormEntity);
                    HttpResponse response = httpClient.execute(httpPost);
                    int code = response.getStatusLine().getStatusCode();
                    if (code == 200) {
                        HttpEntity entity = response.getEntity();
                        InputStream inputStream = entity.getContent();

                        String result = StreamUtils.streamToString(inputStream);

                        Message msg = Message.obtain();
                        msg.what = 2;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
