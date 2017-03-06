package com.wang.querycontacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Contact> queryContacts = QueryContactUtils.queryContacts(getApplicationContext());
        for (Contact contact :
                queryContacts) {
            System.out.println(contact);
        }
    }
}
