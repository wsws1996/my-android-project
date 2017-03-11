package com.wang.syf;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView iv = (ImageView) findViewById(R.id.iv);

        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pre19);

        final Bitmap alterBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(),
                srcBitmap.getConfig());
        Canvas canvas = new Canvas(alterBitmap);
        Paint paint = new Paint();
        canvas.drawBitmap(srcBitmap, new Matrix(), paint);

        iv.setImageBitmap(alterBitmap);

        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    for (int i = -50; i < 50; i++) {
                        for (int j = -50; j < 50; j++) {
                            if (Math.sqrt(i * i + j * j) < 50)
                                try {
                                    alterBitmap.setPixel((int) event.getX() + i, (int) event.getY() + j,
                                            Color.TRANSPARENT);
                                } catch (Exception e) {
                                }
                        }
                    }
                    iv.setImageBitmap(alterBitmap);
                }

                return true;
            }
        });
    }
}
