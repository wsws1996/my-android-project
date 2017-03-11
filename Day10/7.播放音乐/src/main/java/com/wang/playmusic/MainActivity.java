package com.wang.playmusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SurfaceView sfv = (SurfaceView) findViewById(R.id.sfv);

        SurfaceHolder holder = sfv.getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                player = new MediaPlayer();

                try {
                    player.setDataSource("http://10.42.0.1:8080/oppo.mp4");
                    player.setDisplay(holder);
                    player.prepareAsync();

                    player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            player.start();
                            player.seekTo(currentPosition);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int
                    height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                System.out.println("surfaceDestroyed");
                if (player != null && player.isPlaying()) {
                    currentPosition = player.getCurrentPosition();
                    player.stop();
                }
            }
        });


    }

}
