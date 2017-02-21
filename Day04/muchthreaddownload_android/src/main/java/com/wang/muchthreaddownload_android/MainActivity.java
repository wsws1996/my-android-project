package com.wang.muchthreaddownload_android;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private int threadCount = 0;
    private int blockSize = 0;
    private String path = "http://10.42.0.1:8080/wang/j2se8.zip";
    private int runningThreadCount = 0;
    private EditText et_threadCount;
    private Context mContext;
    private LinearLayout ll_progress_layout;
    private Map<Integer, ProgressBar> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        findViewById(R.id.bt_download).setOnClickListener(this);
        et_threadCount = (EditText) findViewById(R.id.et_threadCount);
        ll_progress_layout = (LinearLayout) findViewById(R.id.ll_progress_layout);
    }

    @Override
    public void onClick(View v) {
        String threadCount_str = et_threadCount.getText().toString().trim();
        threadCount = Integer.parseInt(threadCount_str);

        ll_progress_layout.removeAllViews();

        for (int i = 0; i < threadCount; i++) {
            ProgressBar progressBar = (ProgressBar) View.inflate(mContext, R.layout
                    .child_progressbar_layout, null);
            map.put(i, progressBar);
            ll_progress_layout.addView(progressBar);
        }
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        download();
                    }
                }
        ).start();

    }

    private void download() {
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10 * 1000);

            int code = connection.getResponseCode();
            if (code == 200) {
                int fileLength = connection.getContentLength();
                RandomAccessFile randomAccessFile = new RandomAccessFile(new File(getFileName
                        (path)), "rw");
                randomAccessFile.setLength(fileLength);

                blockSize = fileLength / threadCount;
                for (int threadId = 0; threadId < threadCount; threadId++) {
                    int startIndex = threadId * blockSize;
                    int endIndex = (threadId + 1) * blockSize - 1;

                    if (threadId == threadCount - 1) {
                        endIndex = fileLength - 1;
                    }
                    new DownloadThread(threadId, startIndex, endIndex).start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class DownloadThread extends Thread {
        private int currentThreadTotalProgress;
        private int threadId;
        private int startIndex;
        private int endIndex;
        private int lastPosition;
        private int oldstartIndex;

        public DownloadThread(int threadId, int startIndex, int endIndex) {
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.currentThreadTotalProgress = endIndex - startIndex + 1;
        }

        @Override
        public void run() {
            ProgressBar progressBar = map.get(threadId);
            synchronized (DownloadThread.class) {
                runningThreadCount = runningThreadCount + 1;
            }
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10 * 1000);

                oldstartIndex = startIndex;
                if (SharedUtils.getLastposition(mContext, threadId) != -1) {
                    lastPosition = SharedUtils.getLastposition(mContext, threadId);
                    if (lastPosition == endIndex + 1) {
                        progressBar.setProgress(currentThreadTotalProgress);
                        runningThreadCount = runningThreadCount - 1;
                    }
                    startIndex = lastPosition;
                }


                System.out.println("线程：" + threadId + "开始位置：" + startIndex + "，结束位置：" + endIndex);

                connection.setRequestProperty("Range", "bytes:" + startIndex + "-" + endIndex);

                int code = connection.getResponseCode();
                if (code == 206) {
                    InputStream inputStream = connection.getInputStream();
                    RandomAccessFile randomAccessFile = new RandomAccessFile(new File(getFileName
                            (path)), "rw");
                    randomAccessFile.seek(startIndex);

                    byte[] buffer = new byte[1024 * 50];
                    int length = -1;
                    int total = 0;
                    while ((length = inputStream.read(buffer)) != -1) {
                        randomAccessFile.write(buffer, 0, length);
                        progressBar.setMax(currentThreadTotalProgress);
                        total = total + length;
                        int currentThreadPosition = startIndex + total;
                        SharedUtils.setLastposition(mContext, threadId, currentThreadPosition);
                        progressBar.setProgress(currentThreadPosition - oldstartIndex);
                    }
                    inputStream.close();
                    randomAccessFile.close();
                    System.out.println("线程：" + threadId + "下载完毕");
                    runningThreadCount = runningThreadCount - 1;

                    synchronized (DownloadThread.class) {
                        if (runningThreadCount == 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mContext, "下载完毕", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String getFileName(String url) {
        return Environment.getExternalStorageDirectory() + "/" + url.substring(url.lastIndexOf
                ("/"));
    }
}
