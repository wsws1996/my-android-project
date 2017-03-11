package com.wang.videoview;

import android.app.Activity;
import android.os.Bundle;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class MainActivity extends Activity {

    private VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Vitamio.isInitialized(getApplicationContext());

        final VideoView vv = (VideoView) findViewById(R.id.vv);
        vv.setVideoPath("http://10.42.0.1:8080/sh.avi");
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
               vv.start();

            }
        });
        //设置video的控制器
        vv.setMediaController(new MediaController(this));
    }

}
