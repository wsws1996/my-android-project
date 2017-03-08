package com.wang.paint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private Canvas canvas;
    private Paint paint;
    private Bitmap copyBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);

        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);

        copyBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(),
                srcBitmap.getConfig());
        canvas = new Canvas(copyBitmap);
        paint = new Paint();
        canvas.drawBitmap(srcBitmap, new Matrix(), paint);

        iv.setImageBitmap(copyBitmap);

        iv.setOnTouchListener(new View.OnTouchListener() {

            private int startY;
            private int startX;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        System.out.println("按下:" + startX + "----------" + startY);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int stopX = (int) event.getX();
                        int stopY = (int) event.getY();
                        System.out.println("移动:" + stopX + "-----------" + stopY);
                        canvas.drawLine(startX, startY, stopX, stopY, paint);

                        startX = stopX;
                        startY = stopY;

                        iv.setImageBitmap(copyBitmap);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }


                return true;
            }
        });
    }

    public void click1(View view) {
        paint.setColor(Color.RED);
    }

    public void click2(View view) {
        paint.setStrokeWidth(15);
    }

    public void click3(View view) {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getPath(), "paint.png");
            FileOutputStream fos = new FileOutputStream(file);
            copyBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
            intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
            sendBroadcast(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
