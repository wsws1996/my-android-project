package com.wang.phonelistener;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * Created by wang on 17-3-3.
 */

public class PhoneService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        tm.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);

        super.onCreate();
    }

    private class MyPhoneStateListener extends PhoneStateListener {

        private MediaRecorder recorder;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    if (recorder != null) {
                        System.out.println("停止录");
                        recorder.stop();
                        recorder.reset();   // You can reuse the object by going back to
                        // setAudioSource() step
                        recorder.release(); // Now the object cannot be reused
                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    if (recorder != null) {
                        System.out.println("开始录");
                        recorder.start();   // Recording is now started
                    }
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    System.out.println("录音机");
                    recorder = new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    recorder.setOutputFile("/mnt/sdcard/luyin.3gp");
                    try {
                        recorder.prepare();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
            }

            super.onCallStateChanged(state, incomingNumber);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
