package com.wang.other_adapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public String[] classz = {"android", "ios", "javaEE", "C#"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv_array = (ListView) findViewById(R.id.lv_array);
        ListView lv_simple = (ListView) findViewById(R.id.lv_simple);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout
                .item_listview_layout, R.id.item_tv_class, classz);
        lv_array.setAdapter(arrayAdapter);

        ArrayList<Map<String, String>> arrayList = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("class", "c++");
        arrayList.add(hashMap);

        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("class", "android");
        arrayList.add(hashMap1);

        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("class", "javaEE");
        arrayList.add(hashMap2);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, arrayList, R.layout
                .item_listview_layout, new String[]{"class"}, new int[]{R.id.item_tv_class});
        lv_simple.setAdapter(simpleAdapter);

    }
}
