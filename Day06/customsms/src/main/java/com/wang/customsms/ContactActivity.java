package com.wang.customsms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wang on 17-2-24.
 */

public class ContactActivity extends AppCompatActivity {

    private ArrayList<Contact> contactLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ListView lv_contact = (ListView) findViewById(R.id.lv_contact);

        contactLists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Contact contact = new Contact();
            contact.name = "zhangsan" + i;
            contact.phone = "123456789" + i;
            contactLists.add(contact);
        }

        lv_contact.setAdapter(new MyAdapter());

        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phone = contactLists.get(position).phone;
                System.out.println("phone:" + phone);
                Intent intent = new Intent();
                intent.putExtra("name", phone);
                setResult(10, intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return contactLists.size();
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
            View view;
            if (convertView == null) {
                view = View.inflate(getApplicationContext(), R.layout.contact_item, null);
            } else {
                view = convertView;
            }
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);

            tv_name.setText(contactLists.get(position).name);
            tv_phone.setText(contactLists.get(position).phone);

            return view;
        }
    }
}
