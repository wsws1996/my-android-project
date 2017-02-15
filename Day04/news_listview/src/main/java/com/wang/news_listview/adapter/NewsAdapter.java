package com.wang.news_listview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wang.news_listview.R;
import com.wang.news_listview.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by wang on 17-2-13.
 */

public class NewsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NewsBean> list;

    public NewsAdapter(Context context, ArrayList<NewsBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
//            view = View.inflate(context, R.layout.item_news_layout, null);
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.from(context).inflate(R.layout.item_news_layout, null);
        }

        ImageView item_img_icon = (ImageView) view.findViewById(R.id.item_img_icon);
        TextView item_tv_des = (TextView) view.findViewById(R.id.item_tv_des);
        TextView item_tv_title = (TextView) view.findViewById(R.id.item_tv_title);
        TextView item_tv_comment = (TextView) view.findViewById(R.id.item_tv_comment);
        TextView item_tv_type = (TextView) view.findViewById(R.id.item_tv_type);

        NewsBean newsBean = list.get(position);

//        item_img_icon.setImageDrawable(newsBean.icon);
        item_tv_title.setText(newsBean.title);
        item_tv_des.setText(newsBean.des);
        item_tv_comment.setText("评论：" + newsBean.comment);

        switch (newsBean.type) {
            case 0:
                item_tv_type.setText("头条");
                break;
            case 1:
                item_tv_type.setText("娱乐");
                break;
            case 2:
                item_tv_type.setText("体育");
                break;
            default:
                item_tv_type.setText("八卦");
                break;
        }


        return view;
    }
}
