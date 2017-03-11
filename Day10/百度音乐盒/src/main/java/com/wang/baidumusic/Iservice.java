package com.wang.baidumusic;

/**
 * Created by wang on 17-3-4.
 */

public interface Iservice {
    public void callPlayMusic();

    public void callPauseMusic();

    public void callRePlayMusic();

    public void callSeekToPosition(int position);
}
