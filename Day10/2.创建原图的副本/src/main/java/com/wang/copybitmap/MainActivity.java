package com.wang.copybitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv_src = (ImageView) findViewById(R.id.iv_src);
        final ImageView iv_copy = (ImageView) findViewById(R.id.iv_copy);

        final Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tomcat);


        iv_src.setImageBitmap(srcBitmap);

        final Bitmap copyBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap
                        .getHeight(),
                srcBitmap.getConfig());
        Canvas canvas = new Canvas(copyBitmap);
        Paint paint = new Paint();
        Matrix matrix = new Matrix();
        matrix.setScale(-1.0f, 1.0f);
        matrix.postTranslate(srcBitmap.getWidth(), 0);
        canvas.drawBitmap(srcBitmap, matrix, paint);
        iv_copy.setImageBitmap(copyBitmap);

    }
}
