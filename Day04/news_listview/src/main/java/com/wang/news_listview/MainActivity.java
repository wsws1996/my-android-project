package com.wang.news_listview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wang.news_listview.adapter.NewsAdapter;
import com.wang.news_listview.bean.NewsBean;
import com.wang.news_listview.utils.NewsUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Context mContext;
    private ListView lv_news;

    private Handler handler = new

            Handler() {
                @Override
                public void handleMessage(Message msg) {
                    ArrayList<NewsBean> allNews = (ArrayList<NewsBean>) msg.obj;
                    if (allNews != null && allNews.size() > 0) {
                        NewsAdapter newsAdapter = new NewsAdapter(mContext, allNews);
                        lv_news.setAdapter(newsAdapter);
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        lv_news = (ListView) findViewById(R.id.lv_news);

        ArrayList<NewsBean> allnews_database = NewsUtils.getAllNewsForDatabase(mContext);

        if (allnews_database != null && allnews_database.size() > 0) {
            NewsAdapter newsAdapter = new NewsAdapter(mContext, allnews_database);
            lv_news.setAdapter(newsAdapter);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<NewsBean> allNews = NewsUtils.getAllNewsForNetWork(mContext);
                Message msg = Message.obtain();
                msg.obj = allNews;
                handler.sendMessage(msg);
            }
        }).start();


        lv_news.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsBean bean = (NewsBean) parent.getItemAtPosition(position);
        String url = bean.news_url;

        Intent intent = new Intent();
        intent.setAction(intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }
}
