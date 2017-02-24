package com.wang.customsms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by wang on 17-2-24.
 */

public class SmsTemplateActivity extends AppCompatActivity {
    String objects[] = {"我在开会，请稍后联系", "我在吃饭，请稍后联系", "我在打代码，请稍后联系", "我在开车，请稍后联系", "我在工作，请稍后联系"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        ListView lv = (ListView) findViewById(R.id.lv);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R
                .layout.item, objects);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String smscontent = objects[position];

                Intent intent = new Intent();
                intent.putExtra("smscontent", smscontent);

                setResult(20, intent);

                finish();
            }
        });
    }
}
