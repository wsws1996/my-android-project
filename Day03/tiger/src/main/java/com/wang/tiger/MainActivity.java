package com.wang.tiger;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        ListView lv_tiger1 = (ListView) findViewById(R.id.lv_tiger1);
        ListView lv_tiger2 = (ListView) findViewById(R.id.lv_tiger2);
        ListView lv_tiger3 = (ListView) findViewById(R.id.lv_tiger3);

        TigerAdapter tigerAdapter = new TigerAdapter();
        lv_tiger1.setAdapter(tigerAdapter);
        lv_tiger2.setAdapter(tigerAdapter);
        lv_tiger3.setAdapter(tigerAdapter);
    }

    class TigerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 500;
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
            Random random = new Random();
            int number = random.nextInt(5);
            switch (number) {
                case 0:
                    view.setTextColor(Color.parseColor("#ff00ff"));
                    view.setText("桃");
                    break;
                case 1:
                    view.setTextColor(Color.YELLOW);
                    view.setText("杏");
                    break;
                case 2:
                    view.setTextColor(Color.GREEN);
                    view.setText("梨");
                    break;
                case 3:
                    view.setTextColor(Color.RED);
                    view.setText("枣");
                    break;
                default:
                    view.setTextColor(Color.parseColor("#666666"));
                    view.setText("瓜");
                    break;
            }
            view.setTextSize(28);

            return view;
        }
    }
}
