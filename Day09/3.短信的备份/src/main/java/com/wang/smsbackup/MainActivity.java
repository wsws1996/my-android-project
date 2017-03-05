package com.wang.smsbackup;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        try {
            XmlSerializer serializer = Xml.newSerializer();
            File file = new File(Environment.getExternalStorageDirectory().getPath(), "smsbackup" +
                    ".xml");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            serializer.setOutput(fileOutputStream, "utf-8");
            serializer.startDocument("utf-8", true);
            serializer.startTag(null, "smss");
            Uri uri = Uri.parse("content://sms/");
            Cursor cursor = getContentResolver().query(uri, new String[]{"address", "date", "body"},
                    null, null, null);
            while (cursor.moveToNext()) {
                String address = cursor.getString(0);
                String date = cursor.getString(1);
                String body = cursor.getString(2);

                serializer.startTag(null, "sms");

                serializer.startTag(null, "address");
                serializer.text(address);
                serializer.endTag(null, "address");

                serializer.startTag(null, "date");
                serializer.text(date);
                serializer.endTag(null, "date");

                serializer.startTag(null, "body");
                serializer.text(body);
                serializer.endTag(null, "body");

                serializer.endTag(null, "sms");

            }

            serializer.endTag(null, "smss");
            serializer.endDocument();

            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
