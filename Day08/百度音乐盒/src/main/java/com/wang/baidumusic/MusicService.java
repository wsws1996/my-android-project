package com.wang.baidumusic;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by wang on 17-3-4.
 */

public class MusicService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void playMusic() {
        System.out.println("音乐播放了");
    }

    public void pauseMusic() {
        System.out.println("音乐暂停了");
    }

    public void rePlayMusic() {
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
    }
}
