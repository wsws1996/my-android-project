package com.wang.baidumusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wang on 17-3-4.
 */

public class MusicService extends Service {

    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void seekToPosition(int position) {
        player.seekTo(position);
    }

    public void playMusic() {

        try {
            player.reset();
            player.setDataSource("/mnt/sdcard/xpg.mp3");
            player.prepare();
            player.start();
            updateSeekBar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("音乐播放了");
    }

    private void updateSeekBar() {
        final int duration = player.getDuration();
        final Timer timer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int currentPosition = player.getCurrentPosition();

                Message message = Message.obtain();

                Bundle bundle = new Bundle();
                bundle.putInt("duration", duration);
                bundle.putInt("currentPosition", currentPosition);
                message.setData(bundle);
                MainActivity.handler.sendMessage(message);
            }
        };
        timer.schedule(task, 300, 1000);


        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                timer.cancel();
                task.cancel();
                System.out.println("歌曲播放完成了");
            }
        });
    }

    public void pauseMusic() {
        player.pause();
        System.out.println("音乐暂停了");
    }

    public void rePlayMusic() {
        player.start();
        System.out.println("音乐继续播放了");
    }

    private class MyBinder extends Binder implements Iservice {

        @Override
        public void callPlayMusic() {
            playMusic();
        }

        @Override
        public void callPauseMusic() {
            pauseMusic();
        }

        @Override
        public void callRePlayMusic() {
            rePlayMusic();
        }

        @Override
        public void callSeekToPosition(int position) {
            seekToPosition(position);
        }
    }
}
