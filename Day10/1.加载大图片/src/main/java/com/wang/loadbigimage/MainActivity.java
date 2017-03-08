package com.wang.loadbigimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private int screenHeight;
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();

        System.out.println("手机的宽和高:" + screenWidth + "--------" + screenHeight);
    }

    public void click(View view) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile("/mnt/sdcard/test.jpg", options);

        int imgWidth = options.outWidth;
        int imgHeight = options.outHeight;
        System.out.println("图片的宽:" + imgWidth + "---------" + imgHeight);

        int scale = 1;
        int scalex = imgWidth / screenWidth;
        int scaley = imgHeight / screenHeight;

        if (scalex >= scaley) {
            scale = scalex;
        } else {
            scale = scaley;
        }
        if (scale < 1) {
            scale = 1;
        }

        System.out.println("缩放比为:" + scale);

        options.inSampleSize = scale;

        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile("/mnt/sdcard/test.jpg", options);

        iv.setImageBitmap(bitmap);
    }
}
