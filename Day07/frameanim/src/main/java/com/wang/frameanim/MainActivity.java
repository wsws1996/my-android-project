package com.wang.frameanim;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView rocketImage = (ImageView) findViewById(R.id.iv);
        rocketImage.setBackgroundResource(R.drawable.my_anim);

        AnimationDrawable ad = (AnimationDrawable) rocketImage.getBackground();
        ad.start();
    }
}
