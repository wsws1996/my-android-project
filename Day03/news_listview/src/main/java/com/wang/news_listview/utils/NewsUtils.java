package com.wang.news_listview.utils;

import android.content.Context;

import com.wang.news_listview.R;
import com.wang.news_listview.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by wang on 17-2-13.
 */
public class NewsUtils {
    public static ArrayList<NewsBean> getAllNews(Context context) {
        ArrayList<NewsBean> arrayList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {

            NewsBean newsBean = new NewsBean();
            newsBean.title = "重中自自在在吱吱喳喳知足知止组织重中自自在在吱吱喳喳知足知止组织重中自自在在吱吱喳喳知足知止组织";
            newsBean.des = "广告歌古古怪怪更尴尬公关稿更改广告歌古古怪怪更尴尬公关稿更改广告歌古古怪怪更尴尬公关稿更改";
            newsBean.news_url = "https://www.sina.cn";
            newsBean.icon = context.getResources().getDrawable(R.drawable.icon1);
            arrayList.add(newsBean);

            NewsBean newsBean1 = new NewsBean();
            newsBean1.title = "凄凄切切去瞧瞧请求权圈圈圈齐全期凄凄切切去瞧瞧请求权圈圈圈齐全期";
            newsBean1.des = "吾问无为谓勿忘我往万维网窝窝网嗡嗡嗡吾问无为谓勿忘我往万维网窝窝网嗡嗡嗡";
            newsBean1.news_url = "https://www.baidu.com";
            newsBean1.icon = context.getResources().getDrawable(R.drawable.icon2);
            arrayList.add(newsBean1);

            NewsBean newsBean2 = new NewsBean();
            newsBean2.title = "扰扰攘攘融入柔柔弱弱仍然融入扰扰攘攘融入柔柔弱弱仍然融入";
            newsBean2.des = "探讨探讨妥妥帖帖头痛天天通忐忐忑忑天堂探讨探讨妥妥帖帖头痛天天通忐忐忑忑天堂";
            newsBean2.news_url = "https://www.github.com";
            newsBean2.icon = context.getResources().getDrawable(R.drawable.icon3);
            arrayList.add(newsBean2);
        }

        return arrayList;
    }
}
