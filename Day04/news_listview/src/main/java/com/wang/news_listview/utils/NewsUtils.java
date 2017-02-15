package com.wang.news_listview.utils;

import android.content.Context;

import com.wang.news_listview.bean.NewsBean;
import com.wang.news_listview.dao.NewsDaoUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by wang on 17-2-13.
 */
public class NewsUtils {

    public static String newsPath_url = "http://192.168.1.103:8080/wang/servlet/GetNewsServlet";

    public static ArrayList<NewsBean> getAllNewsForNetWork(Context context) {
        ArrayList<NewsBean> arrayList = new ArrayList<>();

        try {
            URL url = new URL(newsPath_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10 * 1000);

            int code = connection.getResponseCode();

            if (code == 200) {
                InputStream inputStream = connection.getInputStream();
                String result = StreamUtils.streamToString(inputStream);

                JSONObject root_json = new JSONObject(result);
                JSONArray jsonArray = root_json.getJSONArray("newss");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject news_json = jsonArray.getJSONObject(i);

                    NewsBean newsBean = new NewsBean();

                    newsBean.id = news_json.getInt("id");
                    newsBean.comment = news_json.getInt("comment");
                    newsBean.type = news_json.getInt("type");

                    newsBean.time = news_json.getString("time");
                    newsBean.des = news_json.getString("des");
                    newsBean.title = news_json.getString("title");
                    newsBean.news_url = news_json.getString("news_url");
                    newsBean.icon_url = news_json.getString("icon_url");

                    arrayList.add(newsBean);
                }
                new NewsDaoUtils(context).delete();
                new NewsDaoUtils(context).saveNews(arrayList);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public static ArrayList<NewsBean> getAllNewsForDatabase(Context context) {
        return new NewsDaoUtils(context).getNews();
    }
}
