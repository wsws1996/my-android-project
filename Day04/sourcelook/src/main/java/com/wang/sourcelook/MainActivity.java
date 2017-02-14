package com.wang.sourcelook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_url;
    private ImageView img_pic;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        et_url = (EditText) findViewById(R.id.et_url);
        Button bt_looksource = (Button) findViewById(R.id.bt_looksource);
        img_pic = (ImageView) findViewById(R.id.img_pic);

        bt_looksource.setOnClickListener(this);

        System.out.println("oncreate方法线程：" + Thread.currentThread().getName());

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            img_pic.setImageBitmap(bitmap);
        }
    };

    @Override
    public void onClick(View v) {
        try {
            final String url_str = et_url.getText().toString().trim();
            if (TextUtils.isEmpty(url_str)) {
                Toast.makeText(mContext, "url不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            System.out.println("onclick方法线程：" + Thread.currentThread().getName());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("onclick方法runnable线程：" + Thread.currentThread()
                                .getName());
                        URL url = new URL(url_str);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(1000 * 10);

//            Thread.sleep(20 * 1000);

                        int code = connection.getResponseCode();
                        if (code == 200) {
                            InputStream inputStream = connection.getInputStream();
//                            String result = StreamUtils.streamToString(inputStream);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            Message message = Message.obtain();
                            message.obj = bitmap;
                            handler.sendMessage(message);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
