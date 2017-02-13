package com.wang.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        ListView lv_simple = (ListView) findViewById(R.id.lv_simple);
        MyListAdapter myListAdapter = new MyListAdapter();
        lv_simple.setAdapter(myListAdapter);
    }

    class MyListAdapter extends BaseAdapter {

        private Map<Integer, Integer> map = new HashMap<>();

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = null;
            if (convertView != null) {
                view = (TextView) convertView;
            } else {
                view = new TextView(mContext);
            }
            view.setText("position:" + position);
            view.setTextSize(40);
            map.put(view.hashCode(), 1);
            System.out.println("创建了" + map.size() + "个TextView对象");
            return view;

        }
    }
}
